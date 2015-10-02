name := "Spark Vararg Matching"

/** Common settings for all projects **/
lazy val settings = Seq(
  organization := "nl.anchormen",
  isSnapshot := true,
  version := "0.0.1-SNAPSHOT",
  crossPaths := false,
  scalaVersion := "2.11.7"
)

lazy val dependencies = Seq(
  libraryDependencies += "log4j" % "log4j" % "1.2.17",
  libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.12",
  libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  libraryDependencies += "org.mockito" % "mockito-all" % "2.0.2-beta" % "test",
  libraryDependencies += "org.apache.spark" %% "spark-core" % "1.4.1"
)

lazy val root = (project in file("."))
	.settings(settings)
	.settings(dependencies)
						