package org.polushin.distc.server.web.user

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.polushin.distc.server.dao.UsersDao
import org.polushin.distc.server.mapping.JsonMappings
import org.polushin.distc.server.web.user.device.DeviceRegisterPath
import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global

trait UserPath extends DeviceRegisterPath with UserLoginPath with UserLogoutPath with UserRegisterPath with JsonMappings {

  val userPath: Route = pathPrefix("device") {
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
      complete(UsersDao.findById(id).map {
        case Some(value) => value.username
        case None => "User not found"
      }.map(_.toJson))
    }
  }

}
