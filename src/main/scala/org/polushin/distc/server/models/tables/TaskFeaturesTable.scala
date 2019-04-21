package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.{FeatureId, TaskFeature, TaskId}
import slick.jdbc.MySQLProfile.api._

class TaskFeaturesTable(tag: Tag) extends Table[TaskFeature](tag, "task_features") {

  def taskId = column[TaskId]("task_id", O.PrimaryKey)

  def featureId = column[FeatureId]("feature_id", O.PrimaryKey)

  def minValue = column[Option[Int]]("min_value")

  def maxValue = column[Option[Int]]("max_value")

  override def * = (taskId, featureId, minValue, maxValue) <> (TaskFeature.tupled, TaskFeature.unapply)

  def task = foreignKey("feature_task_fk", taskId, TableQuery[TasksTable])(_.id)

  def feature = foreignKey("feature_fk", featureId, TableQuery[FeaturesTable])(_.id)

}
