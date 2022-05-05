package hands.on.undo.vehicle;

import javax.inject.Inject;
import javax.validation.constraints.Size;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vehicle")
public class VehicleResource {

    @Inject
    VehicleRepository repository;

    @GET
    @Path("{vin17}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicle(@PathParam("vin17") @Size(min = 17, max = 17) String vin17) {
        Vehicle vehicle = repository.findByVin17(vin17);
        // if (vehicle == null) {
        //    return Response.status(Status.NOT_FOUND).build();
        // }
        return Response.ok(vehicle).build();
    }
}