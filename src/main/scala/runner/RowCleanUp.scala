package runner

import extraction.RawDataFormatter
import ingest.{ARRow, CSVReader}

import scala.util.Random


object RowCleanUp extends App {

  implicit val testing: Boolean = true
  var successes = 0
  var fails = 0

  // Get, read, and parse file into rows/columns
  val fileName = "/RawData.csv"
  val path = getClass.getResource(fileName).getPath
  val parsedLines = CSVReader.parseFile(path)

  // Try to extract info from the raw data
  val rowData = RawDataFormatter.applySchema(parsedLines, ARRow.attemptConversion)

  //Lets look at the first 10 rows to see how we did
  if(testing) {
    Random.shuffle(rowData).take(10).foreach {
      case Left(value) => failed(value)
      case Right(value) => succeeded(value)
    }
  }

//  rowData.foreach {
//    case Left(value) => failed(value)
//    case Right(value) => succeeded(value)
//  }

  printRes("")(true)

  def failed(strings: List[String]): Unit ={
    incFails()
    printRes(s"Failed: [$strings]")
  }

  def succeeded(info: ARRow): Unit = {
    incSuccesses()
    printRes(s"Succeeded: [${info.rawList}]")
    println(info)
  }

//  def succeededWithName(row: ARRow): Unit = {
//    info.name match {
//      case Some(name) if name.length == 0 =>
//        incFails()
//        printRes(s"Empty Name: [${info.raw}]")
//      case Some(_) =>
//        incSuccesses()
//        printRes(s"NonEmpty Name: [$info]")
//      case _ =>
//        incFails()
//        printRes(s"Some Error: [$info]")
//    }
//  }

  def printRes(string: String)(implicit testing: Boolean): Unit = {
    if(testing) println(f"[$successes%4d][$fails%4d] $string")
  }

  def incFails(): Unit = {
    fails = fails + 1
  }

  def incSuccesses(): Unit = {
    successes = successes + 1
  }
}
