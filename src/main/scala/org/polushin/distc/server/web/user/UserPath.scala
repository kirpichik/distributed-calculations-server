package org.polushin.distc.server.web.user

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import org.polushin.distc.server.web.user.device.DeviceRegisterPath

trait UserPath extends DeviceRegisterPath with UserLoginPath with UserLogoutPath with UserRegisterPath {

  val userPath = pathPrefix("device") {
    path("register") {
      deviceRegisterPath
    }
  } ~ path("login") {
    userLoginPath
  } ~ path("logout") {
    userLogoutPath
  } ~ path("register") {
    userRegisterPath
  } ~ path(IntNumber) { id =>
    get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>User info: $id</h1>"))
    }
  }

}
