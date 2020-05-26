package components;

import java.util.Vector;
import gui.RoadPanel;

public class ThreadedDriving extends Driving implements ThreadedComponent {
    private RoadPanel roadPanel;
    private Vector<ThreadedComponent> components;

    public ThreadedDriving(RoadPanel panel, int junctionsNum, int numOfVehicles) {
        super(junctionsNum, numOfVehicles);
        roadPanel = panel;
        components = new Vector<ThreadedComponent>(vehicles.size() + lights.size());

        components.addAll(vehicles);
        components.addAll(lights);
    }

    /** ThreadedComponent boilerplate */
    private final Object monitor = new Object();
    private boolean doPause = false;
    private boolean doRun = true;

    @Override
    public void run() {
        for (ThreadedComponent component : components)
            new Thread(component, component.toString()).start();

        while (doRun) {
            try {
                if (doPause) {
                    synchronized (monitor) {
                        monitor.wait();
                    }
                }

                roadPanel.repaint();
                Thread.sleep(100);

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void pause() {
        synchronized (this) {
            doPause = true;
            for (ThreadedComponent component : components)
                component.pause();
        }
    }

    @Override
    public void resume() {
        synchronized (monitor) {
            doPause = false;
            monitor.notify();

            for (ThreadedComponent component : components)
                component.resume();
        }
    }

    @Override
    public void stop() {
        doRun = false;

        for (ThreadedComponent component : components)
            component.stop();
    }
}
