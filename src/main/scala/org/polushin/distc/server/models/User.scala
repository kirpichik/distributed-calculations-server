package org.polushin.distc.server.models

case class User(id: UserId, username: String, passwordHash: String, email: String, canCreateTasks: Boolean)
