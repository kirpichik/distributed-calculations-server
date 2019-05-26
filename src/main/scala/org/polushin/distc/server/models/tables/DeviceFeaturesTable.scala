package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.{DeviceFeature, DeviceId, FeatureId}
import slick.jdbc.MySQLProfile.api._

class DeviceFeaturesTable(tag: Tag) extends Table[DeviceFeature](tag, "device_features") {

  def deviceId = column[DeviceId]("device_id", O.PrimaryKey)

  def featureId = column[FeatureId]("feature_id", O.PrimaryKey)

  def value = column[Int]("value")

  override def * = (deviceId, featureId, value) <> (DeviceFeature.tupled, DeviceFeature.unapply)

  def device = foreignKey("device_features_device_fk", deviceId, TableQuery[DevicesTable])(_.id)

  def feature = foreignKey("device_features_feature_fk", featureId, TableQuery[FeaturesTable])(_.id)

}
