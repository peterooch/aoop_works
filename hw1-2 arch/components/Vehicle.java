package components;

import utilities.Utilities;
import utilities.Timer;
/**
 * Vehicle class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Vehicle implements Utilities, Timer {
    /**
     * parameter that counts the objects
     */
    private static int objectsCount;
    /**
     * int value for id parameter
     */
    private int id;
    /**
     * parameter for vehicle type
     */
    private VehicleType vehicleType;
    /**
     * Route parameter for current route
     */
    private Route currentRoute;
    /**
     * Road parameter for last Road
     */
    private Road lastRoad;
    /**
     * road/junction parameter for current RoutPart
     */
    private RouteParts currentRoutePart;
    /**
     * parameter for time passed since beggining
     */
    private int timeFromRouteStart;
    /**
     * parameter for time passed since last checkin
     */
    private int timeOnCurrentPart;
    /**
     * String parameter for status(delay in junc etc..)
     */
    private String status; 


    /** 
     * constructor
     * @param road starting road
     */
    public Vehicle(Road road) {
        status = null;
        id = objectsCount++;
        timeFromRouteStart = 0;
        timeOnCurrentPart = 0;
        vehicleType = VehicleType.getRandomVehicleType();
        lastRoad = road;
        currentRoutePart = road;
        currentRoute = new Route(road, this);
        successMessage(toString());
        System.out.println("- is starting a new " + currentRoute + ", estimated time for route: " + currentRoute.calcEstimatedTime(this) + ".");
        road.checkIn(this);
    }

    /**
     * Attempts to advance the vehicle to the next route
     */
    public void move() {
        if(this.currentRoutePart.canLeave(this)){
            this.currentRoutePart.checkOut(this);
            this.currentRoutePart = currentRoute.findNextPart(this);
            this.timeOnCurrentPart = 0;
            this.currentRoutePart.checkIn(this);
        }
        else {
            this.currentRoutePart.stayOnCurrentPart(this);
        }
        
    }

    /**
     * id setter
     * @param id
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * id getter
     * @return id
     */
    public int getID() {
        return id;
    }

    /**
     * type setter
     * @param type
     */
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * type getter
     * @return type
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * current route setter
     * @param currentRoute
     */
    public void setCurrentRoute(Route currentRoute) {
        this.currentRoute = currentRoute;
    }

    /**
     * current route getter
     * @return
     */
    public Route getCurrentRoute() {
        return currentRoute;
    }

    /**
     * last road setter
     * @param lastRoad
     */
    public void setLastRoad(Road lastRoad) {
        this.lastRoad = lastRoad;
    }

    /**
     * last road getter
     * @return
     */
    public Road getLastRoad() {
        return lastRoad;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Vehicle) && (id == ((Vehicle)other).id);
    }

    @Override
    public String toString() {
        return String.format("Vehicle %d, %s", id, vehicleType.toString());
    }

    public int getTimeFromRouteStart() {
        return timeFromRouteStart;
    }

    public void setTimeFromRouteStart(int timeFromRouteStart) {
        this.timeFromRouteStart = timeFromRouteStart;
    }

    public int getTimeOnCurrentPart() {
        return timeOnCurrentPart;
    }

    public void setTimeOnCurrentPart(int timeOnCurrentPart) {
        this.timeOnCurrentPart = timeOnCurrentPart;
    }

    public int getObjectsCount() {
        return objectsCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void incrementDrivingTime() {
        this.setTimeOnCurrentPart(this.getTimeOnCurrentPart() + 1);
        this.setTimeFromRouteStart(this.getTimeFromRouteStart() + 1);
        this.move();
    }

	public RouteParts getCurrentRoutePart() {
		return currentRoutePart;
	}

    public void setCurrentRoutePart(RouteParts currentRoutePart) {
        this.currentRoutePart = currentRoutePart;
    }

}
