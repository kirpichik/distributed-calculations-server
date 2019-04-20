package org.polushin.distc.server.dao

import org.polushin.distc.server.models.tables._
import org.polushin.distc.server.utils.DatabaseConfig
import slick.dbio.NoStream
import slick.lifted.TableQuery
import slick.sql.{FixedSqlStreamingAction, SqlAction}

import scala.concurrent.Future

trait BaseDao extends DatabaseConfig {

  val tasksTable = TableQuery[TasksTable]
  val usersTable = TableQuery[UsersTable]
  val devicesTable = TableQuery[DevicesTable]
  val featuresTable = TableQuery[FeaturesTable]
  val resultsTable = TableQuery[TaskResultsTable]
  val userDevicesTable = TableQuery[UserDevicesTable]
  val unitedTasksTable = TableQuery[UnitedTasksTable]
  val taskFilesTable = TableQuery[TaskFilesTable]
  val deviceFeaturesTable = TableQuery[DeviceFeaturesTable]
  val taskFeaturesTable = TableQuery[TaskFeaturesTable]
  val resultFilesTable = TableQuery[ResultFilesTable]

  private type ReadStreamAction[A] = FixedSqlStreamingAction[Seq[A], A, _ <: slick.dbio.Effect]
  private type FromAction[A] = SqlAction[A, NoStream, _ <: slick.dbio.Effect]

  protected implicit def executeFromDb[A](action: FromAction[A]): Future[A] = {
    db.run(action)
  }

  protected implicit def executeReadStreamFromDb[A](action: ReadStreamAction[A]): Future[Seq[A]] = {
    db.run(action)
  }

}
