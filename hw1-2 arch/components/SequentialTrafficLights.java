package components;

import java.util.ArrayList;

/**
 * Sequential Traffic Lights class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */
public class SequentialTrafficLights extends TrafficLights {
    private int increment;

    /**
     * Class contstructor
     * @param roads list of roads that the light is controlling
     */
    public SequentialTrafficLights(ArrayList<Road> roads) {
        super(roads);
        this.increment = 1;
    }

    /**
     * Switch to the next road
     */
    @Override
    public void changeIndex() {                                   ///not sure about this method cause its the same as RandoTrafficLights method
        this.changeLights(++increment);
    }

    public int getincrement() {
        return increment;
    }

    public void setincrement(int increment) {
        this.increment =  increment;   
    }
}