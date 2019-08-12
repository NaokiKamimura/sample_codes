// play2.6
// MailAddress VO
case class MailAddress(value: String){
  require(...)
}

trait MailAddressFormatter {
  implicit object MailAddressFormat extends Formatter[MailAddress] {

    override def bind(key: String, data: Map[String, String]): Either[Seq[FormError], MailAddress] = {
      // any format
      parsing(MailAddress, "error.email", Nil)(key, data)
    }

    override def unbind(key: String, mailAddress: MailAddress): Map[String, String] = {
      Map(key -> mailAddress.value)
    }
  }
}

// play.api.data.Form
case class HogeForm(
  email: MailAddress
)

object HogeForm extends MailAddressFormatter {
  val form = Form(
    mapping(
      "email" -> of[MailAddress].verifying(m => ...)
    )
  )(HogeForm.apply)(HogeForm.unapply)
}
