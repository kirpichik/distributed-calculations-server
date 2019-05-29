package org.polushin.distc.server.web.task

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.polushin.distc.server.dao.{TasksDao, UsersDao}
import org.polushin.distc.server.models.{Task, TaskStatus}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait TaskCreatePath {

  val taskCreatePath: Route = (headerValueByName("User-Token") & post) { token =>
    formFields('displayName, 'description.?) { (displayName, description) =>
      complete(
        UsersDao.findUserIdByToken(token).map {
          case None => Future {
            StatusCodes.Forbidden -> ""
          }
          case Some(userId) =>
            TasksDao.create(Task(None, userId, displayName, description, TaskStatus.Created))
              .map(taskId => StatusCodes.OK -> taskId.toString)
        }
      )
    }
  }

}
