package gamelevels;
import biuoop.DrawSurface;
import gamemechanisim.GameLevel;
import typeof.Sprite;

import java.awt.Color;

/**
 *
 */
public class ColorBackGround implements Sprite {
    private Color back;

    /**
     * @param color wanted color;
     */
    public ColorBackGround(Color color) {
        this.back = color;
    }

    /**
     * draws the object on the surface.
     *
     * @param d current surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.back);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

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
