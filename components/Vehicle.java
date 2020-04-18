package components;

import java.util.ArrayList;
import java.util.Random;

/**
 * Vehicle class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */

public class Vehicle {
    private int id;
    private VehicleType type;
    private int speed;
    private Route currentRoute;
    private Junction lastJunction;
    private Road lastRoad;
    private boolean movesNow;
    private double spentTime;

    public Vehicle(int id, VehicleType type, Junction lastJunction) {
        this.id = id;
        this.type = type;
        this.lastJunction = lastJunction;
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
    public void setID(int id) {
        this.id = id;
    }
    public int getID() {
        return id;
    }
    public void setType(VehicleType type) {
        this.type = type;
    }
    public VehicleType getType() {
        return type;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getSpeed() {
        return speed;
    }
    public void setCurrentRoute(Route currentRoute) {
        this.currentRoute = currentRoute;
    }
    public Route getCurrentRoute() {
        return currentRoute;
    }
    public void setLastJunction(Junction lastJunction) {
        this.lastJunction = lastJunction;
    }
    public Junction getLastJunction() {
        return lastJunction;
    }
    public void setLastRoad(Road lastRoad) {
        this.lastRoad = lastRoad;
    }
    public Road getLastRoad() {
        return lastRoad;
    }
    public void setMovesNow(boolean movesNow) {
        this.movesNow = movesNow;
    }
    public boolean getMovesNow() {
        return movesNow;
    }
    public void setSpentTime(double spentTime) {
        this.spentTime = spentTime;
    }
    public double getSpentTime() {
        return spentTime;
    }
    public boolean equals(Vehicle other) {
        return id == other.id;
    }
    public String toString() {
        return String.format("ID: %d, %s", id, type.toString());
    }

}
