package extraction

object RawDataFormatter {

  def applySchema[A](rawData: List[List[String]], converter: List[String] => Option[A]): List[Either[List[String], A]] = {
    rawData.map{
      list => converter(list) match {
        case Some(convertedInstance) => Right(convertedInstance)
        case None => Left(list)
      }
    }
  }
}
