package nu.nldv.runapp;

import nu.nldv.runapp.model.Track;
import nu.nldv.runapp.util.StoreHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

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
        if (tracks == null) getTracks();
        return tracks.size();
    }

    public boolean storeTrack(Track track) {
        tracks = null; //nulls the "cache".
        return storeHelper.storeTrack(track);
    }

    public Track getTrackById(int id) {
        getTracks();
        for (Track track : tracks) {
            if (track.getId() == id) {
                return track;
            }
        }
        throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
}
