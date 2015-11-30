import sbt._

name := "microzon-auth-client"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).aggregate(base, gattling)

lazy val base = project.in(file("base"))

lazy val gattling = project.in(file("gattling"))

Common.settings
