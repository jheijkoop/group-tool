package controllers

import org.scalatest.FunSpec
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Milliseconds, Span}
import org.scalatestplus.play.{OneServerPerSuite, WsScalaTestClient}
import play.api.http.Status

class ApplicationSpec
  extends FunSpec
  with OneServerPerSuite
  with ScalaFutures
  with WsScalaTestClient
{
  implicit val config = PatienceConfig(timeout = Span(400, Milliseconds))

  describe("Application") {
    it("should show a version") {
      whenReady(wsUrl("/myversion").get()) { response =>
        assert(response.status === Status.OK)
      }
    }
  }
}
