package ingest

import scala.collection.mutable.ListBuffer
import scala.io.Source

object CSVReader {

  def readData(fileName: String): List[String] = Source.fromFile(fileName).getLines().toList

  private def oddQuotes(line: String): Boolean = {
    val quoteCount: Int = line.count(_ == '"')
    if((quoteCount % 2) == 0) false
    else true
  }

  private def fixCells(lines: List[String]): List[String] = {
    val fixedLines = new ListBuffer[String]()
    val lineBuilder = new StringBuilder
    for(line <- lines){
      lineBuilder.append(line + "\n")
      val currLine = lineBuilder.mkString
      if(!oddQuotes(currLine)){
        fixedLines.append(currLine)
        lineBuilder.clear
      }
    }
    if(lineBuilder.nonEmpty){
      val lastLine = lineBuilder.mkString
      fixedLines.append(lastLine)
    }
    fixedLines.result
  }

  def parseData(lines: List[String]): List[List[String]] = {
    val regexStr = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"
    fixCells(lines).map(_.split(regexStr, -1).map(_.trim).toList)
  }

}
