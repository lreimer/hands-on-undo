package hands.on.undo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PartsRepository {

    @Inject
    private VehicleClient client;
    private Map<String, Collection<Part>> parts = new HashMap<>();

    @PostConstruct
    public void initialize() {
        parts.put("BMW-E20-4711", List.of(
            new Part("Tires", "1234567890"), 
            new Part("Door (right", "1234567891"),
            new Part("Door (left)", "1234567892"),
            new Part("Engine", "1234567893"),
            new Part("Chassis", "1234567894")
        ));
    }

    public Collection<Part> findParts(String vin17) {
        if (vin17 == null) {
            return Collections.emptyList();
        }

        Vehicle vehicle = client.getVehicle(vin17);
        String key = String.format("%s-%s-%s", vehicle.getBrand(), vehicle.getModel(), vehicle.getType());
        return parts.get(key);
    }
    
}
