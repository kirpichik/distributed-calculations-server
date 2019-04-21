package org.polushin.distc.server.models

import java.sql.Date

case class DevicePing(
                       deviceId: DeviceId,
                       lastActivity: Option[Date],
                       longitude: Option[Float],
                       latitude: Option[Float],
                       wifiBssid: Option[String],
                       lanMac: Option[String],
                       lanIp: Option[String],
                       bluetoothMac: Option[String]
                     )
