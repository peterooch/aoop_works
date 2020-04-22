package components;

import java.util.ArrayList;
import java.util.Random;
import utilities.Point;

/**
 * Junction class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 * 
 */



public class Junction {
	
    /**
     * String value for junction name
     */
    private String junctionName;
    /**
     * Point that stands for location
     */
    private Point location;
    /**
     * Array for entering roads
     */
    private ArrayList<Road> enteringRoads;
    /**
     * Array for exiting Roads
     */
    private ArrayList<Road> exitingRoads;
    /**
     * boolean value that says if a juction has lights
     */
    private boolean hasLights;
    /**
     * int value for delay
     */
    private int delay;
    /**
     * Array of vehicles
     */
    private ArrayList<Road> vehicles;

    /**
     * constructor
     *
     * @param name to name the junction
     * @param loc for location
     */
    public Junction(String name, Point loc) {
        Random randObj = new Random();
        junctionName = name;
        location = loc;
        delay = randObj.nextInt(10) + 1;
        enteringRoads = new ArrayList<>();
        exitingRoads = new ArrayList<>();
        vehicles = new ArrayList<>();
        hasLights = randObj.nextBoolean();

        System.out.printf("%s object has been created\n", toString());
    }

    /**
     * junction name setter
     * @param name
     */
    public void setJunctionName(String name) {
        junctionName = name;
    }

    /**
     * junction name getter
     * @return the junction name
     */
    public String getJunctionName() {
        return junctionName;
    }

    /**
     * location setter
     * @param loc for the location
     */
    public void setLocation(Point loc) {
        location = loc;
    }

    /**
     * location getter
     * @return the location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * entering roads setter
     * @param list of entring roads
     */
    public void setEnteringRoads(ArrayList<Road> list) {
        this.enteringRoads = list;
    }

    /**
     * entering roads getter
     * @return list of entring roads
     */
    public ArrayList<Road> getEnteringRoads() {
        return enteringRoads;
    }

    /**
     * setter of exiting roads
     * @param list of exiting roads
     */
    public void setExitingRoads(ArrayList<Road> list) {
        this.exitingRoads = list;
    }

    /**
     * getter of exiting roads
     * @return list of exiting roads
     */
    public ArrayList<Road> getExitingRoads() {
        return exitingRoads;
    }

    /**
     * setter of the vehicles list
     * @param list of vehicles
     */
    public void setVehicles(ArrayList<Road> list) {
        this.vehicles = list;
    }

    /**
     * getter for vehicles
     * @return list of vehicles
     */
    public ArrayList<Road> getVehicles() {
        return vehicles;
    }

    /**
     * setter for "Has lights" boolean value
     * @param hasLights
     */
    public void setHasLights(boolean hasLights) {
        this.hasLights = hasLights;
    }

    /**
     * getter for hasLights
     * @return  hasLights
     */
    public boolean getHasLights() {
        return hasLights;
    }

    /**
     * setter for delay
     * @param delay
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * getter for delay integer
     * @return  delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * function that changes the the lights of the function
     */
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

    /**
     * function thats checks the availability of the junction
     * @param road
     * @return boolean value that stands for the availability of the junction
     */
    public boolean checkAvailability(Road road) {
        for (Road r : enteringRoads) {
            if (vehicles.contains(r))
                return r.equals(road);
        }
        return true;
    }


    /**
     * function that returns the junction name but as a String value
     */
    public String toString() {
        return "Junction " + junctionName;
    }

    /**
     * function that says if two values are equals
     */
    public boolean equals(Object other) {
        if (other instanceof Junction) {
            Junction other_junc = (Junction)other;
            return junctionName.equals(other_junc.junctionName) && location.equals(other_junc.location);
        }
        return false;
    }

    /**
     * function that set the lights of the junction on
     */
    public void setLightsOn() {
        hasLights = true;
        if (enteringRoads.size() > 0)
            System.out.printf("%s traffic lights ON, Delay time: %d\n", toString(), delay);
        else
            System.out.printf("%s no entry roads, no lights exist to be turned on\n", toString());
    }
}
