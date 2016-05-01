name := "akka-stream"

organization := "e/y"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions := Seq(
  "-deprecation",
  "-encoding", "UTF-8", // yes, this is 2 args
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Ywarn-unused-import"
)

libraryDependencies ++= {
  lazy val akkaHttpVersion = "1.0"
  Seq(
    "com.typesafe.akka" %% "akka-stream-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-core-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaHttpVersion,
    "org.twitter4j" % "twitter4j-stream" % "4.0.4"
  )
}

assemblyJarName in assembly := s"${name.value}-${version.value}.jar"
