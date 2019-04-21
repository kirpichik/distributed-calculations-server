package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.{TaskFile, TaskId}
import slick.jdbc.MySQLProfile.api._

class TaskFilesTable(tag: Tag) extends Table[TaskFile](tag, "task_files") {

  def taskId = column[TaskId]("task_id", O.PrimaryKey)

  def filename = column[String]("filename", O.PrimaryKey)

  override def * = (taskId, filename) <> (TaskFile.tupled, TaskFile.unapply)

  def task = foreignKey("file_task_fk", taskId, TableQuery[TasksTable])(_.id)

}
