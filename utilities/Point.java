package utilities;

public class Point {
    private static final double MINIMUM = 0F;
    private static final double MAXIMUM_X = 1000000F;
    private static final double MAXIMUM_Y = 800F;
    
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }  
    public boolean setX(double x) {
        if (x < MINIMUM || x > MAXIMUM_X)
            return false;

        this.x = x;
        
        return true;
    }  
    public double getY() {
        return y;
    }
    public boolean setY(double y) {
        if (y < MINIMUM || y > MAXIMUM_Y)
            return false;

        this.y = y;

        return true;
    }
    public String toString() {
        return "X: " + x + ", Y: " + y;
    }
    public boolean equals(Point other) {
        return other.x == x && other.y == y;
    }
}
