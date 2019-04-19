package typeof;

import complexobjects.Velocity;
import geometryprimitives.Ball;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

/**
 * the collidable interface.
 */
public interface Collidable {
    /**
     * @return the shape of the collided rect.
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint  the collision point
     * @param currentVelocity the ball velocity
     * @param hitter          the object that clashed into collidable.
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
