package controllers

import play.api.mvc.{Action, Controller}
import util.StoreHelper
import play.api.libs.json.{JsValue, Json}
import model.Track
import play.api.Logger
import java.io.File

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-01-25
 * Time: 16:05
 */
object TrackController extends Controller {

  def listTracks = Action {
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

  def storeGpx = Action(parse.multipartFormData)  {
    request =>
      request.body.file("gpx").map { gpxFile =>
        val filename = gpxFile.filename
        gpxFile.ref.moveTo(new File(s"/tmp/track/$filename"))
        Ok("File uploaded")
      }.getOrElse {
        Redirect(routes.Application.index).flashing(
          "error" -> "Missing file")
      }

  }
}
