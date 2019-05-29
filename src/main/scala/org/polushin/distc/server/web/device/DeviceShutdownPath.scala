package org.polushin.distc.server.web.device

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, post}
import akka.http.scaladsl.server.Route

trait DeviceShutdownPath {

  val deviceShutdownPath: Route = post {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Device shutdown path</h1>")) // TODO - notify device shutdown
  }

}
