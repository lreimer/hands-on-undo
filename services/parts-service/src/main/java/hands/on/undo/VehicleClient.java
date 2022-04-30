package hands.on.undo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

public interface VehicleClient {

    @GET
    @Path("/api/vehicles/{vin17}")
    @Consumes(MediaType.APPLICATION_JSON)
    Vehicle getVehicle(@PathParam("vin17") String vin17);
    
}
