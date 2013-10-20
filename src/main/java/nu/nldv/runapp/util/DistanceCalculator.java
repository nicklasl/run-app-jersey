package nu.nldv.runapp.util;

import nu.nldv.runapp.model.Coordinates;
import nu.nldv.runapp.model.Point;
import nu.nldv.runapp.model.Segment;
import nu.nldv.runapp.model.Track;


/**
 * This uses the �haversine� formula to calculate the great-circle distance between two points � that is, the shortest
 * distance over the earth�s surface � giving an �as-the-crow-flies� distance between the points (ignoring any hills, of
 * course!).
 * <p/>
 * http://www.movable-type.co.uk/scripts/latlong.html
 *
 * @author Nicklas Lundin
 */
public class DistanceCalculator {
    private static final int R = 6378; // km

    /**
     * Returns the distance between two {@link Coordinates}
     *
     * @param l1 First {@link Coordinates}
     * @param l2 Second {@link Coordinates}
     * @return the distance (in km) between the two coordinates
     */
    private static double calculateDistance(Coordinates l1, Coordinates l2) {

        double dLat = Math.toRadians(l2.getLatitude() - l1.getLatitude());
        double dLon = Math.toRadians(l2.getLongitude() - l1.getLongitude());
        double lat1 = Math.toRadians(l1.getLatitude());
        double lat2 = Math.toRadians(l2.getLatitude());

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1)
                * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;

        return d;
    }

    /**
     * Calculates the distance of all the segments in a track
     *
     * @param track the track to calculate
     * @return the total distance in km.
     */
    public static double calculateDistanceOfTrack(Track track) {
        double totalDistanceInKm = 0;
        for (Segment segment : track.getSegments()) {
            for (int i = 0; i < segment.getPoints().size() - 1; i++) {
                Point p1 = segment.getPoints().get(i);
                Point p2 = segment.getPoints().get(i + 1);
                totalDistanceInKm += DistanceCalculator.calculateDistance(p1.getCoordinates(), p2.getCoordinates());
            }
        }
        return totalDistanceInKm;
    }

}
