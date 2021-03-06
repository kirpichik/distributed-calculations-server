package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.{DeviceId, UserDevice, UserId}
import slick.jdbc.MySQLProfile.api._

class UserDevicesTable(tag: Tag) extends Table[UserDevice](tag, "user_devices") {

  def userId = column[UserId]("user_id", O.PrimaryKey)

  def deviceId = column[DeviceId]("device_id", O.PrimaryKey)

  override def * = (userId, deviceId) <> (UserDevice.tupled, UserDevice.unapply)

  def user = foreignKey("user_devices_user_fk", userId, TableQuery[UsersTable])(_.id)

  def device = foreignKey("user_devices_device_fk", deviceId, TableQuery[DevicesTable])(_.id)

}
