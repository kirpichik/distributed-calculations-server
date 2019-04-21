package org.polushin.distc.server.models

case class UserToken(userId: UserId, token: String, lastIp: String)
