package components;

public class LightedJunction extends Junction {
    private TrafficLights lights;

    public LightedJunction() {
        super();
        Init(getRandomBoolean(), getRandomBoolean());
    }

    public LightedJunction(String name, double x, double y, boolean sequential, boolean lightsOn) {
        super(name, x, y);
        Init(sequential, lightsOn);
    }

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
