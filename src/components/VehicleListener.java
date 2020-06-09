package components;

import java.util.EventListener;

public interface VehicleListener extends EventListener{
    public void notifyJunctionArrival(Vehicle vehicle);
}