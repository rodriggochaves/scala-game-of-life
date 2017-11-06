import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.102-R11",
    libraryDependencies += "org.springframework.boot" % "spring-boot-starter-web" % "1.5.8.RELEASE"
  )
