package runner

import extraction.{RawDataFormatter, SalesContactInfo}
import ingest.CSVReader

import scala.util.Random

object SalesContactInfoCleanUp extends App {

  // Testing
  implicit val testing: Boolean = false
  var successes = 0
  var fails = 0

  // Get, read, and parse file into rows/columns
  val fileName = "/SalesContactInfo.csv"
  val path = getClass.getResource(fileName).getPath
  val parsedLines = CSVReader.parseFile(path)

  // Try to extract info from the raw data
  val salesContactData = RawDataFormatter.applySchema(parsedLines, SalesContactInfo.attemptConversion)

  // Lets look at the first 10 rows to see how we did
  if(testing) {
    Random.shuffle(salesContactData).take(10).foreach {
      case Left(value) => failed(value)
      case Right(value) => succeeded(value)
    }
  }

  salesContactData.foreach {
    case Left(value) => failed(value)
    case Right(value) => succeeded(value)
  }

  printRes("")(true)

  def failed(strings: List[String]): Unit ={
    incFails
    printRes(s"Failed: [$strings]")(true)
  }

  def succeeded(info: SalesContactInfo): Unit = {
    info.name match {
      case Some(name) =>
        succeededWithName(info)
      case None =>
        incFails
        printRes(s"No Name: [$info]")(true)
    }
  }

  def succeededWithName(info: SalesContactInfo): Unit = {
    info.name match {
      case Some(name) if name.isEmpty =>
        incFails
        printRes(s"Empty Name: [${info.raw}]")(true)
      case Some(_) =>
        incSuccesses
        printRes(s"NonEmpty Name: [$info]")
      case _ =>
        incFails
        printRes(s"Some Error: [$info]")(true)
    }
  }

  def printRes(string: String)(implicit testing: Boolean): Unit = {
    if(testing) println(f"[$successes%4d][$fails%4d] $string")
  }

  def incFails: Unit = {
    fails = fails + 1
  }

  def incSuccesses: Unit = {
    successes = successes + 1
  }
}
