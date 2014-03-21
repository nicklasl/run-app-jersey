package model

import play.api.libs.json._
import org.joda.time.DateTime
import play.api.data.validation.ValidationError


/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-01-25
 * Time: 16:10
 */
case class Track(id: Int, segments: List[Segment], duration: Option[String], distance: Double, pace: Double, date: DateTime, startDate:Option[String])

object Track {
  implicit val reads = Json.reads[Track]
  implicit val writes = Json.writes[Track]

}