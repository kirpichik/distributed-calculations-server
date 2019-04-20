package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.{DeviceFeature, DeviceId, FeatureId}
import slick.jdbc.MySQLProfile.api._

class DeviceFeaturesTable(tag: Tag) extends Table[DeviceFeature](tag, "device_features") {

  def deviceId = column[DeviceId]("device_id")

  def featureId = column[FeatureId]("feature_id")

  override def * = (deviceId, featureId) <> (DeviceFeature.tupled, DeviceFeature.unapply)

  def deviceFk = foreignKey("device_fk", deviceId, TableQuery[DevicesTable])(_.id)

  def feature = foreignKey("feature_fk", featureId, TableQuery[FeaturesTable])(_.id)

}
