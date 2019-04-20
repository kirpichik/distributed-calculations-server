package org.polushin.distc.server.models

import slick.jdbc.MySQLProfile.api._

//noinspection TypeAnnotation
object TaskStatus extends Enumeration {

  type TaskStatus = Value

  val Created, Ready, Running, NoTargetDevices, Suspended, Cancelled, RuntimeError, Done = Value

  val mapper = MappedColumnType.base[TaskStatus, String](
    e => e.toString,
    s => TaskStatus.withName(s)
  )
}
