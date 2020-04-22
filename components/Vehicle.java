package components;

import java.util.ArrayList;
import java.util.Random;

/**
 * Vehicle class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Vehicle {
    /**
     * int value for id parameter
     */
    private int id;
    /**
     * parameter for vehicle type
     */
    private VehicleType type;
    /**
     * int value for speed
     */
    private int speed;
    /**
     * Route parameter for current route
     */
    private Route currentRoute;
    /**
     * Junction parameter for last Junction
     */
    private Junction lastJunction;
    /**
     * Road parameter for last Road
     */
    private Road lastRoad;
    /**
     * boolean value that says if a vehicle is moving
     */
    private boolean movesNow;
    /**
     * double value for time spent
     */
    private double spentTime;

    /** 
     * constructor that gets three parameters:
     * @param id
     * @param type
     * @param lastJunction
     */
    public Vehicle(int id, VehicleType type, Junction lastJunction) {
        this.id = id;
        this.type = type;
        this.lastJunction = lastJunction;
        movesNow = false;
        spentTime = 0;
        System.out.printf("%s, ID: %d has been created and placed at %s\n", type, id, lastJunction);

        Random randObj = new Random();

        currentRoute = new Route(new ArrayList<Junction>(), new ArrayList<Road>(), type);

        int road_count = randObj.nextInt(6) + 5;
        int roads_added = 0;
        currentRoute.getJunctions().add(lastJunction);
    }

    public void move() {

    }

    public void status() {

    }

    public void checkIn() {

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
    public void setType(VehicleType type) {
        this.type = type;
    }

    /**
     * type getter
     * @return type
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * speed setter
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * speed getter
     * @return speed
     */
    public int getSpeed() {
        return speed;
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
     * last junction setter
     * @param lastJunction
     */
    public void setLastJunction(Junction lastJunction) {
        this.lastJunction = lastJunction;
    }

    /**
     * last junction getter
     * @return
     */
    public Junction getLastJunction() {
        return lastJunction;
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

    /**
     * movesNow setter
     * @param movesNow
     */
    public void setMovesNow(boolean movesNow) {
        this.movesNow = movesNow;
    }

    /**
     * movesNow getter
     * @return movesNow
     */
    public boolean getMovesNow() {
        return movesNow;
    }

    /**
     * spentTime setter
     * @param spentTime
     */
    public void setSpentTime(double spentTime) {
        this.spentTime = spentTime;
    }

    /**
     * spentTime getter
     * @return spentTime
     */
    public double getSpentTime() {
        return spentTime;
    }

    public boolean equals(Object other) {
        return (other instanceof Vehicle) && (id == ((Vehicle)other).id);
    }

    public String toString() {
        return String.format("ID: %d, %s", id, type.toString());
    }

}
