package components;

import java.util.ArrayList;

/**
 * Route class
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */

public class Route {
    private ArrayList<Junction> junctions;
    private ArrayList<Road> roads;
    private double delay;
    private VehicleType vehicleType;

    public Route(ArrayList<Junction> junctions, ArrayList<Road> roads, VehicleType vehicleType) {
        this.junctions = junctions;
        this.roads = roads;
        this.vehicleType = vehicleType;
        calcDelay();
    }
    public Route(Junction start, Junction end, VehicleType vehicleType) {
        /** To be implemented */
        this.vehicleType = vehicleType;
    }
    public Junction getStart() {
        return junctions.get(0);
    }
    public Junction getEnd() {
        return junctions.get(junctions.size() - 1);
    }
    public double calcDelay() {
        delay = 0.0;
   
        for (Junction junction : junctions) {
            if (junction.getHasLights()) {
                delay += junction.getDelay() * (junction.getEnteringRoads().size() - 1);
            }
            else {
                for (Road road : roads) {
                    if (road.getToJunc().equals(junction)) {
                        delay += junction.getEnteringRoads().indexOf(road);
                        break;
                    }
                }
            }  
        }

        for (Road road : roads)
            delay += road.getLength() / Math.min(road.getMaxSpeed(), vehicleType.getSpeed());

        return delay;
    }
}
