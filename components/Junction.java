package components;

import java.util.ArrayList;
import java.util.Random;
import utilities.Point;

/**
 * Junction class
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */

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
        enteringRoads = new ArrayList<>();
        exitingRoads = new ArrayList<>();
        vehicles = new ArrayList<>();
        hasLights = true; // for now...

        System.out.printf("%s object has been created\n", toString());
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
        if (enteringRoads.isEmpty()) {
            System.out.printf("%s: No roads enter the junction\n", toString());
            return;
        }
        Road first = enteringRoads.get(0);
        first.setIsOpen(true);
        System.out.printf("%s : green light\n", first.toString());
        /** Shift them all */
        enteringRoads.remove(first);

        for (Road road : enteringRoads) {
            road.setIsOpen(false);
        }

        enteringRoads.add(first);

    }
    public boolean checkAvailability(Road road) {
        for (Road r : enteringRoads) {
            if (vehicles.contains(r))
                return r.equals(road);
        }
        return true;
    }
    public String toString() {
        return "Junction " + junctionName;
    }
    public boolean equals(Junction other) {
        return junctionName.equals(other.junctionName) && location.equals(other.location);
    }
	public void setLightsOn() {
        hasLights = true;
        if (enteringRoads.size() > 0)
            System.out.printf("%s traffic lights ON, Delay time: %d\n", toString(), delay);
        else
            System.out.printf("%s no entry roads, no lights exist to be turned on\n", toString());
	}
}
