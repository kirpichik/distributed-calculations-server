package org.polushin.distc.server.models

//noinspection TypeAnnotation
object TaskResultStatus extends Enumeration {

  type TaskResultStatus = Value

  val Done, CancelledByOwner, CancelledOnDevice, CancelledSelf, CompileError, RuntimeError = Value

}
