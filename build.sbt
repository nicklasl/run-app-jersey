name := "run-app-jersey-play"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

libraryDependencies += "com.google.code.gson" % "gson" % "2.2.1"


play.Project.playScalaSettings
