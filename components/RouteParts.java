package components;

import utilities.Utilities;

/**
 * RouteParts Interface
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public interface RouteParts extends Utilities {
    public double calcEstimatedTime(Object object);
    public boolean canLeave(Vehicle vehicle);
    public void checkIn(Vehicle vehicle);
    public void checkOut(Vehicle vehicle);
    public RouteParts findNextPart(Vehicle vehicle);
    public void stayOnCurrentPart(Vehicle vehicle);
}
