package components;

import java.util.EventListener;
/**
 * Vehicle listener class
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */
public interface VehicleListener extends EventListener{
    /** Notify the listener that the vehicle arrived to a junction */
    public void notifyJunctionArrival(Vehicle vehicle);
}