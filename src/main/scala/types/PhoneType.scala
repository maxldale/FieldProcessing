package types

import scala.util.matching.Regex
object PhoneType extends FieldType {
  override type T = String

  // Regexes for parts of a name [Change here if format changes]
  private val countryCodePluses: String = """\+{0,2}"""
  private val countryCodeRaw: String = """\d{1,3}"""
  private val areaCode: String = """\d{3}"""
  private val midThree: String = """\d{3}"""
  private val lastFour: String = """\d{4}"""
  private val extensionRaw: String = """\d+"""
  private val connector: String = """[-.()\/* ]*"""
  // End of Regexes for parts of a name

  // Combining components
  private def countryCode(rawStr: String): String = raw"(?:$countryCodePluses$rawStr)?"
  private def extension(rawStr: String): String = raw"(?:(?:x|ext)[:]?[ ]*$rawStr)?"

  private def wCon(input: String, last: Boolean = false): String = {
    if(last) raw"$input"
    else raw"$input$connector"
  }

  private val fullNumber: String = raw"(${wCon(countryCode(countryCodeRaw))}${wCon(areaCode)}${wCon(midThree)}${wCon(lastFour)}${wCon(extension(extensionRaw), true)})"
  private val compiledPattern: Regex = fullNumber.r.unanchored

  override def regex: Regex = compiledPattern

  private def mkGrp(input: String): String = raw"($input)"
  private def mkGrpWCon(input: String, last: Boolean = false): String = wCon(mkGrp(input), last)

  private val fullNumberGroups: String = raw"${wCon(countryCode(mkGrp(countryCodeRaw)))}${mkGrpWCon(areaCode)}${mkGrpWCon(midThree)}${mkGrpWCon(lastFour)}${wCon(extension(mkGrp(extensionRaw)), true)}"
  private val compiledGroupPattern: Regex = fullNumberGroups.r.unanchored

  override def clean(input: String): T = {
    val groupRegex = compiledGroupPattern
    input match {
      case groupRegex(null, areC, midT, lasF, null) => s"($areC)$midT-$lasF"
      case groupRegex(null, areC, midT, lasF, ext) => s"($areC)$midT-$lasF(ext:$ext)"
      case groupRegex(conC, areC, midT, lasF, null) => s"(+$conC)($areC)$midT-$lasF"
      case groupRegex(conC, areC, midT, lasF, ext) => s"(+$conC)($areC)$midT-$lasF(ext:$ext)"
      case _ => input
    }
  }
}
