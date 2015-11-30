import sbt.Keys._
import sbt._

object Common {
  val settings = Seq(

    organization := "de.leanovate.swaggercheck",

    scalaVersion := "2.11.7",

    scalacOptions := Seq("-deprecation", "-feature"),

    fork in run := true,

    publishMavenStyle := true
  )
}