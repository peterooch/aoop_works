package game;

import java.util.ArrayList;
import java.util.Random;

import components.Map;
import components.Vehicle;
import components.VehicleType;

/**
 * Driving class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Driving {
    /**
     * int value for num of junctions
     */
    private int numOfJuncs;
    /**
     * int value for num of vehicles
     */
    private int numOfVehicles;
    /**
     * map value for current map
     */
    private Map currentMap;
    /**
     * list of vehicles for current vehicles
     */
    private ArrayList<Vehicle> currentVehicles;
    /**
     * double value for driving time
     */
    private double drivingTime;
    /**
     * int value for maximum time
     */
    private int maxTime;

    /**
     * constructor that gets three parameters:
     * @param numOfJuncs
     * @param numOfVehicles
     * @param maxTime
     */
    public Driving(int numOfJuncs, int numOfVehicles, int maxTime) {
        this.numOfJuncs = numOfJuncs;
        this.numOfVehicles = numOfVehicles;
        this.maxTime = maxTime;

        currentMap = new Map(numOfJuncs);
        currentVehicles = new ArrayList<Vehicle>(numOfVehicles);
        addVehicles(numOfVehicles);
    }

    /**
     * function that adds a map
     */
    public void addMap() {
        int count = new Random().nextInt(16) + 10;

        currentMap = new Map(count, count);
    }

    /**
     * function that adds vehicles
     * @param count
     */
    private void addVehicles(int count) {
        currentVehicles = new ArrayList<Vehicle>(count);

        for (int i = 0; i < count; i++) {
            currentVehicles.add(new Vehicle(i, VehicleType.getRandomVehicleType(),
                    currentMap.getJunctions().get(new Random().nextInt(currentMap.getJunctions().size()))));
        }
    }

    /**
     * default function that adds vehicles without parameters
     */
    public void addVehicles() {
        addVehicles(new Random().nextInt(7) + 2);
    }

    /**
     * function that starts the driving (simulates) with maximum time as parameter
     * @param maxTime
     */
    public void startDrive(int maxTime) {
    	for (int i = 0 ; i < maxTime ; i++){
            System.out.println("\nTurn: " + i);
            for (int j = 0; j < currentVehicles.size() ; j++){
                currentVehicles.get(j).move();
            }
        }
        System.out.println("\nStatus");
        for (int i = 0 ; i <currentVehicles.size() ; i++){
            currentVehicles.get(i).status();
        }

    }

    public String toString() {
        return "Number of junctions: " + numOfJuncs + ", Number of vehicles: " + numOfVehicles;
    }

    public boolean equals(Driving other) {
        return currentMap == other.currentMap;
    }

    /**
     * getter for numOfVehicles
     * @return numOfVehicles
     */
    public int getNumOfVehicles() {
        return numOfVehicles;
    }

    /**
     * getter for numOfJuncs
     * @return numOfJuncs
     */
    public int getNumOfJuncs() {
        return numOfJuncs;
    }

    /**
     * setter for numOfJuncs
     * @param numOfJuncs
     */
    public void setNumOfJuncs(int numOfJuncs) {
        this.numOfJuncs = numOfJuncs;
    }

    /**
     * setter for numOfVehicles
     * @param numOfVehicles
     */
    public void setNumOfVehicles(int numOfVehicles) {
        this.numOfVehicles = numOfVehicles;
    }

    /**
     * getter for currentMap
     * @return currentMap
     */
    public Map getCurrentMap() {
        return currentMap;
    }

    /**
     * setter for currentMap
     * @param currentMap
     */
    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }

    /**
     * getter for currentVehicles
     * @return list of current vehicles
     */
    public ArrayList<Vehicle> getCurrentVehicles() {
        return currentVehicles;
    }

    /**
     * setter of currentVehicles
     * @param currentVehicles
     */
    public void setCurrentVehicles(ArrayList<Vehicle> currentVehicles) {
        this.currentVehicles = currentVehicles;
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
    public void setDrivingTime(double drivingTime) {
        this.drivingTime = drivingTime;
    }

    /**
     * getter for maximum time
     * @return maxTime
     */
    public int getMaxTime() {
        return maxTime;
    }

    /**
     * setter for maximum time
     * @param maxTime
     */
    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    /**
     * getter for currentVehicles
     * @return currentVehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        return currentVehicles;
    }
}
