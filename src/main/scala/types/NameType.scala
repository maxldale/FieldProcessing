package types

import scala.util.matching.Regex
object NameType extends FieldType {
  override type T = String

  // Regexes for parts of a name [Change here if format changes]
  private val prefixOrSuffix: String = """(?:\b[a-zA-Z]{2,6}\b\.?)?"""
  private val initial: String = """(?:\b[a-zA-Z]\b\.?)?"""
  private val name: String = """(?:\b[a-zA-Z]+\b)??"""
  private val space: String = """[ ]*"""
  // End of Regexes for parts of a name

  // Combining components
  private val fullName: String = raw"($prefixOrSuffix$space$name$space$initial$space$name$space$initial$space$prefixOrSuffix)"
  private val compiledPattern: Regex = fullName.r.unanchored

  override def regex: Regex = compiledPattern

  //TODO Implement a real cleaner
  override def clean(input: String): T = input
}
