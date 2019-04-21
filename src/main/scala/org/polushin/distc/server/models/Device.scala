package org.polushin.distc.server.models

case class Device(id: Option[DeviceId], currentTaskId: Option[TaskId], activeToken: Option[String])
