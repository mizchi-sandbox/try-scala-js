package main.scala.example

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
  def $methods: js.Object = ???

  def $appendTo(s: js.Any): js.Any = ???
  def $before(s: js.Any): js.Any = ???
  def $after(s: js.Any): js.Any = ???
  def $remove(): js.Any = ???
  def $destroy(): js.Any = ???

  def $watch(keypath:String, callback: js.Function):  js.Any = ???
  def $unwatch(keypath:String, callback: js.Function): js.Any = ???
  def $dispatch(event: String, args: js.Any *): js.Any = ???
  def $broadcast(event: String, args: js.Any *): js.Any = ???
  def $emit(event: String, args: js.Any *): js.Any = ???
  def $on(event: String, callback: js.Function): js.Any = ???
  def $once(event: String, callback: js.Function): js.Any = ???
  def $off(): js.Any = ???
  def $off(event: String): js.Any = ???
  def $off(event: String, callback: js.Function): js.Any = ???
}
trait Schema extends js.Object{}
