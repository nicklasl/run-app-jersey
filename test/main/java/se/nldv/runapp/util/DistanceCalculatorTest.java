package se.nldv.runapp.util;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import se.nldv.runapp.model.Track;

public class DistanceCalculatorTest {

	List<Track> tracks;
	
	@Before
	public void loadTracks() {
		tracks=StoreHelper.loadTracks();
	}
	
	@Test
	public void testThatTracksWereLoaded(){
		Assert.assertNotNull(tracks);
		Assert.assertFalse(tracks.isEmpty());
	}
	
	@Test
	public void testDistance(){
		for(Track track:tracks){
			System.out.println("Track has duration:"+track.getDuration());
			double totalDistanceInKm=DistanceCalculator.calculateDistanceOfTrack(track);
			System.out.println(totalDistanceInKm);
		}
	}

}
