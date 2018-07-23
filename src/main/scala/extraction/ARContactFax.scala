package extraction

import types.PhoneType

object ARContactFax {

  /**
    * METHOD apply
    * Simple instantiation of a new SalesContactInfo
    * @param data a String that contains the pertinent sales contact info
    * @return a new  instance of SalesContactInfo
    */
  def apply(data: String): ARContactFax = {
    val fax: Option[String] = PhoneType.extractFrom(data)
    new ARContactFax(data, fax)
  }

  /**
    * METHOD attemptConversion
    * Takes a list (should contain one element) to convert
    * @param params a List containing a String or Strings
    * @return an Option containing a SalesContactInfo on successful conversion
    */
  def attemptConversion(params: List[String]): Option[ARContactFax] = {
    params match {
      case data :: Nil => Some(ARContactFax(data))
      case _ => None
    }
  }
}

class ARContactFax(val raw: String,
                   val fax: Option[String] = None) extends Info {

  override val fields: Map[String, Option[String]] = Map(
    "fax_number" -> fax
  )

}
