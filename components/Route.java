package components;

import java.util.ArrayList;

/**
 * Route class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Route implements RouteParts {
    private Vehicle vehicle;
    private ArrayList<RouteParts> routeParts;

    public Route(RouteParts start, Vehicle vehicle) {
        this.vehicle = vehicle;
        // TODO build route
        routeParts = new ArrayList<RouteParts>(10);
    }

    @Override
    public double calcEstimatedTime(Object object) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean canLeave(Vehicle vehicle) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void checkIn(Vehicle vehicle) {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkOut(Vehicle vehicle) {
        // TODO Auto-generated method stub

    }

    @Override
    public RouteParts findNextPart(Vehicle vehicle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void stayOnCurrentPart(Vehicle vehicle) {
        // TODO Auto-generated method stub

    }
}
