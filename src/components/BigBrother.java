package components;

import java.time.LocalTime;

public class BigBrother implements VehicleListener {
    /************************* SINGLETON CODE ***********************/
    /** Class Instance */
    private static volatile BigBrother singleton;
    /** Class Instance access method
     * 
     * @return The BigBrother Singleton
     */
    public static BigBrother getInst() {   
        if (singleton == null) {
            synchronized (BigBrother.class) {
                if (singleton == null)
                    singleton = new BigBrother();
            }
        }
        return singleton;
    }
    /************************* CLASS CODE ***************************/
    private Moked moked;
    /** Class constructor */
    private BigBrother() {
        moked = new Moked("reports.txt");
    }

    public Moked getMoked() {
        return moked;
    }

    @Override
    public void notifyJunctionArrival(Vehicle vehicle) {
        double estimatedSpeed = vehicle.getLastRoad().getLength() / vehicle.getTimeOnCurrentPart();

        if (estimatedSpeed > vehicle.getLastRoad().getMaxSpeed())
            moked.writeReport(vehicle.getId(), LocalTime.now());
    }
}