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
      "net.sf.jt400" % "jt400" % "6.7",
      "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here      
    )

}
            
