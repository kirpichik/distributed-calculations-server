package org.polushin.distc.server

import akka.http.scaladsl.server.Directives._
import org.polushin.distc.server.web.device.DevicePath
import org.polushin.distc.server.web.task.TaskPath
import org.polushin.distc.server.web.user.UserPath

trait Routes extends UserPath with TaskPath with DevicePath {

  val route = pathPrefix("user") {
    userPath
  } ~ pathPrefix("task") {
    taskPath
  } ~ pathPrefix("device") {
    devicePath
  }

}
