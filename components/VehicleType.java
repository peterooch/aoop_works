package components;

import java.util.ArrayList;
import java.util.Random;

/**
 * VehicleType class
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */

public class VehicleType {
    private String typeName;
    private int speed;

    private static ArrayList<VehicleType> vehiclesTypes = new ArrayList<>();
    
    static {
        if (vehiclesTypes.size() == 0) {
            /** Copypasta from example  */
            vehiclesTypes.add(new VehicleType("bus", 60));
            vehiclesTypes.add(new VehicleType("motorcycle", 120));
            vehiclesTypes.add(new VehicleType("truck", 80));
            vehiclesTypes.add(new VehicleType("semi-trailer", 80));
            vehiclesTypes.add(new VehicleType("car", 90));
            vehiclesTypes.add(new VehicleType("bicycle", 30));
        }
    }

    public VehicleType(String typeName, int speed) {
        this.typeName = typeName;
        this.speed = speed;
        
        if (!vehiclesTypes.contains(this))
            vehiclesTypes.add(this);
    }
    
    public String toString() {
        return "Type: " + typeName + ", Speed: " + speed;
    }

    public boolean equals(VehicleType other) {
        return typeName.equals(other.typeName);
    }
    
	public int getSpeed() {
        return speed;
	}
    
	public static VehicleType getRandomVehicleType() {
		return vehiclesTypes.get(new Random().nextInt(vehiclesTypes.size()));
    }

    public static ArrayList<VehicleType> getRandomVehicleTypes() {
        Random randObj = new Random();
        int rand_size = (vehiclesTypes.size() > 0) ?  randObj.nextInt(vehiclesTypes.size()) + 1 : 0;
        ArrayList<VehicleType> result = new ArrayList<VehicleType>(rand_size);

        for (int i = 0; i < rand_size; i++) {
            VehicleType type = getRandomVehicleType();

            if (!result.contains(type))
                result.add(type);
        }
        return result;
    }
}
