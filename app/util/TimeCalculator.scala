package util

import play.api.Logger

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-03-21
 * Time: 21:13
 */
object TimeCalculator {

  def calculatePace(duration: String, trackLength: Double) = {
    val durationInMinutes = durationInMinutesFromString(duration)
    durationInMinutes / trackLength
  }

  def durationInMinutesFromString(durationString: String) = {
    val times = durationString.split(":")
    var duration = times.apply(1).toDouble
    if (!times.apply(0).equalsIgnoreCase("00")) {
      val hours = times.apply(0).toInt
      duration += hours * 60
    }
    val seconds = times.apply(2).toInt
    duration += (seconds / 60)
    duration
  }

}
