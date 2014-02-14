// Turn this project into a Scala.js project by importing these settings
scalaJSSettings

name := "Example"

version := "0.1-SNAPSHOT"

libraryDependencies ++= Seq(
//    "org.scala-lang.modules.scalajs" %% "scalajs-jasmine-test-framework" % scalaJSVersion % "test",
    "org.scala-lang.modules.scalajs" %% "scalajs-dom" % "0.1-SNAPSHOT",
    "com.scalatags" % "scalatags_2.10" % "0.2.3-JS",
    "org.scala-lang.modules.scalajs" %% "scalajs-jquery" % "0.1-SNAPSHOT"
//    "com.scalarx" % "scalarx_2.10" % "0.2.1-JS"
//    "com.lihaoyi.utest" % "utest_2.10" % "0.1.1",
)

// Specify additional .js file to be passed to package-js and optimize-js
unmanagedSources in (Compile, ScalaJSKeys.packageJS) +=
    baseDirectory.value / "js" / "startup.js"

