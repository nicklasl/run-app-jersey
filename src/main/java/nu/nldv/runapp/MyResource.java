
package nu.nldv.runapp;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nu.nldv.runapp.model.gpx.GpxType;
import nu.nldv.runapp.util.GpxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nu.nldv.runapp.model.Track;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Service
@Path("/tracks")
public class MyResource {


    @Autowired
    private Controller controller;
    @Autowired
    private GpxMapper gpxMapper;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Track> getAllTracks() {
        List<Track> tracks = controller.getTracks();
        return tracks;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Track getSingleTrack(@PathParam("id") int id) {
        Track track = controller.getTrackById(id);
        return track;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putIt(Track track) {
        track.setId(controller.getNextFreeId());
        if (controller.storeTrack(track)) {
            return Response.status(Response.Status.OK).entity("Received and stored a track.").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Failed to store a track.").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response putXml(GpxType gpx){
        List<Track> tracks = gpxMapper.map(gpx);
        int numOfSuccess=0;
        for (Track track : tracks) {
            track.setId(controller.getNextFreeId());
            boolean success = controller.storeTrack(track);
            if(success)numOfSuccess+=1;
        }
        return Response.status(Response.Status.OK).entity("Succeeded in storing "+numOfSuccess+" of "+tracks.size()+" tracks.").build();
    }

}
