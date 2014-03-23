package controllers

import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("All your GPX belong to us!"))
  }

  def upload = Action {
    Ok(views.html.upload())
  }

}