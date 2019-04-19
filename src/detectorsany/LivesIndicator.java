package detectorsany;

import biuoop.DrawSurface;
import complexobjects.Counter;
import gamemechanisim.GameLevel;
import typeof.Sprite;

import java.awt.Color;


/**
 *
 */
public class LivesIndicator implements Sprite {
    private Counter numOfLives;

    /**
     * constructor.
     *
     * @param lives wanted num of lives.
     */
    public LivesIndicator(int lives) {
        numOfLives = new Counter();
        numOfLives.increase(lives);
    }

    /**
     * draws the object on the surface.
     *
     * @param d current surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(100, 12, "Lives: " + Integer.toString(numOfLives.getValue()), 13);
    }

    /**
     * calling the relevant functions of the block,ball and paddle.
     *
     * @param dt time passed since last call.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * adding the object to the game.
     *
     * @param g the current game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * @return number of remaining lives.
     */
    public Counter getNumOfLives() {
        return this.numOfLives;
    }
}
