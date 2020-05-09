package components;

import java.util.ArrayList;
import java.util.Random;

public class SequentialTrafficLights extends TrafficLights {
    private int increment;

    public SequentialTrafficLights(ArrayList<Road> roads) {
        super(roads);
        this.increment = 1;
    }

    @Override
    public void changeIndex() {                                   ///not sure about this method cause its the same as RandoTrafficLights method
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