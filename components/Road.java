package components;

import java.util.ArrayList;
import java.util.Random;

/**
 * Road class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
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

    public double countLength() {
        double dx = fromJunc.getLocation().getX() - toJunc.getLocation().getX();
        double dy = fromJunc.getLocation().getY() - toJunc.getLocation().getY();

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public void setFromJunc(Junction junction) {
        fromJunc = junction;
        length = countLength();
    }

    public void setToJunc(Junction junction) {
        toJunc = junction;
        length = countLength();
    }

    public Junction getFromJunc() {
        return fromJunc;
    }

    public Junction getToJunc() {
        return toJunc;
    }

    public void setAllowedVehicles(ArrayList<VehicleType> list) {
        allowedVehicles = list;
    }

    public ArrayList<VehicleType> getAllowedVehicle() {
        return allowedVehicles;
    }

    public void setIsOpen(boolean open) {
        isOpen = open;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setMaxSpeed(int speed) {
        maxSpeed = speed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getLength() {
        return length;
    }

    public boolean addVehicleType(VehicleType type) {
        if (type == null || allowedVehicles.contains(type))
            return false;

        allowedVehicles.add(type);
        return true;
    }

    public String toString() {
        return String.format("Road %s -> %s", fromJunc.getJunctionName(), toJunc.getJunctionName());
    }

    public boolean equals(Object other) {
        if (other instanceof Road) {
            Road otherRoad = (Road)other;
            return fromJunc.equals(otherRoad.fromJunc) && toJunc.equals(otherRoad.toJunc);
        }
        return false;
    }
}
