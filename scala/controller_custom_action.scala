// play 2.6
class AuthCheckAction @Inject() (parser: BodyParsers.Default)(implicit ec: ExecutionContext) extends ActionBuilderImpl(parser) {
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    if(request.session.get("id").isEmpty) {
      Future.successful(Redirect(application.controllers.routes.Controller.index())
    } else {
      block(request)
    }
  }
}

// controllers

class HogeController @Inject()(withAuth: AuthCheckAction) ... {
  def index = withAuth { implicit request =>
    ...
  }
}
