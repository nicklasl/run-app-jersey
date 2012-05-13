
package se.nldv.runapp;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.nldv.runapp.model.Track;
import se.nldv.runapp.util.StoreHelper;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MyResource {
	
	private static List<Track> tracks=new ArrayList<Track>();
	
	static{
		tracks.addAll(StoreHelper.loadTracks());
		System.out.println("Loaded "+tracks.size()+" tracks");
	}
    

	/** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public List<Track> getIt() {
    	System.out.println("meh");
    	return tracks;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putIt(Track track){
    	if(StoreHelper.storeTrack(track)){
    		tracks.add(track);
    		return Response.status(201).entity("Received and stored a track.").build();
    	}
    	return Response.status(201).entity("Failed to retrieve and/or store a track.").build();
    }
    
}
