package example

import scala.scalajs.js
import scalatags.all._
import js.Dynamic.{
  newInstance,
  global => g,
  literal => O
}

object ComponentLoader {
  val component = g.Vue.extend(
    O()
  )
  def createVue[T](str:String): Vue[T] =
    newInstance(component)(
      O(template = str)
    ).asInstanceOf[Vue[T]]
}

trait Vue[T] extends js.Object{
  def el: js.Any = ???
  def $el: js.Any = ???
  def $data: T = ???
  def $appendTo(s: String): js.Any = ???
  def $methods: js.Any = ???
}

trait Schema extends js.Object{}
trait Produce extends Schema {
  var name: String = ???
}

object ScalaJSExample {
  def createVue[T](str:String): Vue[T] =
    newInstance(g.Vue)(
      O(template = str)
    ).asInstanceOf[Vue[T]]

  def main(): Unit = {
    val vue : Vue[Produce] = ComponentLoader.createVue[Produce](
      html(
        div(
          h1("Hello, {{name}}"),
          p("This is my first paragraph")
        )).toString
    )
    vue.$data.name = "fooo" + new js.Date().toString

    g.window.onload = () => {
      g.console.log("foo")
      vue.$appendTo("body")
    }
  }
}
