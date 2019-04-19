package detectorsany;

import complexobjects.Block;
import complexobjects.Counter;
import gamemechanisim.GameLevel;
import geometryprimitives.Ball;
import typeof.HitListener;
import typeof.ScoreTrackingListener;

/**
 *
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    private ScoreTrackingListener myListener;

    /**
     * class constructor.
     *
     * @param game          current game.
     * @param removedBlocks number of blocks that already removed.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
        this.myListener = new ScoreTrackingListener(new Counter());
    }

    /**
     * @param beingHit the clashed block.
     * @param hitter   the ball the collided into the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCollisionRectangle().getWidth() == 100) {
            this.getRemainingBlocks().decrease(this.remainingBlocks.getValue());
        } else if (getRemainingBlocks().getValue() > 1 && hitter.getSize() == 2) {
            if (beingHit.getCollisionRectangle().getWidth() > 10) {
                this.getRemainingBlocks().decrease(1);
                myListener.hitEvent(beingHit, hitter);
            }
            beingHit.removeFromGame(getGame());
        } else if (beingHit.getCollisionRectangle().getWidth() == 4) {
            beingHit.removeFromGame(getGame());
        } else if (getRemainingBlocks().getValue() == 1) {
            if (beingHit.getCollisionRectangle().getWidth() > 10) {
                this.getRemainingBlocks().decrease(1);
                myListener.endOfLevel();
            }
            beingHit.removeFromGame(getGame());
            ;
        } else if (beingHit.getCollisionRectangle().getWidth() == 40 && hitter.getSize() == 4) {
            hitter.removeFromGame(getGame());
        }
    }

    /**
     * @return the game of  block remover working on.
     */
    public GameLevel getGame() {
        return game;
    }

    /**
     * @return blocks remaining in the game.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * @return current game score.
     */
    public Counter whatIsTheScore() {
        return myListener.getCurrentScore();
    }

    /**
     *
     */
    public void setRemainingBlocks() {
        this.remainingBlocks.increase(50);
    }
}
