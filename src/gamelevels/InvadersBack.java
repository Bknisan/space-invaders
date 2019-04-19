package gamelevels;

import biuoop.DrawSurface;
import gamemechanisim.GameLevel;
import typeof.Sprite;

import java.awt.Color;

/**
 *
 */
public class InvadersBack implements Sprite {
    /**
     * draws the object on the surface.
     *
     * @param d current surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
    }

    /**
     * @param dt how long since last call.
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

    }
}
