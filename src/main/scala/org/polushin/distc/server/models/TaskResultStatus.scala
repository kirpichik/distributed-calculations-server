package org.polushin.distc.server.models

import slick.jdbc.MySQLProfile.api._

//noinspection TypeAnnotation
object TaskResultStatus extends Enumeration {

  type TaskResultStatus = Value

  val Done, CancelledByOwner, CancelledOnDevice, CancelledSelf, CompileError, RuntimeError = Value

  val mapper = MappedColumnType.base[TaskResultStatus, String](
    e => e.toString,
    s => TaskResultStatus.withName(s)
  )

}
