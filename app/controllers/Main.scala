package controllers

import models.Meetups
import play.api.mvc.{Action, AnyContent, Controller, Request}

object Main extends Controller {
  def index = Action {
    Ok(views.html.index())
  }

  def create = Action { (request: Request[AnyContent]) =>
    if (request.method == "POST") {
      val form = request.body.asFormUrlEncoded.get
      val name = form("name").head
      val description = form("description").head

      Meetups.add(name -> description)

      println(Meetups.list)

      Redirect("/view")
    } else {
      Ok(views.html.create())
    }

  }

  def list = Action {
    Ok(views.html.meetups(Meetups.list))
  }

  def view(name: String) = Action {
    Meetups.list.get(name)
      .map(description => Ok(views.html.view(name, description)))
      .getOrElse(Redirect("/create"))
  }
}