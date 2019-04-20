package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.{User, UserId}
import slick.jdbc.MySQLProfile.api._

class UsersTable(tag: Tag) extends Table[User](tag, "users") {

  def id = column[UserId]("id", O.PrimaryKey, O.AutoInc)

  def username = column[String]("username", O.Unique)

  def passwordHash = column[String]("password_hash")

  def email = column[String]("email")

  def canCreateTasks = column[Boolean]("can_create_tasks", O.Default(false))

  override def * = (id, username, passwordHash, email, canCreateTasks) <> (User.tupled, User.unapply)

}
