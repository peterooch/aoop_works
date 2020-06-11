package components.builders;

import java.util.ArrayList;

import components.*;
import components.factories.*;
import utilities.Utilities;
import utilities.VehicleType;

public class CountryBuilder implements Builder, Utilities {
    private final static VehicleType[] prototypes = {
        VFactory.getFactory(2).getVehicle("fast"),
        VFactory.getFactory(4).getVehicle("work"),
        VFactory.getFactory(4).getVehicle("private"),
        VFactory.getFactory(4).getVehicle("public"),
        VFactory.getFactory(10).getVehicle("work"),
    };

    @Override
    public Map buildMap(Driving driving) {
        Map map = new Map();

        // Allocate junctions
        for (int i = 0; i < 6; i++) {
            Junction junction = JFactory.getJunction("country");
            map.getJunctions().add(junction);

            if (junction instanceof LightedJunction)
                map.getLights().add(((LightedJunction)junction).getLights());
        }

        ArrayList<Road> roads = new ArrayList<Road>();
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> positions = getRandomIntArray(0, 5, getRandomInt(2, 5));

            for (Integer j : positions) {
                if (j.intValue() == i)
                    continue;
                
                roads.add(new Road(map.getJunctions().get(i), map.getJunctions().get(j.intValue())));
            }
        }
        map.setRoads(roads);

        // Create 10 vehicles for this simulation
        for (int i = 0; i < 10; i++) {
            Road road = map.getRoads().get(getRandomInt(0, map.getRoads().size()));
            Vehicle vehicle = new Vehicle(road, prototypes[getRandomInt(0, prototypes.length)]);
            driving.getVehicles().add(vehicle);
            driving.getAllTimedElements().add(vehicle);
        }
        map.turnLightsOn();
        return map;
    }
}