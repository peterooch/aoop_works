package utilities;

import java.util.ArrayList;
import java.util.Random;

import components.Road;

public class SequentialTrafficLights extends TrafficLights {
    private int increment;

    public SequentialTrafficLights(ArrayList<Road> roads) {
        super(roads);
        this.increment = 1;
    }

    @Override
    public void changeindex() {                                   ///not sure about this method cause its the same as RandoTrafficLights method
        Random r = new Random();
        int I = this.getroads().size() * r.nextInt();
        this.changeLights(I);

    }

    public int getincrement() {
        return increment;
    }

    public void setincrement(int increment) {
        this.increment =  increment;
        
    }
    
}