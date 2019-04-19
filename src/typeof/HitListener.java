package typeof;

import complexobjects.Block;
import geometryprimitives.Ball;

/**
 *
 */
public interface HitListener {
    /**
     * @param beingHit the clashed block.
     * @param hitter   the ball the collided into the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
