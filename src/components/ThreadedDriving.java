package components;

import java.util.Vector;
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
        roadPanel = panel;
        components = new Vector<ThreadedComponent>(vehicles.size() + lights.size());

        components.addAll(vehicles);
        components.addAll(lights);
        /** do one iteration to juggle vehicles into their roads */
        incrementDrivingTime();
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
