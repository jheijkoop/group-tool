package controllers

import play.api.libs.json.Json
import play.api.mvc._

object Application extends Controller {
  def version = Action {
    Ok(Json.parse(info.BuildInfo.toJson))
  }
}