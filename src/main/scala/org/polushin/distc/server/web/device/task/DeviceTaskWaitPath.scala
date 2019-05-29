package org.polushin.distc.server.web.device.task

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

trait DeviceTaskWaitPath {

  val deviceTaskWaitPath: Route = post {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Device wait path</h1>")) // TODO - notify device wait
  }

}
