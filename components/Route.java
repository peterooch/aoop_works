package components;

import java.util.ArrayList;

/**
 * Route class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Route {
    /**
     * list of junctions
     */
    private ArrayList<Junction> junctions;
    /**
     * list of roads
     */
    private ArrayList<Road> roads;
    /**
     * double time delay parameter
     */
    private double delay;
    /**
     * vehicle type parameter
     */
    private VehicleType vehicleType;

    /**
     * constructor that gets three parameters:
     * @param junctions
     * @param roads
     * @param vehicleType
     */
    public Route(ArrayList<Junction> junctions, ArrayList<Road> roads, VehicleType vehicleType) {
        this.junctions = junctions;
        this.roads = roads;
        this.vehicleType = vehicleType;
        calcDelay();
    }

    /**
     * constructor that gets three parameters:
     * @param start
     * @param end
     * @param vehicleType
     */
    public Route(Junction start, Junction end, VehicleType vehicleType) {
        /** To be implemented */
        this.vehicleType = vehicleType;
    }

    /**
     * getter for starting junction
     * @return junction
     */
    public Junction getStart() {
        return junctions.get(0);
    }

    /**
     * getter for the ending junction
     * @return junction
     */
    public Junction getEnd() {
        return junctions.get(junctions.size() - 1);
    }

    /**
     * function that calculates the delay
     * @return delay
     */
    public double calcDelay() {
        delay = 0.0;

        for (Junction junction : junctions) {
            if (junction.getHasLights()) {
                delay += junction.getDelay() * (junction.getEnteringRoads().size() - 1);
            } else {
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

    /**
     * getter for the junctions
     * @return
     */
    public ArrayList<Junction> getJunctions() {
        return junctions;
    }
}
