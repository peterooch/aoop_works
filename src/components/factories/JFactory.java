package components.factories;

import java.util.Random;
import components.*;

/** Junction factory
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */
public class JFactory {
    private static final Random random = new Random();
    
    /** Junction supplier
     * @param env the enviroment selected for the junction
     */
    public static Junction getJunction(String env) {
        if ("city".equals(env))
            return new LightedJunction();

        if ("country".equals(env))
            return random.nextBoolean() ? new LightedJunction() : new Junction();

        return null;
    }
    /** Make this a static class */
    private JFactory() {}
}