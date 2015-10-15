package controllers

import play.api.mvc.{Action, Controller}

object Main extends Controller {
  def index = Action {
    Ok(views.html.index())
  }
}