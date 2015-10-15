package controllers

import javax.inject.Inject

import play.api.libs.ws.{WSResponse, WSClient}
import play.api.mvc.{Action, Controller}

class Proxy @Inject() (ws: WSClient) extends Controller {
  import play.api.libs.concurrent.Execution.Implicits.defaultContext

  def absoluteEndpoint(endpoint: String) = s"http://localhost:5000/api$endpoint"

  def asJsonResponse(response: WSResponse) = Status(response.status)(response.body).as("application/json")

  def forwardGet(ignored: String) = Action.async { request =>
    ws.url(absoluteEndpoint(request.path))
      .execute(request.method)
      .map(asJsonResponse)
  }

  def forward(ignored: String) = Action.async(parse.json) { request =>
    ws.url(absoluteEndpoint(request.path))
      .withBody(request.body)
      .execute(request.method)
      .map(asJsonResponse)
  }
}