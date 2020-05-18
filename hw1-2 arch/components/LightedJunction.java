package components;
/**
 * Lighted Junction class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */
public class LightedJunction extends Junction {
    private TrafficLights lights;

    /**
     * Default constructor
     */
    public LightedJunction() {
        super();
        Init(getRandomBoolean(), getRandomBoolean());
    }

    /**
     * More specific constructor
     * @param name junction name
     * @param x junction x position
     * @param y junction y position
     * @param sequential are the lights are sequetial(true) or random(false)
     * @param lightsOn turn the lights on ?
     */
    public LightedJunction(String name, double x, double y, boolean sequential, boolean lightsOn) {
        super(name, x, y);
        Init(sequential, lightsOn);
    }

    /**
     * Internal init function to share code between constructors
     */
    private void Init(boolean sequential, boolean lightsOn) {       
        if (sequential)
            lights = new SequentialTrafficLights(getEnteringRoads());
        else
            lights = new RandomTrafficLights(getEnteringRoads());
        
        lights.setTrafficLightsOn(lightsOn);
    }

    @Override
    public String toString() {
        return super.toString() + " (Lighted)";
    }
    @Override
    public double calcEstimatedTime(Object object) {
        return lights.getdelay() * (getEnteringRoads().size() - 1) + 1;
    }
    @Override
    public boolean canLeave(Vehicle vehicle) {
        return vehicle.getLastRoad().getGreenLight() && vehicle.getLastRoad().getWaitingVehicles().indexOf(vehicle) == 0;
    }

	public TrafficLights getLights() {
		return lights;
	}
}
