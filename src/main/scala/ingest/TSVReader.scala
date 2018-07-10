package ingest

import scala.io.Source

object TSVReader {

  def readData(fileName: String): List[String] = Source.fromFile(fileName).getLines().toList

  def parseData(lines: List[String]): List[List[String]] = {
    val regexStr = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"
    lines.map(_.split(regexStr, -1).toList)
  }

}
