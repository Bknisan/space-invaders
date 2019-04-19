package detectorsany;

import complexobjects.Block;
import complexobjects.Counter;
import gamemechanisim.GameLevel;
import geometryprimitives.Ball;
import typeof.HitListener;

/**
 * BallRemover main class.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter numOfBalls;

    /**
     * class constructor.
     *
     * @param g current game.
     */
    public BallRemover(GameLevel g) {
        game = g;
        numOfBalls = new Counter();
    }

    /**
     * @param beingHit the clashed block.
     * @param hitter   the ball the collided into the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
            hitter.removeFromGame(game);
            numOfBalls.decrease(1);
    }

    /**
     *
     */
    public void increaseMyBalls() {
        numOfBalls.increase(1);
    }

    /**
     * @return number of balls currently in the game.
     */
    public int getnumOfBalls() {
        return numOfBalls.getValue();
    }
}
