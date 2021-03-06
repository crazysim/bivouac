package org.fooblahblah.bivouac

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object TestApp {
  val client = Bivouac()

  def main(args: Array[String]) {
    import client._

    def printRooms: Future[Unit] =
      rooms map { rooms =>
        rooms foreach { r =>
          println(r.id + " " + r.name)
        }
      }

    def printMe: Future[Unit] =
      me map (println)

    def printRoom(roomId: Int) =
      room(roomId) map (println)

    def printRecent(roomId: Int) =
      recentMessages(roomId) map (println)

    val roomId = 562997

    for {
      _ <- printMe
//      _ <- printRooms
//      _ <- printRoom(roomId)
//      _ <- printRecent(roomId)
      _ <- leave(roomId)
      _ <- join(roomId)
//      _ <- speak(roomId, "Hello, world!")
      abort <- Future.successful(live(roomId, println))
//      _ <- Future(abort())
    } yield ()
    //sys.exit
  }
}
