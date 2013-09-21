import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "sidekick"
    val appVersion      = "1.0"
    val appDependencies = Seq(
      javaCore,
      javaJdbc,
      javaEbean,
      // Database
      "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
      // IMB iSeries
      "net.sf.jt400" % "jt400" % "6.7",
      // JEE servers management using Cargo
      "org.codehaus.cargo" % "cargo-core-uberjar" % "1.4.4"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here      
    )

}
            
