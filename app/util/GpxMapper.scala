package util

import nu.nldv.parsethatgpx.model.{Point, Segment, Gpx}
import model.{Coordinates, Track}
import org.joda.time.Period

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-03-23
 * Time: 20:16
 */
object GpxMapper {

  def duration(segments: List[Segment]): Option[String] = {
    val duration = Period.millis((segments.takeRight(1).head.points.takeRight(1).head.time.getMillis - segments.head.points.head.time.getMillis).toInt).toStandardDuration
    val allSeconds = duration.getStandardSeconds
    val hours = allSeconds / 3600
    val minutes = (allSeconds - hours * 3600) / 60
    val seconds = (allSeconds - (hours * 3600) - (minutes * 60))
    val str = if (hours > 10) "" + hours else "0" + hours + ":" + (if (minutes > 10) minutes else "0" + minutes) + ":" + (if (seconds > 10) seconds else "0" + seconds)
    Some(str)
  }

  def points(points: List[Point]): List[model.Point] = points.map(point => model.Point(point.elevation, Coordinates(point.latitude, point.longitude), point.time, None))

  def toSegments(segments: List[Segment]): List[model.Segment] = segments.map(segment => model.Segment(points(segment.points)))

  def toTrack(gpx: Gpx): Track = Track(id = -1,
    segments = toSegments(gpx.track.segments),
    duration = duration(gpx.track.segments),
    distance = None,
    pace = None,
    date = gpx.track.time,
    startDate = None)

}
