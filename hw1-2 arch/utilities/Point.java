package utilities;

/**
 * Point coordinate class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public abstract class Point implements Utilities {
    /**
     * Minimal X/Y value
     */
    public static final double minVal = 0;
    /**
     * Maximal X value
     */
    public static final double maxX = 800;
    /**
     * Maximal Y value
     */
    public static final double maxY = 600;

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
        if (!intSetX(x, true)) {
            this.x = getRandomDouble(minVal, maxX);
            correctingMessage(x, this.x, "X");
        }
        if (!intSetY(y, true)) {
            this.y = getRandomDouble(minVal, maxY);
            correctingMessage(x, this.x, "Y");
        }

        //successMessage(String.format("Point (%f , %f) ", this.x, this.y));
    }

    /**
     * Default constructor
     */
    public Point() {
        x = getRandomDouble(minVal, maxX);
        y = getRandomDouble(minVal, maxY);
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
        if (!checkValue(x, minVal, maxX)) {
            if (!constructor)
                errorMessage(x, "X");

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
        if (!checkValue(y, minVal, maxY)) {
            if (!constructor)
                errorMessage(y, "Y");

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

    /**
     * toString method
     */
    @Override
    public String toString() {
        return String.format("(%f , %f)", x, y);
    }

    /**
     * equals method
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Point)
            return ((Point)other).x == x && ((Point)other).y == y;
        return false;
    }
    /**
     * Returns the distance between 2 points
     * @param other point to the other side of the line
     * @return hypertenuse length
     */
    public double calcDistace(Point other) {
        double dx = x - other.x;
        double dy = y - other.y;

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}
