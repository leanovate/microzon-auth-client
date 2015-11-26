package de.leanovate.microzon.auth

import dispatch._
import org.json4s.DefaultFormats

import scala.concurrent.ExecutionContext.Implicits.global

class AuthClient(baseUrl: String) {
  implicit val formats = DefaultFormats

  val base = url(baseUrl)

  def login(): Future[TokenInfo] = {
    val req: Req = (base / "v1" / "tokens").POST.setContentType("application/json", "UTF-8")

    Http(req << "{}" OK as.json4s.Json).map(_.extract[TokenInfo])
  }

  def getMyself(rawToken: String): Future[TokenInfo] = {
    val req: Req = (base / "v1" / "tokens" / "myself").setHeader("Authorization", s"Bearer $rawToken")

    Http(req OK as.json4s.Json).map(_.extract[TokenInfo])
  }

  def logout(rawToken :String): Future[String] = {
    val req: Req = (base / "v1" / "tokens" / "myself").DELETE.setHeader("Authorization", s"Bearer $rawToken")

    Http(req OK as.String)

  }
}
