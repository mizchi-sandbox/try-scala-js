package example

import scala.scalajs.js
import org.scalajs.jquery.jQuery
import main.scala.example.components.Layout
import js.Dynamic.{
global,
}

import main.scala.example.game.battle._

object ScalaJSExample {
  def initialize() = {
    val layout = Layout.create()
    layout.$data.name = new js.Date().getTime().toString
    layout.$appendTo("body")
    println("foo")
  }

  def startBattle = {
    val players = new Party(
      actors = List(
        new Actor(
          name = "mizchi",
          status = new Status(10, 10, 10),
          job = Job.Knight),
        new Actor(
          name = "bob",
          status = new Status(10, 11, 11),
          job = Job.Rogue),
        new Actor(
          name = "Chik",
          status = new Status(10, 11, 16),
          job = Job.Wizard)
      )
    )

    val enemies = new Party(
      actors = List(
        new Actor(
          name = "goblin1",
          status = new Status(5, 4, 6),
          job = Job.Rogue),
        new Actor(
          name = "globin2",
          status = new Status(5, 5, 5),
          job = Job.Rogue)
      )
    )

    val session = new BattleSession(List(players, enemies))
    session.process

    println(
      session
    )
  }

  def main(): Unit = {
    jQuery("body").ready(() => initialize())
    startBattle

  }
}


