
package nu.nldv.runapp;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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


    /**
     * Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     *
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Track> getIt() {
        return controller.getTracks();
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


}
