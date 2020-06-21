package components.builders;

import java.util.Random;

import components.*;
import components.factories.*;
import utilities.Utilities;
import utilities.VehicleType;

/** CityBuilder class
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */
public class CityBuilder implements Builder, Utilities {
    private final static VehicleType[] prototypes = {
        VFactory.getFactory(2).getVehicle("fast"),
        VFactory.getFactory(2).getVehicle("slow"),
        VFactory.getFactory(4).getVehicle("private"),
        VFactory.getFactory(4).getVehicle("public"),
        VFactory.getFactory(10).getVehicle("public"),
    };
    @Override
    public Map buildMap(Driving driving) {
        Map map = new Map(12);
        // Create 15 vehicles for this simulation
        for (int i = 0; i < 15; i++) {
            Road road = map.getRoads().get(getRandomInt(0, map.getRoads().size()));
            Vehicle vehicle = new Vehicle(road, prototypes[getRandomInt(0, prototypes.length)]);
            
            if (vehicle.getSpeedRatio() >= 1)
                vehicle.setSpeedRatio(1 + new Random().nextDouble() * 0.3);

            driving.getVehicles().add(vehicle);
            driving.getAllTimedElements().add(vehicle);
        }
        map.turnLightsOn();
        return map;
    }
}