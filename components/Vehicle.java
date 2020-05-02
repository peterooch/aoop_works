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
     * constructor
     * @param id Vehicle ID
     * @param type Vehicle Type object
     * @param lastJunction Vehicle starting position
     */
    public Vehicle(int id, VehicleType type, Junction lastJunction) {
        this.id = id;
        this.type = type;
        this.lastJunction = lastJunction;
        movesNow = false;
        spentTime = 0;
        System.out.printf("%s, ID: %d has been created and placed at %s\n", type, id, lastJunction);

        Random randObj = new Random();
        ArrayList<Junction> junctions = new ArrayList<Junction>();
        ArrayList<Road> roads = new ArrayList<Road>();

        Junction currentJunc = lastJunction;
        
        /** Plot a random route starting from lastJunction */
        for (int roads_added = 0, road_count = (randObj.nextInt(10) + 10); roads_added < road_count; roads_added++) {
            if (currentJunc.getExitingRoads().isEmpty())
                break;
            
            Road road = currentJunc.getExitingRoads().get(randObj.nextInt(currentJunc.getExitingRoads().size()));
            
            if (junctions.size() == 1) {
                lastJunction.getVehicles().add(road);
                lastRoad = road;
            }
            roads.add(road);
            currentJunc = road.getToJunc();
            junctions.add(currentJunc);
        }
        /** Create the currentRoute object and feed it the plotted route */
        currentRoute = new Route(junctions, roads, type);
    }

    /**
     * Attempts to advance the vehicle in its route
     */
    public void move() {
        if (spentTime == 0)
            System.out.printf("%s is starting route from %s to %s.\n", this, currentRoute.getStart(), currentRoute.getEnd());

        if (currentRoute.getRoads().indexOf(lastRoad) + 1 >= currentRoute.getRoads().size()) {
            System.out.printf("%s has reached the end of its route\n", this);
            return;
        }
        if (lastJunction.checkAvailability(lastRoad)) {
            checkIn();
            System.out.printf("%s has arrived to %s\n", this, lastJunction);
        }
        else {
            System.out.printf("%s is waiting for green light at %s\n", this, lastJunction);
            spentTime += lastJunction.getDelay();
        }
    }
    /** Prints the vehicles current route status */
    public void status() {
        if (currentRoute.getRoads().indexOf(lastRoad) + 1 >= currentRoute.getRoads().size()) {
            System.out.printf("%s has completed its route, ", this);
        }
        else {
            System.out.printf("%s is currently placed at %s while on route from %s to %s, ",
                              this, lastJunction, currentRoute.getStart(), currentRoute.getEnd());
        }
        System.out.printf("Time spent: %f\n", spentTime);
    }
    /** Moves the vehicle to next road in its route and ajust the junction data */
    public void checkIn() {
        double traveltime = lastRoad.getLength() / Math.min(lastRoad.getMaxSpeed(), type.getAverageSpeed());
        spentTime += traveltime;
        System.out.printf("%s is moving on %s. Delay time: %f\n", this, lastRoad, traveltime);
        lastJunction.getVehicles().remove(lastRoad); // What if there other vehicles other than this one?
        lastRoad = currentRoute.getRoads().get(currentRoute.getRoads().indexOf(lastRoad) + 1);
        lastJunction = lastRoad.getToJunc();
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

    @Override
    public boolean equals(Object other) {
        return (other instanceof Vehicle) && (id == ((Vehicle)other).id);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, %s", id, type.toString());
    }

}
