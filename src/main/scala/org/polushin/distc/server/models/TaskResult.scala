package org.polushin.distc.server.models

import java.sql.Date

import org.polushin.distc.server.models.TaskResultStatus.TaskResultStatus

case class TaskResult(
                       id: ResultId,
                       taskId: TaskId,
                       deviceId: DeviceId,
                       status: TaskResultStatus,
                       date: Date,
                       valueInt: Option[Int],
                       valueStr: Option[String],
                       valueFloat: Option[Float]
                     )
