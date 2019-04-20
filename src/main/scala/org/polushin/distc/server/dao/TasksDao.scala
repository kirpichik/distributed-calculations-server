package org.polushin.distc.server.dao

import org.polushin.distc.server.models.TaskStatus.TaskStatus
import org.polushin.distc.server.models.{Task, TaskId, TaskStatus}
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

object TasksDao extends BaseDao {

  //noinspection TypeAnnotation
  private implicit val taskStatusMapper = TaskStatus.mapper

  def findById(taskId: TaskId): Future[Option[Task]] = tasksTable.filter(_.id === taskId).result.headOption

  def create(task: Task): Future[TaskId] = tasksTable returning tasksTable.map(_.id) += task

  def updateDisplayName(taskId: TaskId, displayName: String): Future[Int] = tasksTable.filter(_.id === taskId)
    .map(task => (task.displayName, )).update((displayName, ))

  def updateDescription(taskId: TaskId, description: Option[String]): Future[Int] = tasksTable.filter(_.id === taskId)
    .map(task => (task.description, )).update((description, ))

  def updateStatus(taskId: TaskId, status: TaskStatus): Future[Int] = tasksTable.filter(_.id === taskId)
    .map(task => (task.status, )).update((status, ))

  def delete(taskId: TaskId): Future[Int] = tasksTable.filter(_.id === taskId).delete

}
