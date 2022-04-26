package hands.on.undo.service;

import hands.on.undo.dto.VehicleRecordResponse;

@FunctionalInterface
public interface VehicleRecord {

    VehicleRecordResponse find(String id);
}
