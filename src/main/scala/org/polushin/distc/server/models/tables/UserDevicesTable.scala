package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.{DeviceId, UserDevice, UserId}
import slick.jdbc.MySQLProfile.api._

class UserDevicesTable(tag: Tag) extends Table[UserDevice](tag, "user_devices") {

  def userId = column[UserId]("user_id")

  def deviceId = column[DeviceId]("device_id")

  override def * = (userId, deviceId) <> (UserDevice.tupled, UserDevice.unapply)

  def user = foreignKey("user_fk", userId, TableQuery[UsersTable])(_.id)

  def device = foreignKey("device_fk", deviceId, TableQuery[DevicesTable])(_.id)

}
