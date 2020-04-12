package components;

import java.util.ArrayList;

public class Route {
    private ArrayList<Junction> junctions;
    private ArrayList<Road> roads;
    private double delay;
    private String vehicleType;

    public Route(ArrayList<Junction> junctions, ArrayList<Road> roads, String vehicleType) {
        this.junctions = junctions;
        this.roads = roads;
        this.vehicleType = vehicleType;
        delay = calcDelay();
    }
    public Route(Junction start, Junction end, String vehicleType) {
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
        return 0.0;
    }
}
