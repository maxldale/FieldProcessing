package extraction

object SalesContactInfo {

  def apply(data: String): SalesContactInfo = new SalesContactInfo(data)

  def attemptConversion(params: List[String]): Option[SalesContactInfo] = {
    params match {
      case param :: Nil => Some(SalesContactInfo(param))
      case _ => None
    }
  }
}

class SalesContactInfo(data: String) {
  override def toString: String = data
}
