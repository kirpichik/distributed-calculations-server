package org.polushin.distc.server.web.user.device

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._

trait DeviceRegisterPath {

  val deviceRegisterPath = post {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>Device register path</h1>"))
  }

}
