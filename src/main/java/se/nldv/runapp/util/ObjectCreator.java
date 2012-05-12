package se.nldv.runapp.util;

import se.nldv.runapp.model.Track;

import com.google.gson.Gson;

public class ObjectCreator {

	public static Track createJavaObject(String track) {
		Gson gson=new Gson();
		//convert the json string back to object
		Track obj = gson.fromJson(track, Track.class);
 
		return obj;
	}

}
