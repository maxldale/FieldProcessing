package ingest

import extraction._

object ARRow {

  def apply(email: String, fax: String, name: String, phone: String, title: String, salesContact: String): ARRow = {
    val emailField = ARContactEmail.attemptConversion(List(email))
    val faxField = ARContactFax.attemptConversion(List(fax))
    val nameField = ARContactName.attemptConversion(List(name))
    val phoneField = ARContactPhone.attemptConversion(List(phone))
    val titleField = ARContactTitle.attemptConversion(List(title))
    val salesContactField = ARSalesContactInfo.attemptConversion(List(salesContact))
    new ARRow(List(email, fax, name, phone, title, salesContact), emailField, faxField, nameField, phoneField, titleField, salesContactField)
  }

  def attemptConversion(params: List[String]): Option[ARRow] = {
    params match {
      case email :: fax :: name :: phone :: title :: salesContact :: Nil => Some(ARRow(email, fax, name, phone, title, salesContact))
      case _ => None
    }
  }
}

class ARRow (val rawList: List[String],
             val email: Option[ARContactEmail],
             val fax: Option[ARContactFax],
             val name: Option[ARContactName],
             val phone: Option[ARContactPhone],
             val title: Option[ARContactTitle],
             val salesContact: Option[ARSalesContactInfo]) {

  val fields: Map[String, Option[Info]] = Map(
    "csv_email" -> email,
    "csv_fax" -> fax,
    "csv_name" -> name,
    "csv_phone_number" -> phone,
    "csv_title" -> title,
    "csv_sales_contact" -> salesContact
  )

  private def formattedFields(info: Info, indent: String): String = {
    info.fields.foldLeft(""){
      (accStr: String, map: (String, Option[String])) => {
        val fieldName = map._1
        val strVer = map._2 match {
          case Some(value) => value
          case None => "[Not Present]"
        }
        accStr + s"$indent|- $fieldName -> " + strVer + "\n"
      }
    }
  }

  private val mapToStringOp = (accStr: String, map: (String, Option[Info])) => {
    val fieldName = map._1
    val indent = "\t"
    val strVer = map._2 match {
      case Some(info) => "\n" + formattedFields(info, indent + indent).reverse.replaceFirst("\\n", "").reverse
      case None => "[Not Present]"
    }
    accStr + s"$indent|- $fieldName -> " + strVer + "\n"
  }

  override def toString: String = fields.foldLeft("")(mapToStringOp)
}
