package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.TaskStatus.TaskStatus
import org.polushin.distc.server.models.{Task, TaskId, TaskStatus, UserId}
import slick.jdbc.MySQLProfile.api._

class TasksTable(tag: Tag) extends Table[Task](tag, "tasks") {

  //noinspection TypeAnnotation
  private implicit val taskStatusMapper = MappedColumnType.base[TaskStatus, String](
    e => e.toString,
    s => TaskStatus.withName(s)
  )

  def id = column[TaskId]("id", O.PrimaryKey, O.AutoInc)

  def ownerId = column[UserId]("owner_id")

  def displayName = column[String]("display_name")

  def description = column[Option[String]]("description")

  def status = column[TaskStatus]("status")

  override def * = (id, ownerId, displayName, description, status) <> (Task.tupled, Task.unapply)

  def owner = foreignKey("task_user_fk", ownerId, TableQuery[UsersTable])(_.id)

}
