package components;

public class LightedJunction extends Junction {
    private TrafficLights lights;

    public LightedJunction() {
        super();
        // TODO randomly enable disable something in lights
    }
    public LightedJunction(String name, double x, double y, boolean sequential, boolean lightsOn) {
        super(name, x, y);
        // TODO use the booleans on lights
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
}
