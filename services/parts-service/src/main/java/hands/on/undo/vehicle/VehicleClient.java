package hands.on.undo.vehicle;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/vehicle")
@Consumes(MediaType.APPLICATION_JSON)
public interface VehicleClient {

    @GET
    @Path("{vin17}")
    Vehicle getVehicle(@PathParam("vin17") String vin17);
    
}
