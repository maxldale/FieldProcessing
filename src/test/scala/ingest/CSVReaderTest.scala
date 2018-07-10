package ingest

import java.net.URL

import org.scalatest.FunSuite

class CSVReaderTest extends FunSuite {

  val basicFileName: String = "/BasicCsv.csv"
  val wQuotesFileName: String = "/WQuotesCsv.csv"

  val basicLineOne: List[String] = List("value1", "value2", "value3")
  val basicLineTwo: List[String] = List("value4", "value5", "value6")

  val basicCsvLines: List[String] = List(basicLineOne.mkString(","), basicLineTwo.mkString(","))
  val basicCsvValues: List[List[String]] = List(basicLineOne, basicLineTwo)

  val wQuotesLineOne: List[String] = basicLineOne.updated(0, """"value1.0,value1.1"""")
  val wQuotesLineTwo: List[String] = basicLineTwo.updated(1, """"value5.0,value5.1,value5.2"""")

  val wQuotesCsvLines: List[String] = List(wQuotesLineOne.mkString(","), wQuotesLineTwo.mkString(","))
  val wQuotesCsvValues: List[List[String]] = List(wQuotesLineOne, wQuotesLineTwo)

  test("testParseDataBasic") {
    val res: List[List[String]] = CSVReader.parseData(basicCsvLines)
    assertResult(basicCsvValues)(res)
  }

  test("testParseDataWQuotes") {
    val res: List[List[String]] = CSVReader.parseData(wQuotesCsvLines)
    assertResult(wQuotesCsvValues)(res)
  }

  test("testReadDataBasic") {
    val pathURL: URL = getClass.getResource(basicFileName)
    val path: String = pathURL.getPath
    val res: List[String] = CSVReader.readData(path)
    assertResult(basicCsvLines)(res)
  }

  test("testReadDataWQuotes") {
    val pathURL: URL = getClass.getResource(wQuotesFileName)
    val path: String = pathURL.getPath
    val res: List[String] = CSVReader.readData(path)
    assertResult(wQuotesCsvLines)(res)
  }

}
