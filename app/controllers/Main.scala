package controllers

import play.api.mvc.{AnyContent, Request, Action, Controller}

import scala.collection.mutable

object Main extends Controller {
  var meetups = Map.empty[String, String]
  def index = Action {
    Ok(views.html.index())
  }

  def create = Action { (request: Request[AnyContent]) =>
    if (request.method == "POST") {
      val form = request.body.asFormUrlEncoded.get
      val name = form("name").head
      val description = form("description").head

      meetups = meetups + (name -> description)

      println(meetups)

      Redirect("/create")
    } else {
      Ok(views.html.create())
    }

  }
}