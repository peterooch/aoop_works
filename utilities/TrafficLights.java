package utilities;

import java.util.ArrayList;
import java.util.Random;

import components.*;

public abstract class TrafficLights {
    private int objectsCount;
    private int delay;
    private int greenLightindex;
    private int id;
    private int minDelay;
    private int maxDelay;
    private ArrayList<Road> roads;
    private boolean trafficLightsOn;
    private int workingTime;


    public TrafficLights(ArrayList<Road> roads){
        this.objectsCount++;
        Random r = new Random();
        this.delay = minDelay + (maxDelay- minDelay) * r.nextInt();
        this.minDelay = 2;
        this.maxDelay = 6;
        this.roads = roads;
        this.workingTime = 0;
        for(int i = 0 ; i < roads.size() ; i++){
            if(roads.get(i).getIsOpen() == true){
                this.greenLightindex = i;
            }
        }
        this.trafficLightsOn = false;                           ///didnt know what to do with this field
        this.id = 1122;                                         ///same as  ^^
    }

    public abstract void changeindex();

    public void changeLights(int nextRoadIndex){
        for(int i = 0 ; i < roads.size() ; i++){
            roads.get(i).setIsOpen(false);
        }
        roads.get(nextRoadIndex).setIsOpen(true);

        System.out.println("Sequential traffic lights " + nextRoadIndex + " turned ON, delay time: " + delay);              ///not sure about this message

    }

    public void incrementDrivingTime(){                         ///not complete, how do we know if its time to change the lights?
        this.workingTime++;
    }


    public int getobjectsCount() {
        return objectsCount;
    }

    public void setobjectsCount(int objectsCount) {
        this.objectsCount = objectsCount;
    }

    public int getdelay() {
        return delay;
    }

    public void setdelay(int delay) {
        this.delay = delay;
    }

    public int getgreenLightindex() {
        return greenLightindex;
    }

    public void setgreenLightindex(int greenLightindex) {
        this.greenLightindex = greenLightindex;
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

    public void setminDelay(int minDelay) {
        this.minDelay = minDelay;
    }

    public int getmaxDelay() {
        return maxDelay;
    }

    public void setmaxDelay( int maxDelay) {
        this.maxDelay = maxDelay;
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

    public void settrafficLightsOn(boolean trafficLightsOn) {
        this.trafficLightsOn = trafficLightsOn;
    }

    public int getworkingTime() {
        return workingTime;
    }

    public void setworkingTime( int workingTime) {
        this.workingTime =  workingTime;
    }

    @Override
    public String toString() {                                  ///not sure about this method
        return ("Trafficlight " + getid());
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

        return Double.compare(id, c.id) == 0;
    }

}