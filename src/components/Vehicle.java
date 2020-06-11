/**
 * 
 */
package components;

import java.util.HashMap;
import java.util.Random;
import javax.swing.event.EventListenerList;

import utilities.Timer;
import utilities.VehicleType;

/**
 * @author Sophie Krimberg
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */
public class Vehicle implements Timer, ThreadedComponent {
    private int id;
    private VehicleType vehicleType;
    private Route currentRoute;
    private RouteParts currentRoutePart;
    private double timeFromRouteStart;
    private static int objectsCount = 1;
    private double timeOnCurrentPart;
    private Road lastRoad;
    private String status;
    private EventListenerList listeners;
    private double speed = -1;

    private static HashMap<Integer, Vehicle> allVehicles = new HashMap<Integer, Vehicle>();
    /**
     * Random Constructor
     * 
     * @param currentLocation
     */
    public Vehicle(Road currentLocation, VehicleType type) {
        id = objectsCount++;
        vehicleType = type;
        System.out.println();
        successMessage(this.toString());
        currentRoute = new Route(currentLocation, this); // creates a new route for the vehicle and checks it in
        lastRoad = currentLocation;
        status = null;
        listeners = new EventListenerList();

        allVehicles.put(id, this);
    }

    public Vehicle(Road currentLocation) {
        this(currentLocation,
             currentLocation.getVehicleTypes()[new Random().nextInt(currentLocation.getVehicleTypes().length - 1)]);
    }
    /** Prototype constructor (?) */
    public Vehicle(Road currentLocation, int prototypeID) {
        this(currentLocation, allVehicles.get(prototypeID).vehicleType);

        for (VehicleListener listener : allVehicles.get(prototypeID).listeners.getListeners(VehicleListener.class))
            listeners.add(VehicleListener.class, listener);
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the vehicleType
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * @param vehicleType the vehicleType to set
     */
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * @return the currentRoute
     */
    public Route getCurrentRoute() {
        return currentRoute;
    }

    /**
     * @param currentRoute the currentRoute to set
     */
    public void setCurrentRoute(Route currentRoute) {
        this.currentRoute = currentRoute;
    }

    /**
     * @return the currentRoutePart
     */
    public RouteParts getCurrentRoutePart() {
        return currentRoutePart;
    }

    /**
     * @param currentRoutePart the currentRoutePart to set
     */
    public void setCurrentRoutePart(RouteParts currentRoutePart) {
        this.currentRoutePart = currentRoutePart;
    }

    /**
     * @return the timeFromRouteStart
     */
    public double getTimeFromRouteStart() {
        return timeFromRouteStart;
    }

    /**
     * @param timeFromRouteStart the timeFromRouteStart to set
     */
    public void setTimeFromRouteStart(int timeFromRouteStart) {
        this.timeFromRouteStart = timeFromRouteStart;
    }

    /**
     * @return the timeOnCurrentPart
     */
    public double getTimeOnCurrentPart() {
        return timeOnCurrentPart;
    }

    /**
     * @param timeOnCurrentPart the timeOnCurrentPart to set
     */
    public void setTimeOnCurrentPart(int timeOnCurrentPart) {
        this.timeOnCurrentPart = timeOnCurrentPart;
    }

    /**
     * @return the lastRoad
     */
    public Road getLastRoad() {
        return lastRoad;
    }

    /**
     * @param lastRoad the lastRoad to set
     */
    public void setLastRoad(Road lastRoad) {
        this.lastRoad = lastRoad;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the objectsCount
     */
    public static int getObjectsCount() {
        return objectsCount;
    }

    @Override
    public void incrementDrivingTime() {
        timeFromRouteStart += 0.1;
        timeOnCurrentPart += 0.1;
        move();
    }

    /**
     * controls the vehicle moving from one route part to the next one
     * 
     */
    public void move() {
        if (currentRoutePart.canLeave(this)) {
            currentRoutePart.checkOut(this);

            if (currentRoutePart instanceof Road) {
                for (VehicleListener listener : listeners.getListeners(VehicleListener.class))
                    listener.notifyJunctionArrival(this);
            }

            currentRoute.findNextPart(this).checkIn(this);
        } else {
            currentRoutePart.stayOnCurrentPart(this);
        }
    }

    @Override
    public String toString() {
        return new String("Vehicle " + id + ": " + getVehicleType().name() + ", average speed: "
                + getVehicleType().getAverageSpeed());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (!super.equals(obj))
            return false;
        Vehicle other = (Vehicle) obj;
        if (this.currentRoute != other.currentRoute || this.currentRoutePart != other.currentRoutePart
                || this.id != other.id || this.lastRoad != other.lastRoad || this.status != other.status
                || this.timeFromRouteStart != other.timeFromRouteStart
                || this.timeOnCurrentPart != other.timeOnCurrentPart || this.vehicleType != other.vehicleType)
            return false;
        return true;
    }

    /**
     * @param objectsCount the objectsCount to set
     */
    public static void setObjectsCount(int objectsCount) {
        Vehicle.objectsCount = objectsCount;
    }

    public void addListener(VehicleListener listener) {
        listeners.add(VehicleListener.class, listener);
    }

    public void removeListener(VehicleListener listener) {
        listeners.remove(VehicleListener.class, listener);
    }

    /** ThreadedComponent interface code */
    /** Indicates if the thread to be paused */
    private boolean doPause = false;
    /** Indicates if the thread to be stopped */
    private boolean doRun = true;

    @Override
    public void run() {
        while (doRun) {
            try {
                if (doPause) {
                    synchronized (this) {
                        wait();
                    }
                }

                incrementDrivingTime();
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void pause() {
        doPause = true;
    }

    @Override
    public void resume() {
        synchronized (this) {
            doPause = false;
            notify();
        }
    }

    @Override
    public void stop() {
        doRun = false;

        if (doPause)
            resume();
    }

	public double getSpeed() {
        return (speed < 0) ? (double)Math.min(lastRoad.getMaxSpeed(), vehicleType.getAverageSpeed()) : speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
