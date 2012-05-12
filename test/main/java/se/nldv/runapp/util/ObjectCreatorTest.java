package se.nldv.runapp.util;

import java.util.ArrayList;

import org.junit.Test;

import se.nldv.runapp.model.Point;
import se.nldv.runapp.model.Segment;
import se.nldv.runapp.model.Track;

import com.google.gson.Gson;

public class ObjectCreatorTest {

	private static final String TEST_STRING="{\"segments\":[{\"points\":[{\"elevation\":\"52.048583984375\",\"latitude\":\"59.255392653867602\",\"longitude\":\"17.996923690661788\"},{\"elevation\":\"51.087158203125\",\"latitude\":\"59.255413776263595\",\"longitude\":\"17.996982196345925\"},{\"elevation\":\"44.3580322265625\",\"latitude\":\"59.255488039925694\",\"longitude\":\"17.997046820819378\"},{\"elevation\":\"41.9547119140625\",\"latitude\":\"59.255558699369431\",\"longitude\":\"17.997112451121211\"},{\"elevation\":\"40.0321044921875\",\"latitude\":\"59.255694653838873\",\"longitude\":\"17.997217979282141\"},{\"elevation\":\"39.0706787109375\",\"latitude\":\"59.255799679085612\",\"longitude\":\"17.997236084192991\"},{\"elevation\":\"40.0321044921875\",\"latitude\":\"59.255855418741703\",\"longitude\":\"17.997241280972958\"},{\"elevation\":\"40.0321044921875\",\"latitude\":\"59.255918199196458\",\"longitude\":\"17.997220745310187\"},{\"elevation\":\"40.5128173828125\",\"latitude\":\"59.255940662696958\",\"longitude\":\"17.997204316779971\"},{\"elevation\":\"38.109375\",\"latitude\":\"59.256102768704295\",\"longitude\":\"17.997038941830397\"},{\"elevation\":\"35.2254638671875\",\"latitude\":\"59.256197484210134\",\"longitude\":\"17.996804835274816\"},{\"elevation\":\"31.380126953125\",\"latitude\":\"59.256236543878913\",\"longitude\":\"17.996478779241443\"},{\"elevation\":\"28.9769287109375\",\"latitude\":\"59.25625154748559\",\"longitude\":\"17.996255150064826\"},{\"elevation\":\"26.5736083984375\",\"latitude\":\"59.256338384002447\",\"longitude\":\"17.996281553059816\"},{\"elevation\":\"30.4188232421875\",\"latitude\":\"59.256506441161036\",\"longitude\":\"17.996228160336614\"},{\"elevation\":\"35.2254638671875\",\"latitude\":\"59.25664097070694\",\"longitude\":\"17.996211228892207\"},{\"elevation\":\"48.203369140625\",\"latitude\":\"59.256766531616449\",\"longitude\":\"17.996094888076186\"},{\"elevation\":\"50.6065673828125\",\"latitude\":\"59.256861582398415\",\"longitude\":\"17.996095893904567\"},{\"elevation\":\"53.97119140625\",\"latitude\":\"59.256942383944988\",\"longitude\":\"17.996121039614081\"},{\"elevation\":\"51.56787109375\",\"latitude\":\"59.257076662033796\",\"longitude\":\"17.996099079027772\"}]}]}";
	@Test
	public void test() {
		ObjectCreator.createJavaObject(TEST_STRING);
	}
	
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
    	
		String json = gson.toJson(t);
		System.out.println(json);
	}

}
