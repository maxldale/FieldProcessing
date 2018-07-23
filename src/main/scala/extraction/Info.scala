package extraction

trait Info {

  private val whiteSpace: String = raw"[^a-zA-Z]*"

  val raw: String

  val fields: Map[String, Option[String]]

  lazy val notMatchedPortion: List[String] = filterOutMatched

  private def escapeField(str: String): String = {
    str.replaceAll("\\+", "\\\\+")
  }

  private def filterOutField(fieldOption: Option[String], rawStrings: List[String]): List[String] = {
    fieldOption match {
      case Some(field) => rawStrings.flatMap(_.split(escapeField(field)).toList)
      case None => rawStrings
    }
  }

  private val filterOp = (filteredRaw: List[String], map: (String, Option[String])) => filterOutField(map._2, filteredRaw)

  private def filterOutMatched: List[String] = {
    fields.foldLeft(List(raw))(filterOp).map(_.trim).filterNot(_.matches(whiteSpace))
  }

  private val mapToStringOp = (accStr: String, map: (String, Option[String])) => {
    val fieldName = map._1
    val strVer = map._2 match {
      case Some(value) => s"""$fieldName -> "$value""""
      case None => s"""$fieldName -> [Not Present]"""
    }
    accStr + strVer + "\n"
  }
  override def toString: String = fields.foldLeft("")(mapToStringOp).reverse.replaceFirst("\\n", "").reverse
}
