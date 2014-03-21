package model

import play.api.libs.json._
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsString
import scala.Some
import play.api.libs.json.JsNumber
import play.api.data.validation.ValidationError
import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import java.util.Locale

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-01-25
 * Time: 16:13
 */
case class Point(elevation: Double, coordinates: Coordinates, dateTime:DateTime, date:Option[String])

object Point {
  implicit val reads = Json.reads[Point]
  implicit val writes = Json.writes[Point]
}