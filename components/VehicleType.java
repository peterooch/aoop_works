package components;

import java.util.ArrayList;
import java.util.Random;

/**
 * VehicleType class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class VehicleType {
    private String typeName;
    private int speed;

    private static ArrayList<VehicleType> vehiclesTypes;

    static {
        /** Copypasta from example */
        vehiclesTypes = new ArrayList<VehicleType>();
        vehiclesTypes.add(new VehicleType("bus", 60, false));
        vehiclesTypes.add(new VehicleType("motorcycle", 120, false));
        vehiclesTypes.add(new VehicleType("truck", 80, false));
        vehiclesTypes.add(new VehicleType("semi-trailer", 80, false));
        vehiclesTypes.add(new VehicleType("car", 90, false));
        vehiclesTypes.add(new VehicleType("bicycle", 30, false));
    }

    /**
     * constructor that gets three parameters:
     * @param typeName
     * @param speed
     * @param add_to_list
     */
    private VehicleType(String typeName, int speed, boolean add_to_list) {
        this.typeName = typeName;
        this.speed = speed;

        if (add_to_list && !vehiclesTypes.contains(this))
            vehiclesTypes.add(this);
    }

    /**
     * constructor that gets two parameters:
     * @param typeName
     * @param speed
     */
    public VehicleType(String typeName, int speed) {
        this(typeName, speed, true);
    }

    public String toString() {
        return "Type: " + typeName + ", Speed: " + speed;
    }

    public boolean equals(VehicleType other) {
        return typeName.equals(other.typeName) && speed == other.speed;
    }

    /**
     * getter for speed
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * getter for name
     * @return typeName
     */
    public String getName() {
        return typeName;
    }

    /**
     * function that returns Random vehicle type
     * @return vehicle type
     */
    public static VehicleType getRandomVehicleType() {
        return vehiclesTypes.get(new Random().nextInt(vehiclesTypes.size()));
    }

    /**
     * function that returns a list of random vehicle types
     * @return list vehicle types
     */
    public static ArrayList<VehicleType> getRandomVehicleTypes() {
        Random randObj = new Random();
        int rand_size = (!vehiclesTypes.isEmpty()) ? randObj.nextInt(vehiclesTypes.size()) + 1 : 0;
        ArrayList<VehicleType> result = new ArrayList<VehicleType>(rand_size);

        for (int i = 0; i < rand_size; i++) {
            VehicleType type = getRandomVehicleType();

            if (!result.contains(type))
                result.add(type);
        }
        return result;
    }
}
