package components;

import java.util.ArrayList;

/**
 * Random Traffic Lights class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */
public class RandomTrafficLights extends TrafficLights {

    public RandomTrafficLights(ArrayList<Road> roads) {
        super(roads);
    }

    @Override
    public void changeIndex() {
        this.changeLights(getRandomInt(0, getroads().size() - 1));
    }
}