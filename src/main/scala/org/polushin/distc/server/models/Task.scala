package org.polushin.distc.server.models

import org.polushin.distc.server.models.TaskStatus.TaskStatus

case class Task(id: Option[TaskId], ownerId: UserId, displayName: String, description: Option[String], status: TaskStatus)