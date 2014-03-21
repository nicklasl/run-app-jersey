package model

import play.api.libs.json.Json

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-01-25
 * Time: 17:03
 */
case class Coordinates(latitude: Double, longitude: Double)

object Coordinates {
  implicit val reads = Json.reads[Coordinates]
  implicit val writes = Json.writes[Coordinates]
}