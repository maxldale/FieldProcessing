package extraction

import types.{EmailType, NameType, PhoneType}


object SalesContactInfo {

  /**
    * METHOD apply
    * Simple instantiation of a new SalesContactInfo
    * @param data a String that contains the pertinent sales contact info
    * @return a new  instance of SalesContactInfo
    */
  def apply(data: String): SalesContactInfo = {
    val addressOpt: Option[String] = None
    val phoneNumberOpt: Option[String] = PhoneType.extractFrom(data)
    val emailOpt: Option[String] = EmailType.extractFrom(data)
    val nameOpt: Option[String] = NameType.extractFrom(data)
    new SalesContactInfo(data, name = nameOpt, email = emailOpt, phoneNumber = phoneNumberOpt, address = addressOpt)
  }

  /**
    * METHOD attemptConversion
    * Takes a list (should contain one element) to convert
    * @param params a List containing a String or Strings
    * @return an Option containing a SalesContactInfo on successful conversion
    */
  def attemptConversion(params: List[String]): Option[SalesContactInfo] = {
    params match {
      case param :: Nil => Some(SalesContactInfo(param))
      case _ => None
    }
  }
}

class SalesContactInfo(val raw: String,
                       val name: Option[String] = None,
                       val email: Option[String] = None,
                       val phoneNumber: Option[String] = None,
                       val address: Option[String] = None) {

  override def toString: String = s"$raw\n{$name, $email, $phoneNumber, $address}"
}
