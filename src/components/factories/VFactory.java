package components.factories;

import utilities.VehicleType;

public class VFactory {
    public static VehicleFactory getFactory(int wheel_count) {
        switch (wheel_count) {
            case 2:
                return new TwoWheelFactory();
            case 4:
                return new FourWheelFactory();
            case 10:
                return new TenWheelFactory();
            default:
                return null;
        }
    }
    /** Make this a static class */
    private VFactory() {}
}
class TwoWheelFactory implements VehicleFactory {
    @Override
    public VehicleType getVehicle(String type) {
        if ("fast".equals(type))
            return VehicleType.motorcycle;
        if ("slow".equals(type))
            return VehicleType.bicycle;
        return null;
    }
}
class FourWheelFactory implements VehicleFactory {
    @Override
    public VehicleType getVehicle(String type) {
        if ("private".equals(type))
            return VehicleType.car;
        if ("work".equals(type))
            return VehicleType.truck;
        if ("public".equals(type))
            return VehicleType.bus;
        return null;
    }
}
class TenWheelFactory implements VehicleFactory {
    @Override
    public VehicleType getVehicle(String type) {
        if ("public".equals(type))
            return VehicleType.tram;
        if ("work".equals(type))
            return VehicleType.semitrailer;
        return null;
    }
}