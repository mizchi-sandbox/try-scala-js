package example

import scala.scalajs.js
import js.Dynamic.{ global => g }

object ScalaJSExample {
  def main(): Unit = {
    g.window.onload = () => g.console.log("foo")
  }
}
