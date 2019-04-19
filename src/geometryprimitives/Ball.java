package geometryprimitives;

import biuoop.DrawSurface;
import complexobjects.Velocity;
import detectorsany.CollisionInfo;
import gamemechanisim.GameEnvironment;
import gamemechanisim.GameLevel;
import typeof.Sprite;

import java.awt.Color;


/**
 * the main class of the ball object.
 * the class contains members such the center, the radius, the velocity, the color
 * and 2 bounds of the wanted frame
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private Velocity velocity;
    private java.awt.Color color;
    private GameEnvironment myGame;

    /**
     * the constructor of the ball object.
     *
     * @param g      the game environment
     * @param center of the ball
     * @param r      radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment g) {
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.myGame = g;
    }

    /**
     * function that returns the  value of the center.
     *
     * @return the Point value of the center point
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * function that returns the radius of the ball.
     *
     * @return the size of the circle
     */
    public int getSize() {
        return this.r;
    }

    /**
     * function that returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draws current circle on the surface based on its center.
     *
     * @param surface a given surface
     */

    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) getCenter().getX(), (int) getCenter().getY(), getSize());
        surface.setColor(Color.gray);
        surface.fillCircle((int) getCenter().getX(), (int) getCenter().getY(), getSize() - 1);
        surface.setColor(Color.black);
        surface.drawCircle((int) getCenter().getX(), (int) getCenter().getY(), getSize());

    }

    /**
     * function that calling the moveonestep func.
     *
     * @param dt time passed since last call.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * function that sets the velocity of the ball based on the dx and dy speeds.
     *
     * @param dx the delta in the X direction
     * @param dy the delta in the Y direction
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * function that sets the velocity of the ball based on a given velocity.
     *
     * @param v the velocity of the ball as a Velocity object
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * function the returns the velocity of the ball.
     *
     * @return the speed of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * function that changes the center of the ball based on the speed.
     * it checks if the ball is inside the borders of itself.
     * if its not, it changes its speed to "-"
     *
     * @param dt time passed since last call.
     */
    public void moveOneStep(double dt) {
        double dx;
        double dy;
        Velocity fittedVelocity = this.velocity.adjust(dt);
        Line trajectory = makeTrajectory(fittedVelocity, getCenter(), getSize());
        CollisionInfo nextCollision = myGame.getClosestCollision(trajectory);
        if (nextCollision != null) {
            this.velocity = nextCollision.collisionObject().hit(this, nextCollision.collisionPoint(), this.velocity);
            dx = this.velocity.xDirection() * dt;
            dy = this.velocity.yDirection() * dt;
            this.center = new Velocity(dx, dy).applyToPoint(center);
        } else {
            dx = this.velocity.xDirection() * dt;
            dy = this.velocity.yDirection() * dt;
            this.center = new Velocity(dx, dy).applyToPoint(center);
        }
    }


    /**
     * function the make a imaginary line based on the ball direction.
     *
     * @param v      current ball velocity
     * @param c      current ball center
     * @param radius the radius of the ball
     * @return the created line
     */
    public Line makeTrajectory(Velocity v, Point c, int radius) {
        if (v.xDirection() >= 0 && v.yDirection() <= 0) {
            return new Line(new Point(getCenter().getX(), getCenter().getY()),
                    new Point(getCenter().getX() + v.xDirection() + r,
                            getCenter().getY() + v.yDirection() - radius - 1));
        } else if (v.xDirection() >= 0 && v.yDirection() >= 0) {
            return new Line(new Point(getCenter().getX(), getCenter().getY()),
                    new Point(getCenter().getX() + v.xDirection() + r,
                            getCenter().getY() + v.yDirection() + radius + 1));
        } else if (v.xDirection() <= 0 && v.yDirection() >= 0) {
            return new Line(new Point(getCenter().getX(), getCenter().getY()),
                    new Point(getCenter().getX() + v.xDirection() - r,
                            getCenter().getY() + v.yDirection() + radius + 1));
        }
        return new Line(new Point(getCenter().getX(), getCenter().getY()),
                new Point(getCenter().getX() + v.xDirection() - r,
                        getCenter().getY() + v.yDirection() - radius - 1));
    }

    /**
     * function that add's the ball to the sprites list.
     *
     * @param g the current game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * function that removes ball that reaches the death zone from the game.
     *
     * @param g current game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}