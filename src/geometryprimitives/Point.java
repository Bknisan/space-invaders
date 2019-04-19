package geometryprimitives;
/**
 * the main class of the object point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor of the object point.
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * function that returns the distance between 2 points.
     *
     * @param other point with x and y values
     * @return the distance between the 2 points
     */
    public double distance(Point other) {
        double c = ((this.x - other.x) * (this.x - other.x)
                + (this.y - other.y) * (this.y - other.y));
        c = Math.sqrt(c);
        return c;
    }

    /**
     * function the returns true or false if it is the same point.
     *
     * @param other point with x and y values
     * @return true if it is the same point and false otherwise
     */
    public boolean equals(Point other) {
        return ((int) this.x == (int) other.x) && ((int) this.y == (int) other.y);
    }

    /**
     * function that returns the x value of the point.
     *
     * @return the x value of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * function that returns the y value of the point.
     *
     * @return the y value of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * function that changes the current position of the point.
     *
     * @param newX new X position
     * @param newY new Y position
     */
    public void setPosition(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }
}