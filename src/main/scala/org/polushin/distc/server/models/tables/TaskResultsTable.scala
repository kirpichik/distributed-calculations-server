package org.polushin.distc.server.models.tables

import java.sql.Date

import org.polushin.distc.server.models.TaskResultStatus.TaskResultStatus
import org.polushin.distc.server.models._
import slick.jdbc.MySQLProfile.api._

class TaskResultsTable(tag: Tag) extends Table[TaskResult](tag, "task_results") {

  //noinspection TypeAnnotation
  private implicit val taskStatusMapper = MappedColumnType.base[TaskResultStatus, String](
    e => e.toString,
    s => TaskResultStatus.withName(s)
  )

  def id = column[TaskResultId]("id", O.PrimaryKey, O.AutoInc)

  def taskId = column[TaskId]("task_id")

  def deviceId = column[DeviceId]("device_id")

  def status = column[TaskResultStatus]("status")

  def date = column[Date]("date")

  def valueInt = column[Option[Int]]("value_int")

  def valueString = column[Option[String]]("value_str")

  def valueFloat = column[Option[Float]]("value_float")

  override def * = (id.?, taskId, deviceId, status, date, valueInt, valueString, valueFloat) <> (TaskResult.tupled, TaskResult.unapply)

  def task = foreignKey("result_task_fk", taskId, TableQuery[TasksTable])(_.id)

  def device = foreignKey("result_device_fk", deviceId, TableQuery[DevicesTable])(_.id)

}
