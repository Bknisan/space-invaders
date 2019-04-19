package typeof;

import biuoop.DrawSurface;
import gamemechanisim.GameLevel;

/**
 * the sprite interface.
 */
public interface Sprite {
    // sprite interface methods.

    /**
     * draws the object on the surface.
     *
     * @param d current surface
     */
    void drawOn(DrawSurface d);

    /**
     * @param dt how long since last call.
     */
    void timePassed(double dt);

    /**
     * adding the object to the game.
     *
     * @param g the current game
     */
    void addToGame(GameLevel g);
}
