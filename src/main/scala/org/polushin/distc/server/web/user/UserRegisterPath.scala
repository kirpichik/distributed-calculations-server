package org.polushin.distc.server.web.user

import java.math.BigInteger
import java.security.MessageDigest
import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import org.polushin.distc.server.dao.UsersDao
import org.polushin.distc.server.mapping.JsonMappings
import org.polushin.distc.server.models.{User, UserToken}
import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global

trait UserRegisterPath extends JsonMappings {

  val userRegisterPath = post {
    formFields('username, 'email, 'password) { (username, email, password) =>
      val hash = String.format("%032x", new BigInteger(1, // TODO - extract
        MessageDigest.getInstance("SHA-256").digest(password.getBytes("UTF-8"))))

      complete(UsersDao.create(User(Option.empty, username, hash, email, canCreateTasks = false))
        .map(id => UsersDao.addActiveToken(UserToken(id, UUID.randomUUID().toString, "")).map(_.toJson)))
    }
  }

}
