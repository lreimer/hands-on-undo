package hands.on.undo.dto;

import java.util.Objects;

public class VehicleRecordResponse {

    private final String name;
    private final String address;
    private final String dateOfBirth;
    private final String driversLicenseNumber;
    private final String vehicleYear;
    private final String vehicleMake;
    private final String vehicleIdentificationNumber;

    private VehicleRecordResponse(final Builder builder) {
        this.name = Objects.requireNonNull(builder.name);
        this.address = Objects.requireNonNull(builder.address);
        this.dateOfBirth = Objects.requireNonNull(builder.dateOfBirth);
        this.driversLicenseNumber = Objects.requireNonNull(builder.driversLicenseNumber);
        this.vehicleYear = Objects.requireNonNull(builder.vehicleYear);
        this.vehicleMake = Objects.requireNonNull(builder.vehicleMake);
        this.vehicleIdentificationNumber = Objects.requireNonNull(builder.vehicleIdentificationNumber);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public String getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String address;
        private String dateOfBirth;
        private String driversLicenseNumber;
        private String vehicleYear;
        private String vehicleMake;
        private String vehicleIdentificationNumber;

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withAddress(final String address) {
            this.address = address;
            return this;
        }

        public Builder withDateOfBirth(final String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder withDriversLicenseNumber(final String driversLicenseNumber) {
            this.driversLicenseNumber = driversLicenseNumber;
            return this;
        }

        public Builder withVehicleYear(final String vehicleYear) {
            this.vehicleYear = vehicleYear;
            return this;
        }

        public Builder withVehicleMake(final String vehicleMake) {
            this.vehicleMake = vehicleMake;
            return this;
        }

        public Builder withVehicleIdentificationNumber(final String vehicleIdentificationNumber) {
            this.vehicleIdentificationNumber = vehicleIdentificationNumber;
            return this;
        }

        public VehicleRecordResponse build() {
            return new VehicleRecordResponse(this);
        }
    }
}
