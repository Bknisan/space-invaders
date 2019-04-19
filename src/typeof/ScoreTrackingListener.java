package typeof;

import complexobjects.Block;
import complexobjects.Counter;
import geometryprimitives.Ball;

/**
 *
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * @param beingHit the clashed block.
     * @param hitter   the ball the collided into the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(100);
    }

    /**
     *
     */
    public void endOfLevel() {
        currentScore.increase(200);
    }

    /**
     * class constructor.
     *
     * @param scoreCounter the score of the player.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * @return current score.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}
