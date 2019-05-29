package org.polushin.distc.server.web.task.id.file

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, post}
import akka.http.scaladsl.server.Route

trait FileUploadPath {

  val taskIdFileUploadPath: Route = post {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Task file upload path</h1>"))
  }

}
