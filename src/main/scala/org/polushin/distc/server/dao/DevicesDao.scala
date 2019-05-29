package org.polushin.distc.server.dao

import org.polushin.distc.server.models._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

object DevicesDao extends BaseDao {

  def findById(deviceId: DeviceId): Future[Option[Device]] = devicesTable.filter(_.id === deviceId).result.headOption

  def findFeaturedDeviceIds(features: Map[FeatureId, (Option[Int], Option[Int])]): Future[Seq[DeviceId]] = {
    deviceFeaturesTable.filter { deviceFeature =>
      features.map { case (id, (min, max)) =>
        deviceFeature.featureId === id &&
          min.map(deviceFeature.value >= _).getOrElse(true: Rep[Boolean]) &&
          max.map(deviceFeature.value <= _).getOrElse(true: Rep[Boolean])
      }.reduceLeftOption(_ || _).getOrElse(true: Rep[Boolean])
    }.map(_.deviceId).result
  }

  def create(device: Device, userId: UserId)(implicit ec: ExecutionContext): Future[DeviceId] =
    db.run(devicesTable returning devicesTable.map(_.id) += device).map(deviceId => {
      userDevicesTable += UserDevice(userId, deviceId)
      deviceId
    })

  def updateActiveToken(deviceId: DeviceId, token: String): Future[Int] = devicesTable.filter(_.id === deviceId)
    .map(device => device.activeToken).update(Option(token))

  def removeActiveToken(deviceId: DeviceId): Future[Int] = devicesTable.filter(_.id === deviceId)
    .map(device => device.activeToken).update(Option.empty)

  def getActiveToken(deviceId: DeviceId): Future[Option[Option[String]]] = devicesTable.filter(_.id === deviceId)
    .map(device => device.activeToken).result.headOption

  def updateCurrentTask(deviceId: DeviceId, taskId: Option[TaskId]): Future[Int] = devicesTable.filter(_.id === deviceId)
    .map(device => device.currentTaskId).update(taskId)

  def addFeature(deviceFeature: DeviceFeature): Future[DeviceId] =
    deviceFeaturesTable returning deviceFeaturesTable.map(_.deviceId) += deviceFeature

  def removeFeature(deviceId: DeviceId, featureId: FeatureId): Future[DeviceId] =
    deviceFeaturesTable.filter(_.deviceId === deviceId).filter(_.featureId === featureId).delete

  def getFeatureValue(deviceId: DeviceId, featureId: FeatureId): Future[Option[Int]] =
    deviceFeaturesTable.filter(_.deviceId === deviceId).filter(_.featureId === featureId).map(_.value).result.headOption

  def delete(deviceId: DeviceId): Future[Int] = devicesTable.filter(_.id === deviceId).delete

}
