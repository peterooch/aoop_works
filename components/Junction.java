package components;

import java.util.ArrayList;
import utilities.Point;

/**
 * Junction class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 * 
 */

public class Junction extends Point implements RouteParts {
    
    private static int objectsCount = 1;
    /**
     * String value for junction name
     */
    private String junctionName;
    /**
     * Array for entering roads
     */
    private ArrayList<Road> enteringRoads;
    /**
     * Array for exiting Roads
     */
    private ArrayList<Road> exitingRoads;

    /**
     * constructor
     *
     * @param name Junction name
     * @param loc Junction location
     */
    public Junction(String name, double x, double y) {
        super(x, y);
        Init(name);
    }
    public Junction() {
        super();
        Init("Junction " + objectsCount);
    }

    private void Init(String name) {
        junctionName = name;
        enteringRoads = new ArrayList<>();
        exitingRoads = new ArrayList<>();
        objectsCount++;
        successMessage(toString());
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
     * entering roads setter
     * @param list new list of entring roads
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
     * @param list new list of exiting roads
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
     * function that returns the junction name but as a String value
     */
    @Override
    public String toString() {
        return "Junction " + junctionName;
    }

    /**
     * function that says if two values are equals
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Junction) {
            Junction other_junc = (Junction)other;
            return junctionName.equals(other_junc.junctionName) && super.equals(other_junc);
        }
        return false;
    }
    
    public void addEnteringRoad(Road road) {
        enteringRoads.add(road);
    }

    public void addExitingRoad(Road road) {
        exitingRoads.add(road);
    }

    @Override
    public double calcEstimatedTime(Object object) {
        if (!(object instanceof Vehicle))
            return 0;
        
        Vehicle vehicle = (Vehicle)object;

        return enteringRoads.indexOf(vehicle.getLastRoad()) + 1;
    }
    public static int getJunctionCount() {
        return objectsCount;
    }
    /**
     * function thats checks the availability of the junction
     * @param vehicle 
     * @return boolean value that stands for the availability of the junction
     */
    public boolean checkAvailability(Vehicle vehicle) {
        // TODO to be determined
        return false;
    }

    @Override
    public boolean canLeave(Vehicle vehicle) {
        return checkAvailability(vehicle);
    }

    @Override
    public void checkIn(Vehicle vehicle) {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkOut(Vehicle vehicle) {
        // TODO Auto-generated method stub

    }

    @Override
    public RouteParts findNextPart(Vehicle vehicle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void stayOnCurrentPart(Vehicle vehicle) {
        // TODO Auto-generated method stub

    }
}
