package nu.nldv.runapp.util;

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
public class DistanceCalculatorTest {

    @Autowired
    StoreHelper storeHelper;
    @Autowired
    DistanceCalculator distanceCalculator;

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
    public void testDistance() {
        for (Track track : tracks) {
            System.out.println("Track has duration:" + track.getDuration());
            double totalDistanceInKm = distanceCalculator.calculateDistanceOfTrack(track);
            System.out.println(totalDistanceInKm);
        }
    }

}
