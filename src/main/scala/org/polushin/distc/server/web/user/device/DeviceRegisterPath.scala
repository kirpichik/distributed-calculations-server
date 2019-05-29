package org.polushin.distc.server.web.user.device

import java.util.UUID

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.polushin.distc.server.dao.{DevicesDao, UsersDao}
import org.polushin.distc.server.models.Device

import scala.concurrent.ExecutionContext.Implicits.global

trait DeviceRegisterPath {

  val deviceRegisterPath: Route = post {
    headerValueByName("User-Token") { token =>
      complete(
        UsersDao.findUserIdByToken(token).map {
          case None => StatusCodes.Forbidden -> "Unknown user-token"
          case Some(userId) =>
            val deviceToken = UUID.randomUUID().toString
            DevicesDao.create(Device(Option.empty, Option.empty, Option(deviceToken)), userId)
            StatusCodes.OK -> deviceToken
        }
      )
    }
  }

}
