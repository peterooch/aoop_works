package components;

import java.util.ArrayList;
import utilities.Timer;
import utilities.Utilities;
/**
 * Driving class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Driving implements Utilities, Timer {

    /**
     * map value for current map
     */
    private Map map;
    /**
     * list of vehicles for  vehicles
     */
    private ArrayList<Vehicle> vehicles;
    /**
     * int value for driving time
     */
    private int drivingTime;
    /**
     * int value for maximum time
     */
    private ArrayList<Timer> allTimedElements;

    /**
     * constructor that gets two parameters:
     * @param numOfJuncs number of junctions to be made
     * @param numOfVehicles number of vehicles to be made
     */
    public Driving(int numOfJuncs, int numOfVehicles) {
        map = new Map(numOfJuncs);
        fancyprint("Game map has been created", 80);
        vehicles = new ArrayList<Vehicle>(numOfVehicles);
        allTimedElements = new ArrayList<Timer>(numOfVehicles + numOfJuncs);
        drivingTime = 0;
        
        fancyprint("Creating vehicles", 80);
        for (int i = 0; i < numOfVehicles; i++) {
            Junction randJunc = map.getJunctions().get(getRandomInt(0, map.getJunctions().size() - 1));
            Road randRoad;
    
            if (!randJunc.getExitingRoads().isEmpty()) {
                randRoad = randJunc.getExitingRoads().get(getRandomInt(0, randJunc.getExitingRoads().size() - 1));
            }
            else if (!randJunc.getEnteringRoads().isEmpty()) {
                randRoad = randJunc.getEnteringRoads().get(getRandomInt(0, randJunc.getEnteringRoads().size() - 1));
            }
            else {
                i--;
                continue;
            }

            Vehicle vehicle = new Vehicle(randRoad);
            vehicles.add(vehicle);
            allTimedElements.add(vehicle);
        }

        for (TrafficLights light : map.getTrafficLights())
            allTimedElements.add(light);

    }

    /** Increments the timer of all the internal timer objects, Timer interface method */
    @Override
    public void incrementDrivingTime() { 
        for (Timer timedElement : allTimedElements) {
            timedElement.incrementDrivingTime();
            drivingTime++;
        }
    }

    /**
     * Starts the driving simulation
     * @param numOfTurns the amount of turns made with all timed objects
     */
    public void drive(int numOfTurns) {
    	for (int turn = 1 ; turn <= numOfTurns ; turn++){
            fancyprint("TURN " + turn, 80);
            incrementDrivingTime();
        }
    }
  
    /**
     * Equals method
     * @param other object to compare with
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Driving) && map == ((Driving)other).map;
    }

    /**
     * getter for Map
     * @return Map
     */
    public Map getMap() {
        return map;
    }

    /**
     * setter for Map
     * @param Map
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * getter for vehicles
     * @return list of current vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * setter of vehicles
     * @param vehicles
     */
    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * getter for drivingTime
     * @return drivingTime
     */
    public double getDrivingTime() {
        return drivingTime;
    }

    /**
     * getter for maximum time
     * @return allTimedElements
     */
    public ArrayList<Timer> getAllTimedElements() {
        return allTimedElements;
    }

    /**
     * setter for maximum time
     * @param allTimedElements
     */
    public void setAllTimedElements(ArrayList<Timer> allTimedElements) {
        this.allTimedElements = allTimedElements;
    }
}
