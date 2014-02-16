package main.scala.example.components
import main.scala.example._
import scalatags.all._

trait LayoutData extends Schema {
  var name: String = ???
}
object Layout {
  def create(): Vue[LayoutData] = ComponentLoader.createVue[LayoutData](
      html(
        div(
          h1("Hello, {{name}}"),
          p("This is my first paragraph")
        )
      ).toString)
}
