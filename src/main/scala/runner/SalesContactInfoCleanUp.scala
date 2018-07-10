package runner

import extraction.{RawDataFormatter, SalesContactInfo}
import ingest.CSVReader

object SalesContactInfoCleanUp extends App {

  // Get, read, and parse file into rows/columns
  val fileName = "/SalesContactInfo.csv"
  val path = getClass.getResource(fileName).getPath
  val parsedLines = CSVReader.parseFile(path)

  // Try to extract info from the raw data
  val salesContactData = RawDataFormatter.applySchema(parsedLines, SalesContactInfo.attemptConversion)

  // Lets look at the first 10 rows to see how we did
  val firstTen = salesContactData.take(10)
  firstTen.foreach {
    case Left(value) => println(s"Failed:\t[$value]\n")
    case Right(value) => println(s"Success:\t[$value]\n")
  }
}
