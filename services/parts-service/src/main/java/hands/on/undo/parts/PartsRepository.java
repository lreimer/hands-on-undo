package hands.on.undo.parts;

import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import hands.on.undo.vehicle.Vehicle;
import hands.on.undo.vehicle.VehicleClient;

@ApplicationScoped
public class PartsRepository {

    @Inject
    @ConfigProperty(name = "vehicle.base.uri", defaultValue = "http://localhost:8081/api")
    private URI baseUri;

    private VehicleClient client;
    private Map<String, Collection<Part>> parts = new HashMap<>();

    @PostConstruct
    public void initialize() {
        parts.put("VW-1J-2003", List.of(
            new Part("Tires", "1234567890"), 
            new Part("Door (right", "1234567891"),
            new Part("Door (left)", "1234567892"),
            new Part("Engine", "1234567893"),
            new Part("Chassis", "1234567894")
        ));

        this.client = RestClientBuilder.newBuilder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .baseUri(baseUri)
                        .build(VehicleClient.class);
    }

    public Collection<Part> findParts(String vin17) {
        System.out.printf("vin17 = %s\n", vin17);
        if (vin17 == null) {
            return Collections.emptyList();
        }

        Vehicle vehicle = client.getVehicle(vin17);
        System.out.printf("vin17 = %s, vehicle = %s\n", vin17, vehicle);
        String key = String.format("%s-%s-%s", vehicle.getBrand(), vehicle.getModel(), vehicle.getYear());
        return parts.get(key);
    }
    
}
