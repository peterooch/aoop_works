package components;

import java.util.ArrayList;
import java.util.Random;

import components.Map;
import components.Vehicle;
import components.VehicleType;
import components.Timer;

/**
 * Driving class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Driving {

    /**
     * map value for current map
     */
    private Map Map;
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
     * @param numOfJuncs
     * @param numOfVehicles
     */
    public Driving(int numOfJuncs, int numOfVehicles) {
        Map = new Map(numOfJuncs);
        vehicles = new ArrayList<Vehicle>(numOfVehicles);
        addVehicles(numOfVehicles);
    }

    public void incrementDrivingTime(){                         ///how to promote the timed elements??????????????????
        for(int i = 0 ; i < allTimedElements.size() ; i++){
        }
    }


    /**
     * Starts the driving simulation
     * @param allTimedElements the amount of turns made with all current vehicles
     */
    public void Drive(int numOfTurns) {
    	for (int turn = 1 ; turn <= numOfTurns ; turn++){
            incrementDrivingTime();
        }
    }
  

    @Override
    public boolean equals(Object other) {
        return (other instanceof Driving) && Map == ((Driving)other).Map;
    }

    /**
     * getter for Map
     * @return Map
     */
    public Map getMap() {
        return Map;
    }

    /**
     * setter for Map
     * @param Map
     */
    public void setMap(Map Map) {
        this.Map = Map;
    }

    /**
     * getter for vehicles
     * @return list of current vehicles
     */
    public ArrayList<Vehicle> getvehicles() {
        return vehicles;
    }

    /**
     * setter of vehicles
     * @param vehicles
     */
    public void setvehicles(ArrayList<Vehicle> vehicles) {
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
     * setter for drivingTime
     * @param drivingTime
     */
    public void setDrivingTime(int drivingTime) {
        this.drivingTime = drivingTime;
    }

    /**
     * getter for maximum time
     * @return allTimedElements
     */
    public ArrayList<Timer> getallTimedElements() {
        return allTimedElements;
    }

    /**
     * setter for maximum time
     * @param allTimedElements
     */
    public void setallTimedElements(ArrayList<Timer>) {
        this.allTimedElements = allTimedElements;
    }

    /**
     * getter for vehicles
     * @return vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
}
