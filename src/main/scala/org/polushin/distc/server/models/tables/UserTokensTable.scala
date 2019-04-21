package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.{UserId, UserToken}
import slick.jdbc.MySQLProfile.api._

class UserTokensTable(tag: Tag) extends Table[UserToken](tag, "user_tokens") {

  def userId = column[UserId]("user_id", O.PrimaryKey)

  def token = column[String]("token", O.PrimaryKey)

  def lastIp = column[String]("last_ip")

  override def * = (userId, token, lastIp) <> (UserToken.tupled, UserToken.unapply)

  def user = foreignKey("user_fk", userId, TableQuery[UsersTable])(_.id)

}
