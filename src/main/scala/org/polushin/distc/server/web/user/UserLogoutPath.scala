package org.polushin.distc.server.web.user

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.polushin.distc.server.dao.UsersDao

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait UserLogoutPath {

  val userLogoutPath: Route = post {
    headerValueByName("User-Token") { token =>
      complete(
        UsersDao.findUserIdByToken(token).map {
          case None => Future {
            StatusCodes.Forbidden
          }
          case Some(user) =>
            UsersDao.removeActiveToken(user, token).map(_ => StatusCodes.OK)
        }
      )
    }
  }

}
