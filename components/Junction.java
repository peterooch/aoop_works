package components;

import java.util.ArrayList;
import java.util.Random;
import utilities.Point;

public class Junction {
    private String junctionName;
    private Point location;
    private ArrayList<Road> enteringRoads;
    private ArrayList<Road> exitingRoads;
    private boolean hasLights;
    private int delay;
    private ArrayList<Road> vehicles;

    public Junction(String name, Point loc) {
        junctionName = name;
        location = loc;
        delay = new Random().nextInt(10) + 1;
    }
    public void setJunctionName(String name) {
        junctionName = name;
    }
    public String getJunctionName() {
        return junctionName;
    }
    public void setLocation(Point loc) {
        location = loc;
    }
    public Point getLocation() {
        return location;
    }
    public void setEnteringRoads(ArrayList<Road> list) {
        this.enteringRoads = list;
    }
    public ArrayList<Road> getEnteringRoads() {
        return enteringRoads;
    }
    public void setExitingRoads(ArrayList<Road> list) {
        this.exitingRoads = list;
    }
    public ArrayList<Road> getExitingRoads() {
        return exitingRoads;
    }
    public void setVehicles(ArrayList<Road> list) {
        this.vehicles = list;
    }
    public ArrayList<Road> getVehicles() {
        return vehicles;
    }
    public void setHasLights(boolean hasLights) {
        this.hasLights = hasLights;
    }
    public boolean getHasLights() {
        return hasLights;
    }
    public void setDelay(int delay) {
        this.delay = delay;
    }
    public int getDelay() {
        return delay;
    }
    public void changeLight() {
        enteringRoads.get(0).setIsOpen(true);

        for (int i = 0; i < enteringRoads.size(); i++)
            enteringRoads.get(i).setIsOpen(false);
    }
    public boolean checkAvailability(Road road) {
        for (Road r : enteringRoads) {
            if (vehicles.contains(r))
                return r.equals(road);
        }
        return true;
    }
    public String toString() {
        return "Junction name: " + junctionName + ", Location: " + location.toString();
    }
    public boolean equals(Junction other) {
        return junctionName.equals(other.junctionName) && location.equals(other.location);
    }
}
