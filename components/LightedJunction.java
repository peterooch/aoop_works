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
    public double calcEstimatedTime(Object object) {
        // TODO method stub
        return 0;
    }
    @Override
    public boolean canLeave(Vehicle vehicle) {
        // TODO method stub
        return false;
    }

	public TrafficLights getLights() {
		return lights;
	}
}
