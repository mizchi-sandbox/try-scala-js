package example
import scala.scalajs.js
import org.scalajs.jquery.jQuery
import main.scala.example.components.Layout
import js.Dynamic.{
  newInstance,
  global,
  literal
}

object When {
  def wait[T](args: Deferred[T] *): Deferred[T] = {
    val deferreds = new js.Array[js.Dynamic]()
    for (d <- args) deferreds.push(d.deferred)
    val defer: js.Dynamic = global.jQuery.when.applyDynamic("apply")(null,deferreds)
    return deferredWith[T](defer)
  }
  def deferred[T]():Deferred[T] = new Deferred[T](global.jQuery.Deferred())
  def deferredWith[T](d: js.Dynamic): Deferred[T] = new Deferred[T](d)
}

class Deferred[T](var deferred: js.Dynamic){
  def done(f: ( T => _)) = deferred.done(f)
  def fail(f: js.Dynamic) = deferred.done(f)
  def then(f: (T => _)) = deferred.then(f)
  def resolve(t: T): Unit = {deferred.resolve(t.asInstanceOf[js.Any])}
  def reject(): Unit = deferred.reject()
}

class Point(var x:Int, var y: Int)

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
        new Actor (
          name = "mizchi",
          status = new Status(10,10,10),
          job = Job.Knight),
        new Actor (
          name = "bob",
          status = new Status(10,11,11),
          job = Job.Rogue),
        new Actor (
          name = "Chik",
          status = new Status(10,11,16),
          job = Job.Wizard)
        )
    )

    val enemies = new Party(
      actors = List(
        new Actor (
          name = "goblin1",
          status = new Status(5,4,6),
          job = Job.Rogue),
        new Actor (
          name = "globin2",
          status = new Status(5,5,5),
          job = Job.Rogue)
        )
    )

    var session = new BattleSession(List(players, enemies))
    session.process

    println(
      session
    )

  }

  def main(): Unit = {
    jQuery("body").ready(() => initialize())
    startBattle

    val d = When.deferred[Point]
    val callback = (s: Point) => println(s.x, s.y)
    d.done( callback )
    val p = new Point(3,5)
    global.setTimeout(() => {
      d.resolve(p)
    }, 1000)

    val d1 = When.deferred[Int]
    d1.done( (n:Int) => {println("foo")})
    d1.resolve(3)

//    val deferredList = List(
//      global.jQuery.Deferred().resolve(3),
//      global.jQuery.Deferred().resolve(4)
//    )
//
//    val deferreds = new js.Array[js.Dynamic]()
//    for (d <- deferredList) deferreds.push(d)
//    val defer: js.Dynamic = global.jQuery.when.apply(null, deferreds)
//    defer.done((p1:Int, p2: Int) => println("done", p1, p2)) //not fired

  }
}


case class Id[T](var id:Int) {
  override def toString = "id:"+this.id.toString
  def == (other: Id[T]) : Boolean = toString == other.toString
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

abstract class Job extends Entity[Job](
)

class Status(
   val str:Int,
   val int:Int,
   val dex:Int
)

object Job {
  case object Knight extends Job()
  case object Rogue  extends Job()
  case object Wizard extends Job()
}

class Actor (
              val name: String,
              val status: Status,
              val job: Job
              ) extends Entity[Actor]{
  val max_wt = 30
  var wt = 0
  val max_hp = 30
  var hp = 30

  def update(session: BattleSession) =
    if(wt < max_wt){
      println(name, "chaging")
      wt+=1
    }
    else {
      execAction
      wt = 0
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
                     ) extends Entity[BattleSession]
{
  def process: BattleReport = {
    for (party <- parties)
      for(actor <- party.actors)
        actor.update(this)
    return new BattleReport(events = List(new BattleEvent))
  }
}

