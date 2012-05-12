package se.nldv.runapp.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.nldv.runapp.model.Track;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class StoreHelper {

	public static boolean storeTrack(Track track) {
		File f=new File("Tracks\\Track_"+trim(track.getStartDate())+".json");
		if(f.exists()){
			System.err.println("Trying to write to a file that already exists");
			return false;
		}
		System.out.println("Storing file "+f.getName()+"@"+f.getAbsolutePath());
		try {
			f.createNewFile();
			BufferedWriter bw=new BufferedWriter(new FileWriter(f));
			String jsonToWrite=new Gson().toJson(track);
			System.out.println("writing "+jsonToWrite);
			bw.write(jsonToWrite);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static String trim(String startDate) {
		String returnString=startDate.replaceAll(":","-");
		return returnString.replaceAll(" ", "");
	}

	public static List<Track> loadTracks() {
		List<Track> tracks=new ArrayList<Track>();
		Gson gson = new Gson();
		File file=new File("Tracks\\");
		System.out.println(file.getAbsolutePath());
		assert(file.isDirectory());
		File[] files=file.listFiles();
		for(File f:files){
			System.out.println("Reading file "+f.getAbsolutePath());
			try {
				BufferedReader br=new BufferedReader(new FileReader(f));
				String s = "";
				s=br.readLine();
				System.out.println(s.length());
				Track t=gson.fromJson(s, Track.class);
				tracks.add(t);
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tracks;
	}

}
