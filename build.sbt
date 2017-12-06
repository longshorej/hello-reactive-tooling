organization in ThisBuild := "com.lightbend"
version in ThisBuild := "0.0.1"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

lazy val `hello-reactive-tooling` = (project in file("."))
  .aggregate(
    frontend,
    `simple-api`,
    `simple-impl`
  )

lazy val frontend = (project in file("frontend"))
  .enablePlugins(PlayScala, SbtReactiveAppPlugin)
  .settings(
    // This is required to configure Play's application loader
    libraryDependencies += guice
  )

lazy val `simple-api` = (project in file("simple-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `simple-impl` = (project in file("simple-impl"))
  .enablePlugins(LagomScala, SbtReactiveAppPlugin)
  .settings(
    // This is required to configure Play's application loader
    libraryDependencies += guice
  )
  .dependsOn(`simple-api`)
