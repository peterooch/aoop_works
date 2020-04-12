package components;

import java.util.ArrayList;
import java.util.Random;

/**
 * Road class
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

    private static final int MIN_SPEED = 0;
    private static final int MAX_SPEED = 200;

    public Road(Junction from, Junction to,
                ArrayList<VehicleType> allowed, boolean open, boolean enabled) {
        InitRoad(from, to, allowed, open, enabled);
    }
    public Road(Junction from, Junction to) {
        Random randObj = new Random();
        InitRoad(from, to, null, randObj.nextBoolean(), randObj.nextBoolean());
    }

    private void InitRoad(Junction from, Junction to,
                          ArrayList<VehicleType> allowed, boolean open, boolean enabled) {
        fromJunc = from;
        toJunc = to;
        isOpen = open;
        isEnabled = enabled;
        
        length = countLength();

        if (allowed != null)
        {
            allowedVehicles = allowed;
        }
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
    public boolean setMaxSpeed(int speed) {
        if (speed < MIN_SPEED || speed > MAX_SPEED)
            return false;
        maxSpeed = speed;
        return true;
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
        return "From Junction: " + fromJunc.toString() + ", To Junction: " + toJunc.toString();
    }
    public boolean equals(Road other) {
        return fromJunc.equals(other.fromJunc) && toJunc.equals(other.toJunc);
    }
}
