package org.polushin.distc.server.web.task.id

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.polushin.distc.server.dao.TasksDao
import org.polushin.distc.server.mapping.JsonMappings
import org.polushin.distc.server.models.Task
import org.polushin.distc.server.web.task.id.file.{FileDeletePath, FileUploadPath}
import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait TaskIdPath extends FileDeletePath with FileUploadPath with JsonMappings {

  val taskIdPath: Route = (pathPrefix(IntNumber) & post) { id =>
    path("update") {
      formFields('displayName, 'description.?) { (displayName, description) =>
        complete(
          TasksDao.findById(id).flatMap {
            case None => Future {
              StatusCodes.NotFound
            }
            case Some(task) =>
              val newTask = Task(task.id, task.ownerId, displayName, description, task.status)
              TasksDao.update(newTask).map(_ => StatusCodes.OK)
          }
        )
      }
    } ~ path("delete") {
      complete(
        TasksDao.delete(id).map {
          case 0 => StatusCodes.NotFound
          case _ => StatusCodes.OK
        }
      )
    } ~ pathEnd {
      complete(
        TasksDao.findById(id).map {
          case None => StatusCodes.NotFound -> "".toJson
          case Some(task) => StatusCodes.OK -> task.toJson
        }
      )
    }
  }

}
