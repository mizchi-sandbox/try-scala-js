package main.scala.example
import scala.scalajs.js
import scalatags.all._
import js.Dynamic.{
  newInstance,
  global,
  literal
}

trait Schema extends js.Object{}

object VueLoader {
  // itniailize with template string and interface
  def createVue[T](str:String): Vue[T] =
    newInstance(global.Vue)(
      literal(template = str)
    ).asInstanceOf[Vue[T]]

  // initialize with options
  def createVue[T](options: js.Object): Vue[T] =
    newInstance(global.Vue)(options).asInstanceOf[Vue[T]]
}

trait Vue[T] extends js.Object{
  def el: js.Any = ???
  def $el: js.Any = ???
  def $: js.Any = ???
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

