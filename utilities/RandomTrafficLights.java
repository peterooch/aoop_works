package utilities;

import java.util.ArrayList;
import java.util.Random;

import components.Road;

public class RandomTrafficLights extends TrafficLights {

    public RandomTrafficLights(ArrayList<Road> roads) {
        super(roads);
    }

    @Override
    public void changeindex() {
        Random r = new Random();
        int I = this.getroads().size() * r.nextInt();
        this.changeLights(I);

    }
    
}