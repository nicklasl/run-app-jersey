package nu.nldv.runapp.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import nu.nldv.runapp.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StoreHelper {

    @Autowired
    private DistanceCalculator distanceCalculator;

    @Autowired
    private TimeCalculator timeCalculator;

    Gson gson = new Gson();

    public boolean storeTrack(Track track) {
        File f = new File("Tracks"+File.separator+"Track_" + track.getId() + ".json");
        if (f.exists()) {
            System.err.println("Trying to write to a file that already exists");
            return false;
        }
        try {
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            String jsonToWrite = new Gson().toJson(track);
            bw.write(jsonToWrite);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private String trim(String startDate) {
        String returnString = startDate.replaceAll(":", "-");
        return returnString.replaceAll(" ", "");
    }

    public List<Track> loadTracks() {
        List<Track> tracks = new ArrayList<>();
        File file = new File("Tracks" + File.separator);
        assert (file.isDirectory());
        File[] files = file.listFiles();
        for (File f : files) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String s = "";
                s = br.readLine();
                Track t = gson.fromJson(s, Track.class);
                t.setDistance(distanceCalculator.calculateDistanceOfTrack(t));
                t.setPace(timeCalculator.calculatePace(t.getDuration(), t.getDistance()));
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
