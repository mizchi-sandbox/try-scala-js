package main.scala.example.lib

import scala.scalajs.js
import js.Dynamic.literal
import org.scalajs.jquery.jQuery
import js.Dynamic.{
newInstance,
global,
literal
}

object When {
  def wait[T](args: Deferred[T]*): Deferred[T] = {
    val deferreds = new js.Array[js.Dynamic]()
    for (d <- args) deferreds.push(d.deferred)
    val defer: js.Dynamic = global.jQuery.when.applyDynamic("apply")(null, deferreds)
    return deferredWith[T](defer)
  }

  def deferred[T](): Deferred[T] = new Deferred[T](global.jQuery.Deferred())

  def deferredWith[T](d: js.Dynamic): Deferred[T] = new Deferred[T](d)
}

class Deferred[T](var deferred: js.Dynamic) {
  def done(f: (T => _)) = deferred.done(f)

  def fail(f: js.Dynamic) = deferred.done(f)

  def then(f: (T => _)) = deferred.then(f)

  def resolve(t: T): Unit = {
    deferred.resolve(t.asInstanceOf[js.Any])
  }

  def reject(): Unit = deferred.reject()
}

