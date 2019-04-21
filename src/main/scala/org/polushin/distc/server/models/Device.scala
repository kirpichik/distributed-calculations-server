package org.polushin.distc.server.models

import java.sql.Date

case class Device(id: Option[DeviceId], lastActivity: Date, currentTaskId: Option[TaskId])
