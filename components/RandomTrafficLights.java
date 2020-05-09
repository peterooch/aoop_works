package components;

import java.util.ArrayList;
import java.util.Random;

public class RandomTrafficLights extends TrafficLights {

    public RandomTrafficLights(ArrayList<Road> roads) {
        super(roads);
    }

    @Override
    public void changeIndex() {
        Random r = new Random();
        int I = this.getroads().size() * r.nextInt();
        this.changeLights(I);
    }
}