package nu.nldv.runapp.util;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import nu.nldv.runapp.model.Track;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TimeCalculatorTest {

    @Autowired
    StoreHelper storeHelper;
    @Autowired
    TimeCalculator timeCalculator;
    @Autowired
    DistanceCalculator distanceCalculator;

    @Test
    public void test() {
        Assert.assertEquals(6.0, timeCalculator.calculatePace("01:00:00", 10));
    }

    List<Track> tracks;

    @Before
    public void loadTracks() {
        tracks = storeHelper.loadTracks();
    }

    @Test
    public void testThatTracksWereLoaded() {
        Assert.assertNotNull(tracks);
    }

    @Test
    public void test2() {
        for (Track track : tracks) {
            double distance = distanceCalculator.calculateDistanceOfTrack(track);
            System.out.println("On " + track.getStartDate());
            System.out.println("I ran " + distance + "km in " + track.getDuration());
            System.out.println("That is pace=" + timeCalculator.calculatePace(track.getDuration(), distance) + " min/km");
            System.out.println("--------");
        }
    }

    @Test
    public void testDateDifference() throws InterruptedException {
        Date d1 = new Date();
        Thread.sleep(61000);
        Date d2 = new Date();
        String durationBetweenDates = timeCalculator.getDurationBetweenDates(d1, d2);
        System.out.println(durationBetweenDates);
    }
}
