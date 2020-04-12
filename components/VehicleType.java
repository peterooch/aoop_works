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

    private static ArrayList<VehicleType> vehiclesTypes;

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

	public static VehicleType getRandomVehicleTypes() {
		return vehiclesTypes.get(new Random().nextInt(vehiclesTypes.size()));
	}
}
