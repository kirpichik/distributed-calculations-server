package org.polushin.distc.server.web.task

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.polushin.distc.server.web.task.id.TaskIdPath

trait TaskPath extends TaskListPath with TaskCreatePath with TaskIdPath {

  val taskPath: Route = pathPrefix("list") {
    taskListPath
  } ~ path("create") {
    taskCreatePath
  } ~ taskIdPath

}
