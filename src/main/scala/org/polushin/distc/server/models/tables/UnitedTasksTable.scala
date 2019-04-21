package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.{TaskId, UnitedTasks}
import slick.jdbc.MySQLProfile.api._

class UnitedTasksTable(tag: Tag) extends Table[UnitedTasks](tag, "united_tasks") {

  def taskIdFirst = column[TaskId]("task_id_first", O.PrimaryKey)

  def taskIdSecond = column[TaskId]("task_id_second", O.PrimaryKey)

  override def * = (taskIdFirst, taskIdSecond) <> (UnitedTasks.tupled, UnitedTasks.unapply)

  def taskFirst = foreignKey("task_fk_first", taskIdFirst, TableQuery[TasksTable])(_.id)

  def taskSecond = foreignKey("task_fk_second", taskIdSecond, TableQuery[TasksTable])(_.id)

}
