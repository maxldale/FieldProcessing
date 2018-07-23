package extraction

import types.PhoneType

object ARContactPhone {

  /**
    * METHOD apply
    * Simple instantiation of a new SalesContactInfo
    * @param data a String that contains the pertinent sales contact info
    * @return a new  instance of SalesContactInfo
    */
  def apply(data: String): ARContactPhone = {
    val email: Option[String] = PhoneType.extractFrom(data)
    new ARContactPhone(data, email)
  }

  /**
    * METHOD attemptConversion
    * Takes a list (should contain one element) to convert
    * @param params a List containing a String or Strings
    * @return an Option containing a SalesContactInfo on successful conversion
    */
  def attemptConversion(params: List[String]): Option[ARContactPhone] = {
    params match {
      case data :: Nil => Some(ARContactPhone(data))
      case _ => None
    }
  }
}

class ARContactPhone(val raw: String,
                     val phone: Option[String] = None) extends Info {

  override val fields: Map[String, Option[String]] = Map(
    "phone_number" -> phone
  )

}
