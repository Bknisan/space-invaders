package complexobjects;

import geometryprimitives.Point;

/**
 * the class of a velocity that contain member of dx and dy direction.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor of the object velocity.
     *
     * @param dx the moving distance of the x value
     * @param dy the moving distance of the y value
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * function that returns the dx of the velocity.
     *
     * @return the x value of the velocity direction
     */
    public double xDirection() {
        return this.dx;
    }

    /**
     * function that returns the dy of the velocity.
     *
     * @return the y value of the velocity direction
     */
    public double yDirection() {
        return this.dy;
    }

    /**
     * function that returns the new position of the point.
     *
     * @param p a generic point
     * @return new position of the ball center
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * function that calculetes the velocity based on a given angle and speed.
     *
     * @param angle the angle the ball released at
     * @param speed the speed of the ball
     * @return the new velocity calculeted in the x and the y directions
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = angle - 90;
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * function the calculets the general speed of the ball.
     *
     * @return the speed as a double
     */
    public double getSpeed() {
        double mySpeed = ((this.xDirection()) * (this.xDirection())) + ((this.yDirection()) * (this.yDirection()));
        mySpeed = Math.sqrt(mySpeed);
        return mySpeed;
    }

    /**
     * @param dt time passed since last call.
     * @return new adjusted velocity.
     */
    public Velocity adjust(double dt) {
        return new Velocity(this.xDirection() * dt, this.yDirection() * dt);
    }
}