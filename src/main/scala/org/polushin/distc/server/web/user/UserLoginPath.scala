package org.polushin.distc.server.web.user

import java.math.BigInteger
import java.security.MessageDigest
import java.util.UUID

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.polushin.distc.server.dao.UsersDao
import org.polushin.distc.server.models.UserToken

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait UserLoginPath {

  val userLoginPath: Route = post {
    formFields('username, 'password) { (username, password) =>
      extractClientIP { ip =>
        complete(
          UsersDao.findByUsername(username).map {
            case None =>
              StatusCodes.Forbidden -> Future {
                "Username not found"
              }
            case Some(user) =>
              if (hashPassword(password) == user.passwordHash) {
                StatusCodes.OK ->
                  UsersDao.addActiveToken(UserToken(user.id.get, UUID.randomUUID().toString,
                    ip.toOption.map(_.getHostAddress).getOrElse("unknown")))
              } else
                StatusCodes.Forbidden -> Future {
                  "Wrong password"
                }
          }
        )
      }
    }
  }


  private def hashPassword(password: String) = String.format("%032x", new BigInteger(1,
    MessageDigest.getInstance("SHA-256").digest(password.getBytes("UTF-8"))))

}
