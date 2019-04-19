package gamemechanisim;
import detectorsany.CollisionInfo;
import geometryprimitives.Line;
import geometryprimitives.Point;
import typeof.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * the gameenvironment class.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * function that creates a new game enviroment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * function that adds a new object to the existing list.
     *
     * @param c a collidable object
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * function that removes wanted collidable from current list.
     *
     * @param c a collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * @param trajectory direction of the ball
     * @return info of the closest collision point.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> collisionPoints = new ArrayList<>(collidables.size());
        int objectIndex = 0;
        for (int i = 0; i < collidables.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle()) != null) {
                collisionPoints.add(trajectory.closestIntersectionToStartOfLine(
                        collidables.get(i).getCollisionRectangle()));
                objectIndex = i;
            }
        }
        if (collisionPoints.isEmpty()) {
            return null;
        }
        double minDistance = collisionPoints.get(0).distance(trajectory.start());
        for (int i = objectIndex; i < collisionPoints.size(); i++) {
            if (collisionPoints.get(i).distance(trajectory.start()) <= minDistance) {
                objectIndex = i;
                minDistance = collisionPoints.get(i).distance(trajectory.start());
            }
        }
        return new CollisionInfo(collidables.get(objectIndex), trajectory);
    }

    /**
     * @param center current center of the ball.
     * @return true or false if the ball is inside a block or not.
     */
    public boolean isInsideRect(Point center) {
        for (Collidable collidable : this.collidables) {
            if (center.getX() >= collidable.getCollisionRectangle().getUpperLeft().getX()
                    && center.getX() <= collidable.getCollisionRectangle().getLowerRight().getX()
                    && center.getY() >= collidable.getCollisionRectangle().getUpperLeft().getY()
                    && center.getX() <= collidable.getCollisionRectangle().getLowerRight().getY()) {
                return true;
            }
        }
        return false;
    }
}
