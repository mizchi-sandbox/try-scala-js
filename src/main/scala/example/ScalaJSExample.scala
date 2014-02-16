package example
import scala.scalajs.js
import org.scalajs.jquery.jQuery
import main.scala.example.components.Layout

object ScalaJSExample {
  def initialize() = {
    val layout = Layout.create()
    layout.$data.name = new js.Date().getTime().toString
    layout.$appendTo("body")
    println("foo")
  }

  def main(): Unit = {
    jQuery("body").ready(() => initialize())
  }
}
