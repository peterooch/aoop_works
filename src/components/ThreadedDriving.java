package components;

import java.util.Vector;

import components.builders.Builder;
import gui.RoadPanel;

/**
 * Threaded extension of the Driving class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */
public class ThreadedDriving extends Driving implements ThreadedComponent {
    private RoadPanel roadPanel;
    private Vector<ThreadedComponent> components;

    public ThreadedDriving(RoadPanel panel, int junctionsNum, int numOfVehicles) {
        super(junctionsNum, numOfVehicles);
        internalInit(panel);
    }

    public ThreadedDriving(RoadPanel panel, Builder builder) {
        super(builder);
        internalInit(panel);
    }

    private void internalInit(RoadPanel panel) {
        roadPanel = panel;
        components = new Vector<ThreadedComponent>(vehicles.size() + getMap().getLights().size());

        for (Vehicle vehicle : vehicles)
            vehicle.addListener(BigBrother.getInst());

        components.addAll(vehicles);
        components.addAll(getMap().getLights());
        /** do one iteration to juggle vehicles into their roads */
        incrementDrivingTime();
    }

    public void addVehicle(int vehicleID) {
        Vehicle vehicle = new Vehicle(getMap().getRoads().get(getRandomInt(0, getMap().getRoads().size())), vehicleID);
        
        vehicle.addListener(BigBrother.getInst());
        vehicles.add(vehicle);
        components.add(vehicle);

        new Thread(vehicle, vehicle.toString()).start();
        
        if (doPause)
            vehicle.pause();
    }
    /** ThreadedComponent interface code */
    /** Indicates if the thread to be paused */
    private boolean doPause = false;
    /** Indicates if the thread to be stopped */
    private boolean doRun = true;

    @Override
    public void run() {
        for (ThreadedComponent component : components)
            new Thread(component, component.toString()).start();

        while (doRun) {
            try {
                if (doPause) {
                    synchronized (this) {
                        wait();
                    }
                }

                roadPanel.repaint();
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  Pauses the thread and the threads created for traffic lights and vehicles
     */
    @Override
    public void pause() {
        synchronized (this) {
            doPause = true;
            for (ThreadedComponent component : components)
                component.pause();
        }
    }

    /**
     * Resumes the thread and the threads created for traffic lights and vehicles
     */
    @Override
    public void resume() {
        synchronized (this) {
            doPause = false;
            notify();

            for (ThreadedComponent component : components)
                component.resume();
        }
    }

    /** 
     * Stops the thread and the threads created for traffic lights and vehicles
     */
    @Override
    public void stop() {
        doRun = false;

        if (doPause)
            resume();

        for (ThreadedComponent component : components)
            component.stop();
    }
}
