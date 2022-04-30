package hands.on.undo.vehicle;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VehicleRepository {

    private Map<String, Vehicle> vehicles = new HashMap<>();

    @PostConstruct
    public void initialize() {
        vehicles.put("B079V9ZDNY", new Vehicle("B079V9ZDNY", "BMW", "E20", "4711"));
    }

    public Collection<Vehicle> findAll() {
        return vehicles.values();
    }

    public Vehicle findByVin17(String vin17) {
        return vehicles.get(vin17);
    }
    
}
