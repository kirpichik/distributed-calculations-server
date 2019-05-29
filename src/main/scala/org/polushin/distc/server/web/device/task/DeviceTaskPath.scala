package org.polushin.distc.server.web.device.task

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

trait DeviceTaskPath extends DeviceTaskCancelPath with DeviceTaskDonePath with DeviceTaskWaitPath {

  val deviceTaskPath: Route = pathPrefix("cancel") {
    deviceTaskCancelPath
  } ~ pathPrefix("done") {
    deviceTaskDonePath
  } ~ pathPrefix("wait") {
    deviceTaskWaitPath
  }

}
