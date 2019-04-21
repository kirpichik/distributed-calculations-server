package org.polushin.distc.server.models.tables

import java.sql.Date

import org.polushin.distc.server.models.{Device, DeviceId, TaskId}
import slick.jdbc.MySQLProfile.api._

class DevicesTable(tag: Tag) extends Table[Device](tag, "devices") {

  def id = column[DeviceId]("id", O.PrimaryKey, O.AutoInc)

  def lastActivity = column[Date]("last_activity")

  def currentTaskId = column[Option[TaskId]]("current_task_id", O.Default(Option.empty))

  override def * = (id.?, lastActivity, currentTaskId) <> (Device.tupled, Device.unapply)

  def currentTask = foreignKey("device_task_fk", currentTaskId, TableQuery[TasksTable])(_.id.?)
}
