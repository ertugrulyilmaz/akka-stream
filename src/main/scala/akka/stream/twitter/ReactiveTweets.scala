package akka.stream.twitter

import akka.actor.{ActorSystem, Props}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}

object ReactiveTweets extends App {

  implicit val actorSystem = ActorSystem()
  implicit val flowMaterializer = ActorMaterializer()

  import actorSystem.dispatcher

  val streamClient = new TwitterStreamClient(actorSystem)
  streamClient.init

  val source = Source.actorPublisher[Tweet](Props[StatusPublisher])

  val normalize = Flow[Tweet].filter(t => t.getHashTags().contains(HashTag("#Akka")))

  val sink = Sink.foreach[Tweet](println)

  source.via(normalize).runWith(sink).andThen {
    case _ =>
      actorSystem.shutdown()
      actorSystem.awaitTermination()
  }

}
