package components.factories;

import java.util.Random;
import components.*;

public class JFactory {
    private static final Random random = new Random();
    
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