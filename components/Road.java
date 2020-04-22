package components;

import java.util.ArrayList;
import java.util.Random;

/**
 * Road class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Road {
    private Junction fromJunc;
    private Junction toJunc;
    private ArrayList<VehicleType> allowedVehicles;
    private boolean isOpen;
    private boolean isEnabled;
    private double length;
    private int maxSpeed;

    /**
     * 
     * @param from    The road entrance junction
     * @param to      The road exit junction
     * @param allowed what kind of vehicles are permitted
     * @param open    is the road open to traffic
     * @param enabled is the road enabled (?)
     */
    public Road(Junction from, Junction to, ArrayList<VehicleType> allowed, boolean open, boolean enabled) {
        InitRoad(from, to, allowed, open, enabled);
    }

    /**
     * See the {@link #Road(Junction, Junction, ArrayList, boolean, boolean)}
     * Constructor for more info
     */
    public Road(Junction from, Junction to) {
        Random randObj = new Random();
        InitRoad(from, to, VehicleType.getRandomVehicleTypes(), randObj.nextBoolean(), randObj.nextBoolean());
    }

    /**
     * Internal Init function to be called from the public constructors, See the
     * {@link #Road(Junction, Junction, ArrayList, boolean, boolean)} Constructor
     * for more info
     */
    private void InitRoad(Junction from, Junction to, ArrayList<VehicleType> allowed, boolean open, boolean enabled) {
        fromJunc = from;
        toJunc = to;
        isOpen = open;
        isEnabled = enabled;
        allowedVehicles = allowed;

        length = countLength();

        if (!fromJunc.getExitingRoads().contains(this))
            fromJunc.getExitingRoads().add(this);

        if (!toJunc.getEnteringRoads().contains(this))
            toJunc.getEnteringRoads().add(this);

        /** Pick random speed limit */
        maxSpeed = new Random().nextInt(12) * 5 + 55;

        System.out.printf("%s object has been created\n", toString());
    }

    /**
     * function that counts the length of the road
     * @return double value
     */
    public double countLength() {
        double dx = fromJunc.getLocation().getX() - toJunc.getLocation().getX();
        double dy = fromJunc.getLocation().getY() - toJunc.getLocation().getY();

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * setter for the entrance junction
     * @param junction
     */
    public void setFromJunc(Junction junction) {
        fromJunc = junction;
        length = countLength();
    }

    /**
     * setter for The road exit junction
     * @param junction
     */
    public void setToJunc(Junction junction) {
        toJunc = junction;
        length = countLength();
    }

    /**
     * getter for The road entrance junction
     * @return fromJunc
     */
    public Junction getFromJunc() {
        return fromJunc;
    }

    /**
     * getter for the road exit junction
     * @return toJunc
     */
    public Junction getToJunc() {
        return toJunc;
    }

    /**
     * setter for the kind of vehicles that are permitted
     * @param allowedVehicles
     */
    public void setAllowedVehicles(ArrayList<VehicleType> list) {
        allowedVehicles = list;
    }

    /**
     * getter for the allowed vehicles
     * @return
     */
    public ArrayList<VehicleType> getAllowedVehicle() {
        return allowedVehicles;
    }

    /**
     * setter for the isOpen parameter
     * @param open
     */
    public void setIsOpen(boolean open) {
        isOpen = open;
    }

    /**
     * getter for the isOpen parameter
     * @return isOpen
     */
    public boolean getIsOpen() {
        return isOpen;
    }

    /**
     * setter for the isEnabled parameter
     * @param enabled
     */
    public void setIsEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    /**
     * getter for the isEnabled parameter
     * @return
     */
    public boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * setter for the maxSpeed parameter
     * @param speed
     */
    public void setMaxSpeed(int speed) {
        maxSpeed = speed;
    }

    /**
     * getter for the maxSpeed parameter
     * @return
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * getter for the length parameter
     * @return
     */
    public double getLength() {
        return length;
    }

    /**
     * function that adds vehicle type
     * @param type
     * @return a boolean value that says if the type was added
     */
    public boolean addVehicleType(VehicleType type) {
        if (type == null || allowedVehicles.contains(type))
            return false;

        allowedVehicles.add(type);
        return true;
    }

    /**
     * function that returns the value
     */
    public String toString() {
        return String.format("Road %s -> %s", fromJunc.getJunctionName(), toJunc.getJunctionName());
    }

    /**
     * function that checks if two values are equal
     */
    public boolean equals(Object other) {
        if (other instanceof Road) {
            Road otherRoad = (Road)other;
            return fromJunc.equals(otherRoad.fromJunc) && toJunc.equals(otherRoad.toJunc);
        }
        return false;
    }
}
