package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.{Feature, FeatureId}
import slick.jdbc.MySQLProfile.api._

class FeaturesTable(tag: Tag) extends Table[Feature](tag, "features") {

  def id = column[FeatureId]("id", O.PrimaryKey, O.AutoInc)

  def displayName = column[String]("display_name")

  override def * = (id, displayName) <> (Feature.tupled, Feature.unapply)

}
