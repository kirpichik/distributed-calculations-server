package org.polushin.distc.server.utils

import org.flywaydb.core.Flyway

trait MigrationConfig extends Config {

  private val flyway = Flyway.configure().dataSource(databaseUrl, databaseUser, databasePassword).load()

  def migrate(): Unit = {
    flyway.migrate()
  }

  def reloadSchema(): Unit = {
    flyway.clean()
    flyway.migrate()
  }

}
