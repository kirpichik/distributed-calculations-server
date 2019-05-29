package org.polushin.distc.server.web.device.task

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, post}
import akka.http.scaladsl.server.Route

trait DeviceTaskCancelPath {

  val deviceTaskCancelPath: Route = post {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Device cancel path</h1>")) // TODO - notify device cancel
  }

}
