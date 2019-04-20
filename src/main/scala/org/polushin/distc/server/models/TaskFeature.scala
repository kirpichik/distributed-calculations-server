package org.polushin.distc.server.models

case class TaskFeature(taskId: TaskId, featureId: FeatureId, minValue: Option[Int], maxValue: Option[Int])
