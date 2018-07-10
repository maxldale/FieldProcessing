package runner

import extraction.{RawDataFormatter, SalesContactInfo}
import ingest.CSVReader

object SalesContactInfoCleanUp extends App {
  val fileName = "/SalesContactInfo.csv"
  val pathUrl = getClass.getResource(fileName)
  println(pathUrl)
  val path = pathUrl.getPath
  println(path)
  val fileLines = CSVReader.readData(path)
  val parsedLines = CSVReader.parseData(fileLines)
  val rawData = RawDataFormatter.applySchema(parsedLines, SalesContactInfo.attemptConversion)
  val firstTen = rawData.take(10)
  firstTen.foreach {
    case Left(value) => println(value)
    case Right(value) => println(value)
  }
}
