package org.polushin.distc.server

import java.sql.Date

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.slick.javadsl.SlickSession

object Main extends App {

  implicit val system: ActorSystem = ActorSystem()
  implicit val mat: ActorMaterializer = ActorMaterializer()

  implicit val session: SlickSession = SlickSession.forConfig("slick-mysql")
  system.registerOnTermination(session.close())

  import session.profile.api._

  class Users(tag: Tag) extends Table[(Int, String, String, String, Boolean)](tag, "users") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def username = column[String]("username", O.Unique)

    def passwordHash = column[String]("password_hash")

    def email = column[String]("email")

    def canCreateTasks = column[Boolean]("can_create_tasks", O.Default(false))

    override def * = (id, username, passwordHash, email, canCreateTasks)
  }

  val users = TableQuery[Users]

  class Tasks(tag: Tag) extends Table[(Int, Int, String, Option[String], Int)](tag, "tasks") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def ownerId = column[Int]("owner_id")

    def displayName = column[String]("display_name")

    def description = column[Option[String]]("description")

    def status = column[Int]("status") // TODO: change to enum

    override def * = (id, ownerId, displayName, description, status)
  }

  val tasks = TableQuery[Tasks]

  class TaskFiles(tag: Tag) extends Table[(Int, String)](tag, "task_files") {
    def taskId = column[Int]("task_id")

    def filename = column[String]("filename")

    override def * = (taskId, filename)

    def taskFk = foreignKey("task_fk", taskId, tasks)(_.id)
  }

  class UnitedTasks(tag: Tag) extends Table[(Int, Int)](tag, "united_tasks") {
    def taskIdFirst = column[Int]("task_id_first")

    def taskIdSecond = column[Int]("task_id_second")

    override def * = (taskIdFirst, taskIdSecond)

    def taskFkFirst = foreignKey("task_fk_second", taskIdFirst, tasks)(_.id)

    def taskFkSecond = foreignKey("task_fk_second", taskIdSecond, tasks)(_.id)
  }

  class Devices(tag: Tag) extends Table[(Int,
    Date,
    Option[Int],
    Option[Float],
    Option[Float],
    Option[String],
    Option[String],
    Option[String],
    Option[String])](tag, "devices") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def lastActivity = column[Date]("last_activity")

    def currentTaskId = column[Option[Int]]("current_task_id", O.Default(Option.empty))

    def longitude = column[Option[Float]]("longitude")

    def latitude = column[Option[Float]]("latitude")

    def wifiBssId = column[Option[String]]("wifi_bssid")

    def lanMac = column[Option[String]]("lan_mac")

    def lapIp = column[Option[String]]("lan_ip")

    def bluetoothMac = column[Option[String]]("bluetooth_mac")

    override def * = (id, lastActivity, currentTaskId, longitude, latitude, wifiBssId, lanMac, lapIp, bluetoothMac)

    def taskFk = foreignKey("task_fk", currentTaskId, tasks)(_.id)
  }

  val devices = TableQuery[Devices]

  class UserDevices(tag: Tag) extends Table[(Int, Int)](tag, "user_devices") {
    def userId = column[Int]("user_id")

    def deviceId = column[Int]("device_id")

    override def * = (userId, deviceId)

    def userFk = foreignKey("user_fk", userId, users)(_.id)

    def deviceFk = foreignKey("device_fk", deviceId, devices)(_.id)
  }

  class Features(tag: Tag) extends Table[(Int, String)](tag, "features") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def displayName = column[String]("display_name")

    override def * = (id, displayName)
  }

  val features = TableQuery[Features]

  class TaskFeatures(tag: Tag) extends Table[(Int, Int, Option[Int], Option[Int])](tag, "task_features") {
    def taskId = column[Int]("task_id")

    def featureId = column[Int]("feature_id")

    def minValue = column[Option[Int]]("min_value")

    def maxValue = column[Option[Int]]("max_value")

    override def * = (taskId, featureId, minValue, maxValue)

    def taskFk = foreignKey("task_fk", taskId, tasks)(_.id)

    def featureFk = foreignKey("feature_id", featureId, features)(_.id)
  }

  class DeviceFeatures(tag: Tag) extends Table[(Int, Int)](tag, "device_features") {
    def deviceId = column[Int]("device_id")

    def featureId = column[Int]("feature_id")

    override def * = (deviceId, featureId)

    def deviceFk = foreignKey("device_fk", deviceId, devices)(_.id)

    def featureFk = foreignKey("feature_fk", featureId, features)(_.id)
  }

  class TaskResults(tag: Tag) extends Table[(Int, Int, Int, Int, Date, Option[Int], Option[String], Option[Float])](tag, "task_results") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def taskId = column[Int]("task_id")

    def deviceId = column[Int]("device_id")

    def status = column[Int]("status") // TODO: change to enum
    def date = column[Date]("date")

    def valueInt = column[Option[Int]]("value_int")

    def valueString = column[Option[String]]("value_str")

    def valueFloat = column[Option[Float]]("value_float")

    override def * = (id, taskId, deviceId, status, date, valueInt, valueString, valueFloat)

    def taskFk = foreignKey("task_fk", taskId, tasks)(_.id)

    def deviceFk = foreignKey("device_fk", deviceId, devices)(_.id)
  }

  val taskResults = TableQuery[TaskResults]

  class ResultFiles(tag: Tag) extends Table[(Int, String)](tag, "result_files") {
    def resultId = column[Int]("result_id")

    def filename = column[String]("filename")

    override def * = (resultId, filename)

    def taskResultFk = foreignKey("task_result_fk", resultId, taskResults)(_.id)
  }

}