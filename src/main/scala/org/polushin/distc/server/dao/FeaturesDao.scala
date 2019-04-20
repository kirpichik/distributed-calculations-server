package org.polushin.distc.server.dao

import org.polushin.distc.server.models.{Feature, FeatureId}
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

object FeaturesDao extends BaseDao {

  def findById(featureId: FeatureId): Future[Option[Feature]] = featuresTable.filter(_.id === featureId).result.headOption

  def create(feature: Feature): Future[FeatureId] = featuresTable returning featuresTable.map(_.id) += feature

  def updateDisplayName(featureId: FeatureId, displayName: String): Future[Int] = featuresTable.filter(_.id === featureId)
    .map(feature => (feature.displayName, )).update((displayName, ))

  def delete(featureId: FeatureId): Future[Int] = featuresTable.filter(_.id === featureId).delete
}
