package org.polushin.distc.server.web.device

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._

trait DevicePath {

  val devicePath = path("device") {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Device path</h1>"))
  }

}
