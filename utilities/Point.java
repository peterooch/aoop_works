package utilities;

/**
 * Point coordinate class
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */

public class Point {
    /**
     * Minimal X/Y value
     */
    private static final double MINIMUM = 0F;
    /**
     * Maximal X value
     */
    private static final double MAXIMUM_X = 1000000F;
    /**
     * Maximal Y value
     */
    private static final double MAXIMUM_Y = 800F;
    
    private double x;
    private double y;

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
