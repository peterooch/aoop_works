package components;

import java.util.ArrayList;
import utilities.Point;

/**
 * Junction class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 * 
 */

public class Junction extends Point implements RouteParts {
    
    private static int objectsCount = 1;
    /**
     * String value for junction name
     */
    private String junctionName;
    /**
     * Array for entering roads
     */
    private ArrayList<Road> enteringRoads;
    /**
     * Array for exiting Roads
     */
    private ArrayList<Road> exitingRoads;

    /**
     * constructor
     *
     * @param name Junction name
     * @param loc Junction location
     */
    public Junction(String name, double x, double y) {
        super(x, y);
        Init(name);
    }
    public Junction() {
        super();
        Init("Junction " + objectsCount);
    }

    /** Internal init function
     * @param name junction name
     */
    private void Init(String name) {
        junctionName = name;
        enteringRoads = new ArrayList<>();
        exitingRoads = new ArrayList<>();
        objectsCount++;
        successMessage(toString());
    }
    /**
     * junction name setter
     * @param name
     */
    public void setJunctionName(String name) {
        junctionName = name;
    }

    /**
     * junction name getter
     * @return the junction name
     */
    public String getJunctionName() {
        return junctionName;
    }

    /**
     * entering roads setter
     * @param list new list of entring roads
     */
    public void setEnteringRoads(ArrayList<Road> list) {
        this.enteringRoads = list;
    }

    /**
     * entering roads getter
     * @return list of entring roads
     */
    public ArrayList<Road> getEnteringRoads() {
        return enteringRoads;
    }

    /**
     * setter of exiting roads
     * @param list new list of exiting roads
     */
    public void setExitingRoads(ArrayList<Road> list) {
        this.exitingRoads = list;
    }

    /**
     * getter of exiting roads
     * @return list of exiting roads
     */
    public ArrayList<Road> getExitingRoads() {
        return exitingRoads;
    }

    /**
     * function that returns the junction name but as a String value
     */
    @Override
    public String toString() {
        return junctionName;
    }

    /**
     * function that says if two values are equals
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Junction) {
            Junction other_junc = (Junction)other;
            return junctionName.equals(other_junc.junctionName) && super.equals(other_junc);
        }
        return false;
    }
    
    /**
     * add a road to the internal entering road list
     * @param road road the be added
     */
    public void addEnteringRoad(Road road) {
        enteringRoads.add(road);
    }

    /**
     * add a road to the internal exiting road list
     * @param road road the be added
     */
    public void addExitingRoad(Road road) {
        exitingRoads.add(road);
    }

    /**
     * Calculate how much time the vehicle will sit in the junction
     * @param object (Supposedly vehicle) vehicle object to be evalued
     * @return how much time
     */
    @Override
    public double calcEstimatedTime(Object object) {
        if (!(object instanceof Vehicle))
            return 0;
        
        Vehicle vehicle = (Vehicle)object;

        return enteringRoads.indexOf(vehicle.getLastRoad()) + 1;
    }
    /**
     * Return the amount of junction objects that were created
     * @return
     */
    public static int getJunctionCount() {
        return objectsCount;
    }
    /**
     * function thats checks the availability of the junction
     * @param vehicle vehicle to be checked
     * @return boolean value that stands for the availability of the junction
     */
    public boolean checkAvailability(Vehicle vehicle) {
        for (Road road : enteringRoads) {            
            if (road.equals(vehicle.getLastRoad()) && !road.getWaitingVehicles().isEmpty()) {
                return road.getWaitingVehicles().get(0).equals(vehicle);
            }
        }
        return false;
    }

    /**
     * Check if the vehicle can leave the junction
     * @param vehicle vehicle to be checked
     * @return true if the vehicle can leave, false otherwise
     */
    @Override
    public boolean canLeave(Vehicle vehicle) {
        return checkAvailability(vehicle);
    }

    /**
     * "Checkin" a vehicle into the junction
     * @param vehicle vehicle to be "checkedin"
     */
    @Override
    public void checkIn(Vehicle vehicle) {
        System.out.println(vehicle);
        System.out.println("- is entering " + this + ", estimated wait time: " + calcEstimatedTime(vehicle));
        vehicle.getLastRoad().getWaitingVehicles().add(vehicle);
    }

    /**
     * "Checkout" a vehicle out of the junction
     * @param vehicle vehicle to be "checkedout"
     */
    @Override
    public void checkOut(Vehicle vehicle) {
        System.out.println(vehicle);
        System.out.println("- is leaving " + this + ".");
        vehicle.getLastRoad().getWaitingVehicles().remove(vehicle);
    }

    /**
     * 
     */
    @Override
    public RouteParts findNextPart(Vehicle vehicle) {
        for (Road road : exitingRoads) {
            for (VehicleType type : road.getVehicleTypes()) {
                if (vehicle.getVehicleType() == type)
                    return road;
            }
        }
        return null;
    }
    /**
     * Print that the vehicle will stay in its current location
     * @param vehicle the vehicle
     */
    @Override
    public void stayOnCurrentPart(Vehicle vehicle) {
        System.out.println(vehicle);
        if (this instanceof LightedJunction) {
            System.out.println("- is waiting at " + this + " for green light");
        }
        else {
            System.out.println("- is waiting at " + this + " - there are cars with higher priorities.");
        }
    }
}
