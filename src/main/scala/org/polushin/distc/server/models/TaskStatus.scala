package org.polushin.distc.server.models

//noinspection TypeAnnotation
object TaskStatus extends Enumeration {

  type TaskStatus = Value

  val Created, Ready, Running, NoTargetDevices, Suspended, Cancelled, RuntimeError, Done = Value

}
