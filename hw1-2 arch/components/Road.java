package components;

import java.util.ArrayList;

/**
 * Road class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Road implements RouteParts {
    private static final int[] allowedSpeedOptions = {30,40,50,55,60,70,80,90};
    private Junction startJunction;
    private Junction endJunction;
    private boolean greenLight;
    private double length;
    private int maxSpeed;
    private VehicleType[] vehicleTypes;
    private ArrayList<Vehicle> waitingVehicles;

    /**
     * Road constructor
     */
    public Road(Junction start, Junction end) {
        startJunction = start;
        endJunction = end;
        waitingVehicles = new ArrayList<Vehicle>();
        greenLight = false;
        length = countLength();

        startJunction.addExitingRoad(this);
        endJunction.addEnteringRoad(this);

        /** Pick random speed limit */
        maxSpeed = allowedSpeedOptions[getRandomInt(0, allowedSpeedOptions.length - 1)];

        successMessage(toString());
    }
    /**
     * function that counts the length of the road
     * @return double value
     */
    public double countLength() {
        return startJunction.calcDistace(endJunction);
    }

    /**
     * setter for the entrance junction
     * @param junction
     */
    public void setStartJunction(Junction junction) {
        startJunction = junction;
        length = countLength();
    }

    /**
     * setter for The road exit junction
     * @param junction
     */
    public void setEndJunction(Junction junction) {
        endJunction = junction;
        length = countLength();
    }

    /**
     * getter for The road entrance junction
     * @return fromJunc
     */
    public Junction getStartJunction() {
        return startJunction;
    }

    /**
     * getter for the road exit junction
     * @return toJunc
     */
    public Junction getEndJunction() {
        return endJunction;
    }

    /**
     * setter for the kind of vehicles that are permitted
     * @param allowedVehicles
     */
    public void setVehicleTypes(VehicleType[] list) {
        vehicleTypes = list;
    }

    /**
     * getter for the allowed vehicles
     * @return
     */
    public VehicleType[] getVehicleTypes() {
        return vehicleTypes;
    }

    /**
     * setter for the maxSpeed parameter
     * @param speed
     */
    public void setMaxSpeed(int speed) {
        for (int s : allowedSpeedOptions) {
            if (s == speed)
                maxSpeed = speed;
        }
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
     * function that returns the value
     */
    @Override
    public String toString() {
        return String.format("Road %s -> %s, length: %d, max speed  %d", startJunction, endJunction, (int)length, maxSpeed);
    }

    /**
     * function that checks if two values are equal
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Road) {
            Road otherRoad = (Road)other;
            return startJunction.equals(otherRoad.startJunction) && endJunction.equals(otherRoad.endJunction);
        }
        return false;
    }

    @Override
    public double calcEstimatedTime(Object object) {
        if (!(object instanceof Vehicle))
            return 0;
        return (int)(length / Math.min(maxSpeed,((Vehicle)object).getVehicleType().getAverageSpeed()));
    }

    @Override
    public boolean canLeave(Vehicle vehicle) {
        return vehicle.getTimeOnCurrentPart() >= calcEstimatedTime(vehicle);
    }

    @Override
    public void checkIn(Vehicle vehicle) {
        waitingVehicles.add(vehicle);
        System.out.println(vehicle);
        System.out.println("- is starting to move on " + this +  ", time to finish: " + calcEstimatedTime(vehicle) + ".");
    }

    @Override
    public void checkOut(Vehicle vehicle) {
        removeVehicleFromWaitingVehicles(vehicle);
    }
    public void removeVehicleFromWaitingVehicles(Vehicle vehicle) {
        waitingVehicles.remove(vehicle);
    }
    @Override
    public RouteParts findNextPart(Vehicle vehicle) {
        return endJunction;
    }

    @Override
    public void stayOnCurrentPart(Vehicle vehicle) {
        System.out.println(vehicle);
        System.out.println("- is still moving on " + toString());
    }

    public void setGreenLight(boolean greenLight) {
        this.greenLight = greenLight;
    }

    public boolean getGreenLight() {
        return greenLight;
    }
	public ArrayList<Vehicle> getWaitingVehicles() {
		return waitingVehicles;
	}
}
