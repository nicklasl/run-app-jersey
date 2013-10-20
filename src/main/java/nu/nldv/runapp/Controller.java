package nu.nldv.runapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nu.nldv.runapp.model.Track;
import nu.nldv.runapp.util.StoreHelper;

@Service
public final class Controller {

    @Autowired
    private StoreHelper storeHelper;

    private List<Track> tracks;

    public List<Track> getTracks() {
        if (tracks == null) {
            tracks = new ArrayList<>();
            tracks.addAll(storeHelper.loadTracks());
        }
        return tracks;
    }

    public int getNextFreeId() {
        return tracks.size();
    }

    public boolean storeTrack(Track track) {
        tracks = null; //nulls the "cache".
        return storeHelper.storeTrack(track);
    }
}
