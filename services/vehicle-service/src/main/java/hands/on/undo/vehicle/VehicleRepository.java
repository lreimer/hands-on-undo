package hands.on.undo.vehicle;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

@Dependent
public class VehicleRepository {

    private Map<String, Vehicle> vehicles = new HashMap<>();

    @PostConstruct
    public void initialize() {
        vehicles.put("WVWZZZ1JZ3W386752", new Vehicle("WVWZZZ1JZ3W386752", "VW", "1J", "2003"));
    }

    public Collection<Vehicle> findAll() {
        return vehicles.values();
    }

    public Vehicle findByVin17(String vin17) {
        return vehicles.get(vin17);
    }
    
}
