package components;

import java.util.ArrayList;
import utilities.Utilities;
import utilities.Timer;

/**
 * Traffic Lights abstract class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */
public abstract class TrafficLights implements Timer, Utilities {
    private static int objectsCount = 1;
    private int delay;
    private int greenLightIndex;
    private int id;
    private static final int minDelay = 2;
    private static final int maxDelay = 6;
    private ArrayList<Road> roads;
    private boolean trafficLightsOn;
    private int workingTime;

    /**
     * Class contstructor
     * @param roads list of roads that the light is controlling
     */
    public TrafficLights(ArrayList<Road> roads){
        id = objectsCount++;
        this.roads = roads;
        greenLightIndex = 0;
        workingTime = 0;
        setTrafficLightsOn(false);
    }

    public abstract void changeIndex();

    /**
     * Switch the current green light road to a different one
     * @param nextRoadIndex green light road index
     */
    public void changeLights(int nextRoadIndex){
        greenLightIndex = nextRoadIndex % roads.size();

        for (int i = 0; i < roads.size(); i++) {
            roads.get(i).setGreenLight((i == greenLightIndex) ? true : false);
        }

        System.out.print((this instanceof RandomTrafficLights) ? "Random " : "Sequential ");
        System.out.println("traffic lights " + id + " turned " + ((trafficLightsOn) ? "ON" : "OFF") + ", delay time: " + delay);
        System.out.println("- " + roads.get(greenLightIndex));

    }
    @Override
    public void incrementDrivingTime() {
        this.workingTime++;
        if (workingTime % delay == 0){
            changeIndex();
        }
    }

    public int getObjectsCount() {
        return objectsCount;
    }

    public int getdelay() {
        return delay;
    }

    public void setdelay(int delay) {
        this.delay = delay;
    }

    public int getGreenLightIndex() {
        return greenLightIndex;
    }

    public void setGreenLightIndex(int greenLightIndex) {
        this.greenLightIndex = greenLightIndex;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getminDelay() {
        return minDelay;
    }

    public int getmaxDelay() {
        return maxDelay;
    }

    public ArrayList<Road> getroads() {
        return roads;
    }

    public void setroads( ArrayList<Road> roads ) {
        this.roads = roads;
    }

    public boolean gettrafficLightsOn() {
        return trafficLightsOn;
    }

    public void setTrafficLightsOn(boolean trafficLightsOn) {
        if (trafficLightsOn)
            delay = getRandomInt(minDelay, maxDelay);
        else
            delay = 0;

        this.trafficLightsOn = trafficLightsOn;
        if (roads.isEmpty())
            return;

        System.out.print((this instanceof RandomTrafficLights) ? "Random " : "Sequential ");
        System.out.println("traffic lights " + id + " turned " + ((trafficLightsOn) ? "ON" : "OFF") + ", delay time: " + delay);
        System.out.println("- " + roads.get(0));
    }

    public int getworkingTime() {
        return workingTime;
    }

    public void setworkingTime(int workingTime) {
        this.workingTime =  workingTime;
    }

    @Override
    public String toString() {                                  ///not sure about this method
        return ("Trafficlight " + id);
    }

    @Override
    public boolean equals(Object other) {                       ///not sure about this method
        if (other == this) { 
            return true; 
        }
        if (!(other instanceof TrafficLights)) { 
            return false; 
        }   
        TrafficLights c = (TrafficLights) other;

        return (id == c.id);
    }

}