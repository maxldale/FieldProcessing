package output

import java.io.{File, PrintWriter}

import ingest.ARRow

object CSVWriter {

  val outName = "ProcessedData.csv"
  lazy val outFile = new File(outName)
  lazy val writer = new PrintWriter(outFile)

  def writeARRow(arRow: ARRow): Unit = {
    writer.write(arRow.asCSVRow + "\n")
  }

  def writeARRows(rows: List[ARRow]): Unit = {
    rows.foreach(writeARRow)
  }

  def close: Unit = writer.close
}
