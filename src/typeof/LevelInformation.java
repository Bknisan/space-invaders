package typeof;

import complexobjects.Block;
import complexobjects.Velocity;

import java.util.List;

/**
 *
 */
public interface LevelInformation {
    /**
     * @return number of balls in the level.
     */
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * @return list of the ball velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * @return the paddle width.
     */
    int paddleWidth();
    // the level name will be displayed at the top of the screen.

    /**
     * @return the level name.
     */
    String levelName();
    // Returns a sprite with the background of the level

    /**
     * @return the level background.
     */
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.

    /**
     * @return list of blocks in the level.
     */
    List<Block> blocks();
    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    /**
     * @return number of blocks in the level.
     */
    int numberOfBlocksToRemove();
}
