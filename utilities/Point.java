package utilities;

import java.util.Random;

/**
 * Point coordinate class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */

public class Point {
    /**
     * Minimal X/Y value
     */
    public static final double MINIMUM = 0F;
    /**
     * Maximal X value
     */
    public static final double MAXIMUM_X = 1000000F;
    /**
     * Maximal Y value
     */
    public static final double MAXIMUM_Y = 800F;

    /**
     * Point's x position
     */
    private double x;
    /**
     * Point's y position
     */
    private double y;

    /**
     * Point class constructor
     * 
     * @param x Position in the x axis, between 0 to 1000000
     * @param y Position in the y axis, between 0 to 800
     */
    public Point(double x, double y) {
        Random randObj = new Random();

        if (!intSetX(x, true)) {
            this.x = randObj.nextDouble() * MAXIMUM_X;
            System.out.printf(", therefore it was replaced by %f\n", this.x);
        }
        if (!intSetY(y, true)) {
            this.y = randObj.nextDouble() * MAXIMUM_Y;
            System.out.printf(", therefore it was replaced by %f\n", this.y);
        }

        System.out.printf("Point object %s has been created\n", toString());
    }

    /**
     * X position getter
     * 
     * @return Position on the x axis
     */
    public double getX() {
        return x;
    }

    /**
     * Internal X postion setter
     * 
     * @param x           new X position
     * @param constructor Is the function called from the constructor?
     * @return true if x is in the valid range, false otherwise
     */
    private boolean intSetX(double x, boolean constructor) {
        if (x < MINIMUM || x > MAXIMUM_X) {
            System.out.printf("%f is not valid X value", x);

            if (!constructor)
                System.out.printf("\n");

            return false;
        }

        this.x = x;

        return true;
    }

    /**
     * X position setter
     * 
     * @param x new X position
     * @return true if x is in the valid range, false otherwise
     */
    public boolean setX(double x) {
        return intSetX(x, false);
    }

    /**
     * Y position getter
     * 
     * @return Position on the y axis
     */
    public double getY() {
        return y;
    }

    /**
     * Internal Y postion setter
     * 
     * @param y           new Y position
     * @param constructor Is the function called from the constructor?
     * @return true if y is in the valid range, false otherwise
     */
    public boolean intSetY(double y, boolean constructor) {
        if (y < MINIMUM || y > MAXIMUM_Y) {
            System.out.printf("%f is not valid Y value", y);

            if (!constructor)
                System.out.printf("\n");

            return false;
        }

        this.y = y;

        return true;
    }

    /**
     * Y position setter
     * 
     * @param y new Y position
     * @return true if y is in the valid range, false otherwise
     */
    public boolean setY(double y) {
        return intSetY(y, false);
    }

    public String toString() {
        return String.format("(%f , %f)", x, y);
    }

    public boolean equals(Point other) {
        return other.x == x && other.y == y;
    }
}
