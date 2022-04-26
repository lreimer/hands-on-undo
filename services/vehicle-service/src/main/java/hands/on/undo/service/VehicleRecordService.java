package hands.on.undo.service;

import hands.on.undo.dto.VehicleRecordResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VehicleRecordService implements VehicleRecord {

    @Override
    public VehicleRecordResponse find(String id) {
        if (id == null) {
            throw new RuntimeException("Id provided cannot be null.");
        }
        return VehicleRecordResponse.builder()
                .withName("Test Name")
                .withAddress("Test Address")
                .withDateOfBirth("11/05/1993")
                .withDriversLicenseNumber("TEST827329FOO28")
                .withVehicleYear("2017")
                .withVehicleMake("WMB")
                .withVehicleIdentificationNumber("1HGSQDUO4867294")
                .build();
    }
}
