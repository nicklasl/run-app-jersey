package util

import model.Track
import java.io.File
import play.api.{Logger, Play}
import play.api.Play.current
import play.api.libs.json.{Json, JsValue}
import play.api.libs.Files
import play.api.cache.Cache


/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-01-25
 * Time: 16:09
 */
object StoreHelper {

  private def fileToJson(file: File): List[JsValue] = {
    val string: String = Files.readFile(file)
    List(Json.parse(string))
  }

  def listTracks: List[Track] = {
    val option: Option[List[Track]] = Cache.getAs[List[Track]]("tracks")
    if (option.isDefined) option.get
    else {
      val file: Option[File] = Play.getExistingFile("Tracks")
      if (file.isDefined) {
        val jsValues: List[JsValue] = file.get.listFiles().flatMap(fileToJson(_)).toList
        val tracks = jsValues.map(js => js.as[Track]).map {
          track =>
            Logger.debug("mapping " + track.id+" : "+track.date)
            val distance = DistanceCalculator.calculateDistanceOfTrack(track)
            track.copy(
              distance = distance,
              pace = Some(TimeCalculator.calculatePace(track.duration.get, distance.get))
            )
        }.sortBy(track => track.id)
        Cache.set("tracks", tracks)
        tracks
      } else {
        List.empty
      }
    }
  }

  def storeTrack(track: Track, fileName: String) = {
    Cache.remove("tracks")
    val dir = Play.getExistingFile("Tracks")
    val file = new File(dir.get.getAbsolutePath + File.separator + fileName)
    Files.writeFile(file, Json.toJson(track).toString())
    track.id
  }

  def nextId: Int = listTracks.length

}
