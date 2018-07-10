package types

import scala.util.matching.Regex
object NameType extends FieldType {
  override type T = String

  // Regexes for parts of a name [Change here if format changes]
  private val prefixOrSuffix: String = """\b[a-zA-Z]{2,6}\b\.?"""
  private val initial: String = """\b[a-zA-Z]\b\.?"""
  private val name: String = """\b[a-zA-Z]+\b"""
  private val space: String = """[ ]*"""
  // End of Regexes for parts of a name

  // Combining components
  private val prefixOpt: String = raw"(?:$prefixOrSuffix$space)?"
  private val suffixOpt: String = raw"(?:$prefixOrSuffix)?"
  private val nameOpt: String = raw"(?:$name$space)?"
  private val nameOrInitialOpt: String = raw"(?:(?:$initial|$name)$space)?"
  private val fullName: String = raw"($prefixOpt$nameOrInitialOpt$nameOrInitialOpt$nameOrInitialOpt$suffixOpt)"
  private val compiledPattern: Regex = fullName.r.unanchored

  override def regex: Regex = compiledPattern

  //TODO Implement a real cleaner
  override def clean(input: String): T = input
}
