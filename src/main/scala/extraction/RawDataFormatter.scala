package extraction

object RawDataFormatter {

  /**
    * METHOD applySchema
    * Take raw data and attempt to cast it
    * @param rawData A list of params (also a list) to try and convert
    * @param converter A function that takes the raw elements (params), returning a desired type
    * @tparam A Identifies the required final type upon successful conversion
    * @return A List containing the successes and failures as Eithers
    */
  def applySchema[A](rawData: List[List[String]], converter: List[String] => Option[A]): List[Either[List[String], A]] = {
    rawData.map{
      list => converter(list) match {
        case Some(convertedInstance) => Right(convertedInstance)
        case None => Left(list)
      }
    }
  }
}
