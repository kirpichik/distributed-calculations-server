package org.polushin.distc.server.web.device

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.polushin.distc.server.web.device.task.DeviceTaskPath

trait DevicePath extends DeviceUnregisterPath with DeviceShutdownPath with DevicePingPath with DeviceTaskPath {

  val devicePath: Route = pathPrefix("task") {
    deviceTaskPath
  } ~ path("unregister") {
    deviceUnregisterPath
  } ~ path("shutdown") {
    deviceShutdownPath
  } ~ path("ping") {
    devicePingPath
  }

}
