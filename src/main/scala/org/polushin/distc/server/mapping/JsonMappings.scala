package org.polushin.distc.server.mapping

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import org.polushin.distc.server.models.{Task, TaskStatus}
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, JsonFormat}

trait JsonMappings extends DefaultJsonProtocol with SprayJsonSupport {

  def jsonEnum[T <: Enumeration](enum: T) = new JsonFormat[T#Value] {

    def write(obj: T#Value) = JsString(obj.toString)

    def read(json: JsValue): enum.Value = json match {
      case JsString(txt) => enum.withName(txt)
      case something => throw DeserializationException(s"Expected a value from enum $enum instead of $something")
    }
  }

  implicit val taskStatusFormat = jsonEnum(TaskStatus)
  implicit val taskFormat = jsonFormat5(Task)

}
