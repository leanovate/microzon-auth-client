package de.leanovate.microzon.auth.gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class AuthSimulation extends Simulation {
  println("Hurra")
  val httpConf = http
    .baseURL("http://localhost:8080")

  val scn = scenario("Login")
    .exec(
      http("login").post("/v1/tokens").body(StringBody("{}"))
    )

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)
}
