package org.polushin.distc.server.web.user

import java.math.BigInteger
import java.security.MessageDigest
import java.util.UUID

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.polushin.distc.server.dao.UsersDao
import org.polushin.distc.server.mapping.JsonMappings
import org.polushin.distc.server.models.{User, UserToken}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait UserRegisterPath extends JsonMappings {

  val userRegisterPath: Route = post {
    formFields('username, 'email, 'password) { (username, email, password) =>
      extractClientIP { ip =>
        complete(
          UsersDao.findByUsername(username).map {
            case Some(_) => Future {
              StatusCodes.Conflict -> "Username already exists"
            }
            case None =>
              val hash = hashPassword(password)
              UsersDao.create(User(Option.empty, username, hash, email, canCreateTasks = false))
                .flatMap(
                  id => UsersDao.addActiveToken(UserToken(id, UUID.randomUUID().toString,
                    ip.toOption.map(_.getHostAddress).getOrElse("unknown")))
                ).map(uuid => StatusCodes.OK -> uuid)
          }
        )
      }
    }
  }

  private def hashPassword(password: String) = String.format("%032x", new BigInteger(1,
    MessageDigest.getInstance("SHA-256").digest(password.getBytes("UTF-8"))))

}
