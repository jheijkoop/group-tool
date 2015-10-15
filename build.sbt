import java.text.SimpleDateFormat
import java.util.Date

import com.typesafe.sbt.SbtGit.GitKeys._

organization := "leipie"
version := "1.0.0"
scalaVersion := "2.11.7"
scalacOptions := Seq(
  "-encoding", "utf8",
  "-target:jvm-1.8",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-unchecked",
  "-deprecation",
  "-Xlog-reflective-calls",
  "-Xfatal-warnings",
  "-Ywarn-unused"
)

libraryDependencies ++= Seq(
  ws,
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalatestplus" %% "play" % "1.4.0-M3" % "test"
)

buildInfoOptions += BuildInfoOption.ToJson

lazy val root = Project("play-demo", file("."))
  .enablePlugins(PlayScala)
  .enablePlugins(BuildInfoPlugin)
  .settings(
    buildInfoPackage := "info",
    buildInfoKeys := Seq[BuildInfoKey](
      name,
      version,
      BuildInfoKey.map(gitHeadCommit) {
        case (key, value) => key -> value.getOrElse("-")
      },
      BuildInfoKey.action("buildTime") {
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date())
      }
    )
  )
