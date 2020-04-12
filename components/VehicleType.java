package components;

public class VehicleType {
    private String typeName;
    private int speed;

    public VehicleType(String typeName, int speed) {
        this.typeName = typeName;
        this.speed = speed;
    }

    public String toString() {
        return "Type: " + typeName + ", Speed: " + speed;
    }

    public boolean equals(VehicleType other) {
        return typeName.equals(other.typeName);
    }
}
