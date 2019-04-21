package org.polushin.distc.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import org.polushin.distc.server.utils.Config

import scala.concurrent.ExecutionContextExecutor

object Main extends App with Config with Routes {

  private implicit val system: ActorSystem = ActorSystem()
  private implicit val materializer: ActorMaterializer = ActorMaterializer()
  private implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  Http().bindAndHandle(route, httpInterface, httpPort)

}