package org.polushin.distc.server.dao

import java.sql.Date

import org.polushin.distc.server.models._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

object DevicesDao extends BaseDao {

  def findById(deviceId: DeviceId): Future[Option[Device]] = devicesTable.filter(_.id === deviceId).result.headOption

  def findFeaturedDeviceIds(features: Map[FeatureId, (Option[Int], Option[Int])]): Future[Seq[DeviceId]] = {
    deviceFeaturesTable.filter { deviceFeature =>
      features.map { case (id, (min, max)) =>
        deviceFeature.featureId === id &&
          min.map(deviceFeature.value >= _).getOrElse(true) &&
          max.map(deviceFeature.value <= _).getOrElse(true)
      }.reduceLeftOption(_ || _).getOrElse(true)
    }.map(_.deviceId).result
  }

  def create(device: Device): Future[DeviceId] = devicesTable returning devicesTable.map(_.id) += device

  def updateActivity(deviceId: DeviceId, date: Date): Future[Int] = devicesTable.filter(_.id === deviceId)
    .map(device => (device.lastActivity, )).update((date, ))

  def updateCurrentTask(deviceId: DeviceId, taskId: Option[TaskId]): Future[Int] = devicesTable.filter(_.id === deviceId)
    .map(device => (device.currentTaskId, )).update((taskId, ))

  def addFeature(deviceFeature: DeviceFeature): Future[DeviceId] =
    deviceFeaturesTable returning deviceFeaturesTable.map(_.deviceId) += deviceFeature

  def removeFeature(deviceId: DeviceId, featureId: FeatureId): Future[DeviceId] =
    deviceFeaturesTable.filter(_.deviceId === deviceId).filter(_.featureId === featureId).delete

  def getFeatureValue(deviceId: DeviceId, featureId: FeatureId): Future[Option[Int]] =
    deviceFeaturesTable.filter(_.deviceId === deviceId).filter(_.featureId === featureId).map(_.value).result.headOption

  def delete(deviceId: DeviceId): Future[Int] = devicesTable.filter(_.id === deviceId).delete

}
