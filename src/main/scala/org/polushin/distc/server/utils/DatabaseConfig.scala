package org.polushin.distc.server.utils

trait DatabaseConfig extends Config {

  val driver = slick.jdbc.MySQLProfile

  import driver.api.Database

  def db: driver.backend.Database = Database.forConfig("database")

  implicit val session: driver.backend.Session = db.createSession()

}
