package de.leanovate.microzon.auth.gatling

import io.gatling.app.Gatling
import io.gatling.core.scenario.Simulation

object Main extends App {
  val gatlingArgs = Array[String]("--mute")

  Gatling.fromArgs(gatlingArgs, Some(classOf[AuthSimulation].asInstanceOf[Class[Simulation]]))
}
