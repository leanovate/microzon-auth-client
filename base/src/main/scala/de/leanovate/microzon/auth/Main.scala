package de.leanovate.microzon.auth

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object Main extends App {
  val client = new AuthClient("http://localhost:8080")

  val loginToken = Await.result(client.login(), 5.seconds)

  println(loginToken)

  val myselfToken = Await.result(client.getMyself(loginToken.raw), 5.seconds)

  println(myselfToken)

  assert(loginToken == myselfToken)

  Await.result(client.logout(loginToken.raw), 5.seconds)

  Thread.sleep(2000)

  val checkLogout = client.getMyself(loginToken.raw)

  Await.ready(checkLogout, 5.seconds)

  assert(checkLogout.value.get.isFailure)

  println("All ok")
  sys.exit()
}
