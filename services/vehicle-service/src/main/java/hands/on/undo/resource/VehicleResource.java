package hands.on.undo.resource;

import hands.on.undo.dto.VehicleRecordResponse;
import hands.on.undo.service.VehicleRecordService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path(value = "vehicle-record")
public class VehicleResource {

    @Inject
    private VehicleRecordService vehicleRecordService;

    @GET
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public VehicleRecordResponse findVehicleRecord(@QueryParam(value = "id") final String id) {
        return vehicleRecordService.find(id);
    }
}
