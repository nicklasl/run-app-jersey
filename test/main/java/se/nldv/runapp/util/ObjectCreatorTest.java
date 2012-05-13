package se.nldv.runapp.util;

import java.util.ArrayList;

import org.junit.Test;

import se.nldv.runapp.model.Coordinates;
import se.nldv.runapp.model.Point;
import se.nldv.runapp.model.Segment;
import se.nldv.runapp.model.Track;

import com.google.gson.Gson;

public class ObjectCreatorTest {

	@Test
	public void test2(){
		Gson gson = new Gson();
		 
		// convert java object to JSON format,
		// and returned as JSON formatted string
	 	Track t=new Track();
    	t.setSegments(new ArrayList<Segment>());
    	t.getSegments().add(new Segment());
    	t.getSegments().get(0).setPoints(new ArrayList<Point>());
    	t.getSegments().get(0).getPoints().add(new Point());
    	t.getSegments().get(0).getPoints().add(new Point());
    	t.getSegments().get(0).getPoints().get(0).setDate("asdf");
    	t.getSegments().get(0).getPoints().get(0).setElevation(200.0);
    	t.getSegments().get(0).getPoints().get(0).setCoordinates(new Coordinates());
    	t.getSegments().get(0).getPoints().get(0).getCoordinates().setLatitude(230.0);
    	t.getSegments().get(0).getPoints().get(0).getCoordinates().setLongitude(230.0);
    	
		String json = gson.toJson(t);
		System.out.println(json);
	}

}
