package types

import scala.util.matching.Regex
object TitleType extends FieldType {
  override type T = String

  // Regexes for title [Change here if format changes]
  private val everything: String = """[^"]*"""
  private val quoteOpt: String = """["]?"""
  // End of Regex for title

  // Combining components
  private val fullTitle: String = raw"$quoteOpt($everything)$quoteOpt"
  private val compiledPattern: Regex = fullTitle.r.unanchored

  override def regex: Regex = compiledPattern

  //TODO Implement a real cleaner
  override def clean(input: String): T = input.trim
}
