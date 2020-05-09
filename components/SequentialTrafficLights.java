package components;

import java.util.ArrayList;
import java.util.Random;

/**
 * Sequential Traffic Lights class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */
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