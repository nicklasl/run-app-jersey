package controllers

import play.api.mvc.{Action, Controller}
import util.{GpxMapper, StoreHelper}
import play.api.libs.json.{JsValue, Json}
import model.Track
import play.api.Play
import java.io.File
import nu.nldv.parsethatgpx.controllers.ParseThatGpx
import scala.concurrent.Future

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-01-25
 * Time: 16:05
 */
object TrackController extends Controller {

  def listTracks = Action {
    implicit request =>
      Ok(Json.toJson(StoreHelper.listTracks)).as("application/json")
  }

  def singleTrack(id: Int) = Action {
    Ok(Json.toJson(StoreHelper.listTracks.filter(track => track.id == id).head)).as("application/json")
  }

  def storeTrack = Action {
    implicit request =>
      val jsonOption: Option[JsValue] = request.body.asJson
      if (jsonOption.isDefined) {
        val track = jsonOption.get.as[Track]
        val id = StoreHelper.storeTrack(track.copy(id = StoreHelper.nextId), fileName = "Track_" + StoreHelper.nextId + ".json")
        Ok(Json.toJson(track.copy(id = id))).as("application/json")
      }
      else BadRequest
  }

  def storeGpx = Action.async(parse.multipartFormData) {
    request =>
      val ids: Seq[Int] = for {
        gpxFile <- request.body.files
      } yield {
        val filename = gpxFile.filename
        val file: File = Play.current.getFile(s"/tmp/gpx/$filename")
        gpxFile.ref.moveTo(file, replace = true)
        val gpx = ParseThatGpx.parse(file)
        val track = GpxMapper.toTrack(gpx)
        StoreHelper.storeTrack(track.copy(id = StoreHelper.nextId), fileName = "Track_" + StoreHelper.nextId + ".json")
      }

      Future.successful(Ok("File(s) uploaded with id=" + ids))

  }

}
