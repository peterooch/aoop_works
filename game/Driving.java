package game;

import java.util.ArrayList;

import components.Map;
import components.Vehicle;

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
    }
    public void addMap() {

    }
    public void addVehicles() {

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
}
