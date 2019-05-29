package org.polushin.distc.server.web.device

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.polushin.distc.server.dao.DevicesDao

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait DeviceUnregisterPath {

  val deviceUnregisterPath: Route = post {
    headerValueByName("Device-Token") { token =>
      complete(
        DevicesDao.findByToken(token).map {
          case None => Future {
            StatusCodes.Forbidden
          }
          case Some(device) => DevicesDao.removeActiveToken(device.id.get).map(_ => StatusCodes.OK)
        }
      )
    }
  }

}
