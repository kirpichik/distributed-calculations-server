package org.polushin.distc.server.web.user

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, post}

trait UserLogoutPath {

  val userLogoutPath = post {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>User logout path</h1>"))
  }

}
