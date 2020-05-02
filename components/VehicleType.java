package components;

import java.util.ArrayList;
import java.util.Random;

/**
 * VehicleType enumeration
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public enum VehicleType {
    car(90),
    bus(60),
    bicycle(40),
    motorcycle(120),
    truck(80),
    tram(50),
    semitrailer(85);
    
    private int averageSpeed;

    /**
     * Enumeration constructor
     * @param speed Vehicle average speed
     */
    VehicleType(int speed) {;
        averageSpeed = speed;
    }
    
    /**
     * toString method
     * @return String representation
     */
    @Override
    public String toString() {
        return "Type: " + super.toString() + ", Average speed: " + averageSpeed;
    }

    /**
     * getter for speed
     * @return Average speed
     */
    public int getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * Function that fetches a random vehicle type value
     * @return a specific vehicle type
     */
    public static VehicleType getRandomVehicleType() {
        return values()[new Random().nextInt(values().length)];
    }

    /**
     * Function that fetches a list of random vehicle types
     * @return list of vehicle types
     */
    public static ArrayList<VehicleType> getRandomVehicleTypes() {
        Random randObj = new Random();
        int rand_size = randObj.nextInt(values().length) + 1;
        ArrayList<VehicleType> result = new ArrayList<VehicleType>(rand_size);

        for (int i = 0; i < rand_size; i++) {
            VehicleType type = getRandomVehicleType();

            if (!result.contains(type))
                result.add(type);
        }
        return result;
    }
}
