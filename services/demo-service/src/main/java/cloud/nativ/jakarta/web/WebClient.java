package cloud.nativ.jakarta.web;

import cloud.nativ.jakarta.ApplicationPropertiesReader;
import cloud.nativ.jakarta.dto.VehicleRecordResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class WebClient {

    @Inject
    private Client client;

    public VehicleRecordResponse handleRequest(final String id) {
        return client.target(ApplicationPropertiesReader.getValue("vehicle.record.url"))
                .queryParam("id", id)
                .request(MediaType.APPLICATION_JSON)
                .get(VehicleRecordResponse.class);
    }
}
