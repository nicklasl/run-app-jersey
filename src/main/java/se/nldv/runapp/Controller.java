package se.nldv.runapp;

import java.util.ArrayList;
import java.util.List;

import se.nldv.runapp.model.Track;
import se.nldv.runapp.util.StoreHelper;

public final class Controller {

	private static List<Track> tracks=new ArrayList<Track>();
	
	static{
		getTracks().addAll(StoreHelper.loadTracks());
		System.out.println("Loaded "+getTracks().size()+" tracks");
	}

	public static List<Track> getTracks() {
		return tracks;
	}

	public static int getNextFreeId() {
		return tracks.size();
	}

}
