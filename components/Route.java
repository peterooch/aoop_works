package components;

import java.util.ArrayList;

/**
 * Route class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Route implements RouteParts {
    private Vehicle vehicle; // What is my use
    private ArrayList<RouteParts> routeParts;

    /**
     * Class constuctor
     * @param start initial segment
     * @param vehicle vehicle that will use the route
     */
    public Route(RouteParts start, Vehicle vehicle) {
        this.vehicle = vehicle;
        generateRoute(start, vehicle);
    }
    /**
     * Generate a 10 segment route starting from start
     * @param start initial segment
     * @param vehicle vehicle that will use the route
     */
    private void generateRoute(RouteParts start, Vehicle vehicle) {
        routeParts = new ArrayList<RouteParts>(10);
        int added = 1;
        routeParts.add(start);
        RouteParts curr = start;
        while (added < 10) {
            if (curr instanceof Junction) {
                Junction currJunc = (Junction)curr;

                if (currJunc.getExitingRoads().isEmpty())
                    break; // nothing to add
                
                curr = currJunc.getExitingRoads().get(getRandomInt(0, currJunc.getExitingRoads().size() - 1));
            } else if (curr instanceof Road) {
                Road currRoad = (Road)curr;

                curr = currRoad.getEndJunction();
            } else {
                break;
            }

            routeParts.add(curr);
            added++;
        }
        vehicle.setCurrentRoute(this);
    }
    /**
     * get the starting segment of the route
     */
    public RouteParts getStart() {
        return routeParts.get(0);
    }
    /**
     * get the final segment of the route
     */
    public RouteParts getEnd() {
        return routeParts.get(routeParts.size() - 1);
    }

    @Override
    public double calcEstimatedTime(Object object) {
        if (!(object instanceof Vehicle)) 
            return 0;

        double time = 0;

        for (RouteParts part : routeParts)
            time += part.calcEstimatedTime(object);

        return time;
    }

    @Override
    public boolean canLeave(Vehicle vehicle) {
        return vehicle.getCurrentRoutePart().equals(getEnd());
    }

    @Override
    public void checkIn(Vehicle vehicle) {
        // Its not really clear what is needed here...
        this.vehicle = vehicle;
        vehicle.setCurrentRoute(this);
    }

    @Override
    public void checkOut(Vehicle vehicle) {
        // "Releasing" the vehicle object....
        this.vehicle = null;
    }

    @Override
    public RouteParts findNextPart(Vehicle vehicle) {
        if (!canLeave(vehicle))
            return routeParts.get(routeParts.indexOf(vehicle.getCurrentRoutePart()) + 1);
        
        System.out.println(vehicle);
        System.out.println("- has finished the " + this);
        System.out.println("- Time spent on the route: " + vehicle.getTimeFromRouteStart());

        if (vehicle.getCurrentRoutePart() instanceof Junction) {
            Junction junc = (Junction)vehicle.getCurrentRoutePart();
            
            if (junc.getExitingRoads().isEmpty()) {
                generateRoute(getStart(), vehicle);
            }
            else {
                generateRoute(vehicle.getLastRoad(), vehicle);
            }
            System.out.println("- is starting a new " + this + ", estinated time for route: " + calcEstimatedTime(vehicle) + ".");
            return getStart();
        }
        return null; /* Should not happen */
    }

    @Override
    public void stayOnCurrentPart(Vehicle vehicle) {
        System.out.println(vehicle);
        System.out.println(vehicle + " is still moving on " + toString());
    }

    @Override
    public String toString() {
        return "Route from " + getStart() + " to " + getEnd();
    }
}
