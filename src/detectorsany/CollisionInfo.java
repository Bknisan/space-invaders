package detectorsany;

import geometryprimitives.Line;
import geometryprimitives.Point;
import typeof.Collidable;

/**
 * the collisioninfo class.
 */
public class CollisionInfo {
    private Collidable object;
    private Point collisionPoint;

    /**
     * the constructor of the class.
     *
     * @param c         the object that the line collides into
     * @param direction the direction of the ball
     */
    public CollisionInfo(Collidable c, Line direction) {
        this.object = c;
        this.collisionPoint = direction.closestIntersectionToStartOfLine(c.getCollisionRectangle());
    }

    /**
     * @return the value of the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the shape of the collidabe object.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}
