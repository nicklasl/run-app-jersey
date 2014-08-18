package util

import model.{Segment, Point, Coordinates, Track}
import scala.annotation.tailrec

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-03-21
 * Time: 20:46
 */
object DistanceCalculator {

  val R = 6371 //Mean radius

  @tailrec
  private def iter(points: List[Point], sum: Double): Double = {
    if (points.length < 2) {
      sum
    } else iter(points.tail, sum + distanceBetweenCoordinates(points.head.coordinates, points.tail.head.coordinates))
  }

  def calculateDistanceOfTrack(track: Track): Option[Double] = {
    Some(iter(track.segments.flatten(segment => segment.points), 0))
  }


  def distanceBetweenCoordinates(l1: Coordinates, l2: Coordinates): Double = {
    val dLat = Math.toRadians(l2.latitude - l1.latitude)
    val dLon = Math.toRadians(l2.longitude - l1.longitude)
    val lat1 = Math.toRadians(l1.latitude)
    val lat2 = Math.toRadians(l2.latitude)

    val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2)
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    R * c
  }

}
