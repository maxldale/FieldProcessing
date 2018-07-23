package types

import scala.util.matching.Regex

/**
  * TRAIT FieldType
  * Generic field information
  */
trait FieldType {

  /**
    * TYPE T
    * Type of the field
    */
  type T

  /**
    * METHOD regex
    * @return the Regex to extract a field
    */
  def regex : Regex

  /**
    * METHOD clean
    * @param input the uncleaned version of the field
    * @return the cleaned version of the field
    */
  def clean(input: String): T

  /**
    * METHOD extractFrom
    * @param input raw text
    * @return an Option, possibly containing the cleaned version of the field
    */
  def extractFrom(input: String): Option[T] = {
    val fieldRegex = regex
    input match {
      case fieldRegex(field) => Some(clean(field))
      case _ => None
    }
  }

  def extractAllFrom(input: String): List[T] = {
    regex.findAllIn(input).toList.map(clean)
  }
}
