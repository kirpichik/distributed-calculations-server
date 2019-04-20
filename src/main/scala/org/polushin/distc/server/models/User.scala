package org.polushin.distc.server.models

case class User(id: Option[UserId], username: String, passwordHash: String, email: String, canCreateTasks: Boolean)
