package nu.nldv.runapp.util;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import nu.nldv.runapp.model.Track;

public class TimeCalculatorTest {

    StoreHelper storeHelper = new StoreHelper();

    @Test
    public void test() {
        Assert.assertEquals(6.0, TimeCalculator.calculatePace("01:00:00", 10));
    }

    List<Track> tracks;

    @Before
    public void loadTracks() {
        tracks = storeHelper.loadTracks();
    }

    @Test
    public void testThatTracksWereLoaded() {
        Assert.assertNotNull(tracks);
        Assert.assertFalse(tracks.isEmpty());
    }

    @Test
    public void test2() {
        for (Track track : tracks) {
            double distance = DistanceCalculator.calculateDistanceOfTrack(track);
            System.out.println("On " + track.getStartDate());
            System.out.println("I ran " + distance + "km in " + track.getDuration());
            System.out.println("That is pace=" + TimeCalculator.calculatePace(track.getDuration(), distance) + " min/km");
            System.out.println("--------");
        }
    }
}
