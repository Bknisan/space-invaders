package detectorsany;

import biuoop.DrawSurface;
import complexobjects.Counter;
import gamemechanisim.GameLevel;
import typeof.Sprite;

import java.awt.Color;

/**
 *
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * constructor.
     *
     * @param myScore wanted initial score.
     */
    public ScoreIndicator(Counter myScore) {
        scoreCounter = myScore;
    }

    /**
     * draws the object on the surface.
     *
     * @param d current surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(350, 12, "Score: " + Integer.toString(scoreCounter.getValue()), 13);
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
     * @return score of the user.
     */
    public Counter getScoreCounter() {
        return scoreCounter;
    }

    /**
     * @param updatedScore new score of the user.
     */
    public void setScoreCounter(Counter updatedScore) {
        this.scoreCounter = updatedScore;
    }
}
