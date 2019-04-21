package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.{Device, DeviceId, TaskId}
import slick.jdbc.MySQLProfile.api._

class DevicesTable(tag: Tag) extends Table[Device](tag, "devices") {

  def id = column[DeviceId]("id", O.PrimaryKey, O.AutoInc)

  def currentTaskId = column[Option[TaskId]]("current_task_id", O.Default(Option.empty))

  def activeToken = column[Option[String]]("active_token", O.Default(Option.empty))

  override def * = (id.?, currentTaskId, activeToken) <> (Device.tupled, Device.unapply)

  def currentTask = foreignKey("device_task_fk", currentTaskId, TableQuery[TasksTable])(_.id.?)
}
