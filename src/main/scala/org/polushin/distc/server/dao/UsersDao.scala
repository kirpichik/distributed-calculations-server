package org.polushin.distc.server.dao

import org.polushin.distc.server.models.{User, UserId, UserToken}
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

object UsersDao extends BaseDao {

  def findById(userId: UserId): Future[Option[User]] = usersTable.filter(_.id === userId).result.headOption

  def create(user: User): Future[UserId] = usersTable returning usersTable.map(_.id) += user

  def addActiveToken(userToken: UserToken): Future[String] =
    userTokensTable returning userTokensTable.map(_.token) += userToken

  def removeActiveToken(userId: UserId, token: String): Future[Int] =
    userTokensTable.filter(_.userId === userId).filter(_.token === token).delete

  def findActiveToken(userId: UserId, token: String): Future[Option[String]] =
    userTokensTable.filter(_.userId === userId).filter(_.token === token).map(_.lastIp).result.headOption

  def updatePasswordHash(userId: UserId, passwordHash: String): Future[Int] = usersTable.filter(_.id === userId)
    .map(user => user.passwordHash).update(passwordHash)

  def updateEmail(userId: UserId, email: String): Future[Int] = usersTable.filter(_.id === userId)
    .map(user => user.email).update(email)

  def updateCanCreateTasks(userId: UserId, canCreate: Boolean): Future[Int] = usersTable.filter(_.id === userId)
    .map(user => user.canCreateTasks).update(canCreate)

  def delete(userId: UserId): Future[Int] = usersTable.filter(_.id === userId).delete

}
