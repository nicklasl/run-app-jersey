package util

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.test.WithApplication
import model.Track
import scala.collection.mutable.ListBuffer
import org.joda.time.DateTime
import org.joda.time.format._
import java.util.Locale

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-01-25
 * Time: 16:28
 */
@RunWith(classOf[JUnitRunner])
class StoreHelperSpec extends Specification {

  "StoreHelper" should {

    val tracks: ListBuffer[Track] = new ListBuffer[Track]

    "fetch tracks" in new WithApplication {
      tracks.appendAll(StoreHelper.listTracks)

      tracks.foreach(t => {
        println(t.date)
        //StoreHelper.storeTrack(t.copy(startDate = None, segments = t.segments.map(s=>s.copy(points = s.points.map(p=>p.copy(date = None))))), "Track"+t.id+".json")
      })

      tracks.isEmpty must beFalse
    }


  }

  val pattern = "MMM dd, yyyy h:mm:ss a"
  val df: DateTimeFormatter = DateTimeFormat.forPattern(pattern).withLocale(Locale.US);

  def parseDate(input: String): Option[DateTime] = scala.util.control.Exception.allCatch[DateTime] opt (DateTime.parse(input, df))
}
