import nu.nldv.runapp.model.Track;
import nu.nldv.runapp.util.StoreHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2013-12-28
 * Time: 15:40
 */
public class ResetTrackIds {

    private StoreHelper storeHelper;

    public static void main(String[] args){
        System.out.println("Resetting tracks.");
        new ResetTrackIds().run();
    }

    public void run(){
        storeHelper = new StoreHelper();
        List<Track> tracks = storeHelper.loadTracks();
        sortOnDate(tracks);
        resetIds(tracks);
        for (Track track : tracks) {
            storeHelper.storeTrack(track);
        }

    }

    private void resetIds(List<Track> tracks) {
        for(int i =0;i<tracks.size(); i++){
            tracks.get(i).setId(i+1);
        }
    }

    private void printIds(List<Track> tracks) {
        for (Track track : tracks) {
            System.out.println(track.getId());
        }


    }

    private void sortOnDate(List<Track> tracks) {
        Collections.sort(tracks, new DateComparator());
    }


    private class DateComparator implements Comparator<Track> {

        SimpleDateFormat sdf;

        public DateComparator(){
            //Fri Jun 01 2012 04:18:39 GMT+0200
            sdf=new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.US);
        }

        @Override
        public int compare(Track o1, Track o2) {
            if(o1.getStartDate()==null) return -1;
            if(o2.getStartDate()==null) return 1;
            try {
                return parseDate(o1.getStartDate()).compareTo(parseDate(o2.getStartDate()));
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        }

        private Date parseDate(String startDate) throws ParseException {
            return sdf.parse(startDate);
        }
    }
}
