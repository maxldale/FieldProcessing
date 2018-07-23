package extraction

import types.EmailType

object ARContactEmail {

  /**
    * METHOD apply
    * Simple instantiation of a new SalesContactInfo
    * @param data a String that contains the pertinent sales contact info
    * @return a new  instance of SalesContactInfo
    */
  def apply(data: String): ARContactEmail = {
    val email: Option[String] = EmailType.extractFrom(data)
    new ARContactEmail(data, email)
  }

  /**
    * METHOD attemptConversion
    * Takes a list (should contain one element) to convert
    * @param params a List containing a String or Strings
    * @return an Option containing a SalesContactInfo on successful conversion
    */
  def attemptConversion(params: List[String]): Option[ARContactEmail] = {
    params match {
      case data :: Nil => Some(ARContactEmail(data))
      case _ => None
    }
  }
}

class ARContactEmail(val raw: String,
val email: Option[String] = None) extends Info {

  override val fields: Map[String, Option[String]] = Map(
  "email" -> email
  )

}
