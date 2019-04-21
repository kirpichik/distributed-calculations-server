package org.polushin.distc.server.web.user

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._

trait UserLoginPath {

  val userLoginPath = post {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>User login path</h1>"))
  }

}
