package extraction

import types.{EmailType, NameType, PhoneType}


object ARSalesContactInfo {

  /**
    * METHOD apply
    * Simple instantiation of a new SalesContactInfo
    * @param data a String that contains the pertinent sales contact info
    * @return a new  instance of SalesContactInfo
    */
  def apply(data: String): ARSalesContactInfo = {
    val addressOpt: Option[String] = None
    val phoneNumberOpt: Option[String] = PhoneType.extractFrom(data)
    val emailOpt: Option[String] = EmailType.extractFrom(data)
    val nameOpt: Option[String] = NameType.extractFrom(data)
    new ARSalesContactInfo(data, nameOpt, emailOpt, phoneNumberOpt, addressOpt)
  }

  /**
    * METHOD attemptConversion
    * Takes a list (should contain one element) to convert
    * @param params a List containing a String or Strings
    * @return an Option containing a SalesContactInfo on successful conversion
    */
  def attemptConversion(params: List[String]): Option[ARSalesContactInfo] = {
    params match {
      case data :: Nil => Some(ARSalesContactInfo(data))
      case _ => None
    }
  }
}

class ARSalesContactInfo(val raw: String,
                         val name: Option[String] = None,
                         val email: Option[String] = None,
                         val phoneNumber: Option[String] = None,
                         val address: Option[String] = None) extends Info {
  override val fields: Map[String, Option[String]] = Map(
    "name" -> name,
    "email" -> email,
    "phone_number" -> phoneNumber,
    "address" -> address
  )
}
