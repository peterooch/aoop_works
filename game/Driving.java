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
 */

public class Driving {
    private int numOfJuncs;
    private int numOfVehicles;
    private Map currentMap;
    private ArrayList<Vehicle> currentVehicles;
    private double drivingTime;
    private int maxTime;

    public Driving(int numOfJuncs, int numOfVehicles, int maxTime) {
        this.numOfJuncs = numOfJuncs;
        this.numOfVehicles = numOfVehicles;
        this.maxTime = maxTime;

        currentMap = new Map(numOfJuncs);
        currentVehicles = new ArrayList<Vehicle>(numOfVehicles);
        addVehicles(numOfVehicles);
    }

    public void addMap() {
        int count = new Random().nextInt(16) + 10;

        currentMap = new Map(count, count);
    }

    private void addVehicles(int count) {
        currentVehicles = new ArrayList<Vehicle>(count);

        for (int i = 0; i < count; i++) {
            currentVehicles.add(new Vehicle(i, VehicleType.getRandomVehicleType(),
                    currentMap.getJunctions().get(new Random().nextInt(currentMap.getJunctions().size()))));
        }
    }

    public void addVehicles() {
        addVehicles(new Random().nextInt(7) + 2);
    }

    public void startDrive(int maxTime) {

    }

    public String toString() {
        return "Number of junctions: " + numOfJuncs + ", Number of vehicles: " + numOfVehicles;
    }

    public boolean equals(Driving other) {
        return currentMap == other.currentMap;
    }

    public int getNumOfVehicles() {
        return numOfVehicles;
    }

    public int getNumOfJuncs() {
        return numOfJuncs;
    }

    public void setNumOfJuncs(int numOfJuncs) {
        this.numOfJuncs = numOfJuncs;
    }

    public void setNumOfVehicles(int numOfVehicles) {
        this.numOfVehicles = numOfVehicles;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }

    public ArrayList<Vehicle> getCurrentVehicles() {
        return currentVehicles;
    }

    public void setCurrentVehicles(ArrayList<Vehicle> currentVehicles) {
        this.currentVehicles = currentVehicles;
    }

    public double getDrivingTime() {
        return drivingTime;
    }

    public void setDrivingTime(double drivingTime) {
        this.drivingTime = drivingTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public ArrayList<Vehicle> getVehicles() {
        return currentVehicles;
    }
}
