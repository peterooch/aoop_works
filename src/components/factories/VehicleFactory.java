package components.factories;

import utilities.VehicleType;

public interface VehicleFactory {
    public VehicleType getVehicle(String type);
}