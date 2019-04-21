package org.polushin.distc.server.web.task

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._

trait TaskPath {

  val taskPath = path("task") {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Task path</h1>"))
  }

}
