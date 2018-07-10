package types

import scala.util.matching.Regex

/**
  * OBJECT EmailType
  * Email type information
  */
object EmailType extends FieldType {
  type T = String

  // Regexes for parts of an email [Change here if format changes]
  private val localPart : String = """[0-9a-zA-Z](?:[0-9a-zA-Z-\+\._]*[0-9a-zA-Z])"""
  private val hostName: String = """[0-9a-zA-Z][0-9a-zA-Z-\.]+[0-9a-zA-Z]"""
  private val DNSLabel: String = """[a-zA-Z]{2,5}"""
  private val dot: String = """\."""
  private val at: String = """@"""
  private val wordBoundary: String = """\b"""
  //End of Regexes for parts of an email

  // Combining components
  private val domain: String = s"""$hostName$dot$DNSLabel"""
  private val fullEmail: String = s"""($wordBoundary$localPart$at$domain$wordBoundary)"""
  private val compiledPattern : Regex = fullEmail.r.unanchored

  override def regex: Regex = compiledPattern

  //TODO Implement a real cleaner
  override def clean(input: String): T = input

}
