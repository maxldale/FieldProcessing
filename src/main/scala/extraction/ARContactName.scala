package extraction

import types.NameType


object ARContactName {

  /**
    * METHOD apply
    * Simple instantiation of a new SalesContactInfo
    * @param data a String that contains the pertinent sales contact info
    * @return a new  instance of SalesContactInfo
    */
  def apply(data: String): ARContactName = {
    val name: Option[String] = NameType.extractFrom(data)
    new ARContactName(data, name)
  }

  /**
    * METHOD attemptConversion
    * Takes a list (should contain one element) to convert
    * @param params a List containing a String or Strings
    * @return an Option containing a SalesContactInfo on successful conversion
    */
  def attemptConversion(params: List[String]): Option[ARContactName] = {
    params match {
      case data :: Nil => Some(ARContactName(data))
      case _ => None
    }
  }
}

class ARContactName(val raw: String,
                     val name: Option[String] = None) extends Info {

  override val fields: Map[String, Option[String]] = Map(
    "name" -> name
  )

}

