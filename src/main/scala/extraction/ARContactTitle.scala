package extraction

import types.TitleType

object ARContactTitle {

  /**
    * METHOD apply
    * Simple instantiation of a new SalesContactInfo
    * @param data a String that contains the pertinent sales contact info
    * @return a new  instance of SalesContactInfo
    */
  def apply(data: String): ARContactTitle = {
    val title: Option[String] = TitleType.extractFrom(data)
    new ARContactTitle(data, title)
  }

  /**
    * METHOD attemptConversion
    * Takes a list (should contain one element) to convert
    * @param params a List containing a String or Strings
    * @return an Option containing a SalesContactInfo on successful conversion
    */
  def attemptConversion(params: List[String]): Option[ARContactTitle] = {
    params match {
      case data :: Nil => Some(ARContactTitle(data))
      case _ => None
    }
  }
}

class ARContactTitle(val raw: String,
                     val title: Option[String] = None) extends Info {

  override val fields: Map[String, Option[String]] = Map(
    "title" -> title
  )

}