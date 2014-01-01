package nu.nldv.runapp.util;

import nu.nldv.runapp.model.Coordinates;
import nu.nldv.runapp.model.Point;
import nu.nldv.runapp.model.Segment;
import nu.nldv.runapp.model.Track;
import nu.nldv.runapp.model.gpx.GpxType;
import nu.nldv.runapp.model.gpx.TrkType;
import nu.nldv.runapp.model.gpx.TrksegType;
import nu.nldv.runapp.model.gpx.WptType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2014-01-01
 * Time: 16:53
 */
@Service
public class GpxMapper {

    @Autowired
    private TimeCalculator timeCalculator;

    public List<Track> map(GpxType gpx) {
        List<Track> tracks = new ArrayList<>();
        for (TrkType trk: gpx.getTrk()) {
            Track track = new Track();
            track.setSegments(mapSegments(trk.getTrkseg()));
            track.setStartDate(track.getSegments().get(0).getPoints().get(0).getDate());
            track.setDuration(timeCalculator.getDurationBetweenDates(track.getStartDate(),getLastPointDate(track)));
            tracks.add(track);
        }

        return tracks;
    }

    private Date getLastPointDate(Track track) {
        Segment lastSegment= getLastSegment(track);
        return lastSegment.getPoints().get(lastSegment.getPoints().size()-1).getDate();

    }

    private Segment getLastSegment(Track track) {
        return track.getSegments().get(track.getSegments().size()-1);
    }

    private List<Segment> mapSegments(List<TrksegType> trksegs) {
        List<Segment> segments = new ArrayList<>();
        for (TrksegType trksegType : trksegs) {
            Segment segment = new Segment();
            segment.setPoints(mapPoints(trksegType.getTrkpt()));
            segments.add(segment);
        }

        return segments;
    }

    private List<Point> mapPoints(List<WptType> trkpts) {
        List<Point> points = new ArrayList<>();
        for (WptType trkpt : trkpts) {
            Point point = new Point();
            point.setElevation(trkpt.getEle().doubleValue());
            point.setCoordinates(mapCoordinates(trkpt));
            point.setDate(trkpt.getTime().toGregorianCalendar().getTime());
            points.add(point);
        }

        return points;
    }

    private Coordinates mapCoordinates(WptType trkpt) {
        Coordinates coordinate=new Coordinates();
        coordinate.setLatitude(trkpt.getLat().doubleValue());
        coordinate.setLongitude(trkpt.getLon().doubleValue());
        return coordinate;
    }
}
