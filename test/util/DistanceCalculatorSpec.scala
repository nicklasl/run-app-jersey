package util

import org.specs2.mutable.Specification
import model.{Point, Track, Segment, Coordinates}
import org.specs2.matcher.Delta
import org.joda.time.DateTime

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-03-21
 * Time: 21:59
 */
class DistanceCalculatorSpec extends Specification {

  "DistanceCalculator" should {

    "Calculate distance between 2 points in sthlm" in {
      val p1 = Coordinates(59.321148, 18.073797)
      val p2 = Coordinates(59.330596, 18.056116)
      val distanceBetweenCoordinates = DistanceCalculator.distanceBetweenCoordinates(p1, p2)
      distanceBetweenCoordinates must beCloseTo(Delta[Double](1.5D, 0.1)) //10 000 km
    }


    "Calculate a distance between 6 points in one segment" in {
      val p1 = Point(0, Coordinates(59.311973, 17.985134), null, None)
      val p2 = Point(0, Coordinates(59.316572, 17.976594), null, None)
      val p3 = Point(0, Coordinates(59.313615, 17.967796), null, None)
      val p4 = Point(0, Coordinates(59.308819, 17.971058), null, None)
      val p5 = Point(0, Coordinates(59.310396, 17.980757), null, None)
      val p6 = Point(0, Coordinates(59.311272, 17.982602), null, None)

      val segments = List(Segment(List(p1, p2, p3, p4, p5, p6)))

      val track = Track(0, segments, None, None, None, DateTime.now, None) //2.6 km

      val distanceOfTrack: Double = DistanceCalculator.calculateDistanceOfTrack(track).get
      distanceOfTrack must beCloseTo(Delta[Double](2.6D, 0.1))
    }


    "Calculate a distance between 6 points in two segments" in {
      val p1 = Point(0, Coordinates(59.311973, 17.985134), null, None)
      val p2 = Point(0, Coordinates(59.316572, 17.976594), null, None)
      val p3 = Point(0, Coordinates(59.313615, 17.967796), null, None)
      val p4 = Point(0, Coordinates(59.308819, 17.971058), null, None)
      val p5 = Point(0, Coordinates(59.310396, 17.980757), null, None)
      val p6 = Point(0, Coordinates(59.311272, 17.982602), null, None)

      val segments = List(Segment(List(p1, p2, p3, p4)),Segment(List(p5, p6)))

      val track = Track(0, segments, None, None, None, DateTime.now, None) //2.6 km

      val distanceOfTrack: Double = DistanceCalculator.calculateDistanceOfTrack(track).get
      distanceOfTrack must beCloseTo(Delta[Double](2.6D, 0.1))
    }
  }

}
