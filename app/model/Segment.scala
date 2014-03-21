package model

import play.api.libs.json.Json

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-01-25
 * Time: 16:11
 */
case class Segment(points:List[Point])

object Segment {
  implicit val reads = Json.reads[Segment]
  implicit val writes = Json.writes[Segment]
}