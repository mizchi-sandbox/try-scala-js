package main.scala.example.game.battle

case class Id[T](var id: Int) {
  override def toString = "id:" + this.id.toString

  def ==(other: Id[T]): Boolean = toString == other.toString
}

object Id {
  private var _v: Int = 0

  def apply[T](): Id[T] = {
    _v += 1
    return new Id[T](_v)
  }
}

trait Entity[T] {
  val id = Id[T]
}

abstract class Job extends Entity[Job]

class Status(
              val str: Int,
              val int: Int,
              val dex: Int
              )

object Job {

  case object Knight extends Job()

  case object Rogue extends Job()

  case object Wizard extends Job()

}

trait Action extends Entity[Action]{
  def exec(): ActionReport
}

class Attack extends Action {
  override def exec(): ActionReport = new ActionReport("attack")
}


class ActionReport(val message: String)

class Parameter(var current:Int, val max:Int) {
  def incr() = this.current++
}

class Actor(
             val name: String,
             val status: Status,
             val job: Job
             ) extends Entity[Actor] {
  val wt = new Parameter(0, 30)
  val hp = new Parameter(50, 50)

  var skill:Attack = new Attack

  def update(session: BattleSession):Option[ActionReport] =
    if (wt < max_wt) {
      println(name, "chaging")
      wt.incr
      return None
    } else {
      execAction
      wt.current = 0
      val report:ActionReport = skill.exec()
      return Some(report)
    }

  def execAction = println(name, ":attack")
}

class Party(
             val actors: List[Actor]
             ) extends Entity[Party]

// Battle Session
class BattleEvent extends Entity[BattleEvent]

class BattleReport(var events: List[BattleEvent]) extends Entity[BattleReport]

class BattleSession(
                     val parties: List[Party]
                     ) extends Entity[BattleSession] {
  def process: BattleReport = {
    for (party <- parties)
      for (actor <- party.actors)
        actor.update(this)
    return new BattleReport(events = List(new BattleEvent))
  }
}


