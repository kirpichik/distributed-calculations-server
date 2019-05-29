package org.polushin.distc.server.web.device

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, post}
import akka.http.scaladsl.server.Route

trait DevicePingPath {

  val devicePingPath: Route = post {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Device ping path</h1>"))
  }

}
