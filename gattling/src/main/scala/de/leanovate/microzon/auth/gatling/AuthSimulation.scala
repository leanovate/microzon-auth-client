package de.leanovate.microzon.auth.gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class AuthSimulation extends Simulation {
  val httpConf = http
    .baseURL("http://localhost:8080")

  val scn = scenario("Login / get token / logout")
      .repeat(100) {
        exec(
          http("login").post("/v1/tokens").body(StringBody("{}")).check(
            status.is(201),
            jsonPath("$.raw").ofType[String].saveAs("authorization")
          )
        )
          .repeat(5) {
            exec(
              http("get token").get("/v1/tokens/myself").header("Authorization", "Bearer ${authorization}").check(
                status.is(200)
              )
            )
          }
          .exec(
            http("logout").delete("/v1/tokens/myself").header("Authorization", "Bearer ${authorization}").check(
              status.is(204)
            )
          )
          .pause(2.second)
          .exec(
            http("check token invalid").get("/v1/tokens/myself").header("Authorization", "Bearer ${authorization}").check(
              status.is(401)
            )
          )
      }


  setUp(
    scn.inject(atOnceUsers(10), rampUsers(400).over(2.minutes))
  ).protocols(httpConf)
}
