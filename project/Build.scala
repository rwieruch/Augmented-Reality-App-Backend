import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "seusBackend"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    "net.sf.flexjson" % "flexjson" % "2.1",
    javaEbean, // Use this for heroku deployment
    //"postgresql" % "postgresql" % "8.4-702.jdbc4" // Use this for heroku deployment
    "postgresql" % "postgresql" % "9.1-901.jdbc4"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}