package akka.stream

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Flow, Sink, Source}

object BasicStream extends App {

  implicit val actorSystem = ActorSystem()
  implicit val flowMaterializer = ActorMaterializer()

  import actorSystem.dispatcher

  val input = Source(1 to 50)

  val normalize = Flow[Int].filter(_ % 2 == 0).map(_ * 2)

  val output = Sink.foreach(println)

  input.via(normalize).runWith(output).andThen {
    case _ =>
      actorSystem.shutdown()
      actorSystem.awaitTermination()

  }

}
