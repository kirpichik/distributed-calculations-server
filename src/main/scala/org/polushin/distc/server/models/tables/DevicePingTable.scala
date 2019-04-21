package org.polushin.distc.server.models.tables

import java.sql.Date

import org.polushin.distc.server.models.{DeviceId, DevicePing}
import slick.jdbc.MySQLProfile.api._

class DevicePingTable(tag: Tag) extends Table[DevicePing](tag, "device_ping") {

  def deviceId = column[DeviceId]("device_id", O.PrimaryKey)

  def lastActivity = column[Option[Date]]("last_activity")

  def longitude = column[Option[Float]]("longitude")

  def latitude = column[Option[Float]]("latitude")

  def wifiBssid = column[Option[String]]("wifi_bssid")

  def lanMac = column[Option[String]]("lan_mac")

  def lanIp = column[Option[String]]("lan_ip")

  def bluetoothMac = column[Option[String]]("bluetooth_mac")

  override def * = (deviceId, lastActivity, longitude, latitude, wifiBssid, lanMac, lanIp, bluetoothMac) <> (DevicePing.tupled, DevicePing.unapply)

  def device = foreignKey("device_fk", deviceId, TableQuery[DevicesTable])(_.id)

}
