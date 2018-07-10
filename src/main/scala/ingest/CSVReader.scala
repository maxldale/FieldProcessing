package ingest

import scala.collection.mutable.ListBuffer
import scala.io.Source

object CSVReader {

  /**
    * METHOD readData
    * Read all lines of a file
    * @param fileName qualified path to a file
    * @return a List of all lines
    */
  def readData(fileName: String): List[String] = Source.fromFile(fileName).getLines().toList

  /**
    * HELPER METHOD oddQuotes
    * If there are an even number of quotes: false, else: true
    * @param line text to count quotes in
    * @return boolean parity of the number of quotes
    */
  private def oddQuotes(line: String): Boolean = {
    val quoteCount: Int = line.count(_ == '"')
    if((quoteCount % 2) == 0) false
    else true
  }

  /**
    * HELPER METHOD fixCells
    * Multiline cells are split up, this method puts them back together
    * @param lines the lines to fix
    * @return properly paired cells
    */
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

  /**
    * METHOD parseData
    * Splits columns for each row into a list
    * @param lines the lines to split
    * @return a list of all rows, each split into a list of columns
    */
  def parseData(lines: List[String]): List[List[String]] = {
    val regexStr = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"
    fixCells(lines).map(_.split(regexStr, -1).map(_.trim).toList)
  }

  /**
    * METHOD parseFile
    * Combines readData and parseData
    * @param fileName qualified path to the file
    * @return a list of all rows, each split into a list of columns
    */
  def parseFile(fileName: String): List[List[String]] = {
    val lines = readData(fileName)
    parseData(lines)
  }

}
