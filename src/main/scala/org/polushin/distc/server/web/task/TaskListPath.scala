package org.polushin.distc.server.web.task

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.polushin.distc.server.dao.{TasksDao, UsersDao}
import org.polushin.distc.server.mapping.JsonMappings
import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait TaskListPath extends JsonMappings {

  val taskListPath: Route = (get & headerValueByName("User-Token")) { token =>
    path(IntNumber) { userId =>
      complete(
        UsersDao.findUserIdByToken(token).flatMap {
          case None => Future {
            StatusCodes.Forbidden -> "Unknown user token".toJson
          }
          case Some(_) => TasksDao.findUserTasks(userId).map(tasks => StatusCodes.OK -> tasks.toJson)
        }
      )
    } ~ pathEnd {
      complete(
        UsersDao.findUserIdByToken(token).flatMap {
          case None => Future {
            StatusCodes.Forbidden -> "Unknown user token".toJson
          }
          case Some(userId) => TasksDao.findUserTasks(userId).map(tasks => StatusCodes.OK -> tasks.toJson)
        }
      )
    }
  }

}
