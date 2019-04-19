package gamelevels;

import biuoop.DrawSurface;
import gamemechanisim.GameLevel;
import typeof.Sprite;

import java.awt.Image;

/**
 *
 */
public class ImageBack implements Sprite {
    private Image photo;

    /**
     * @param image wanted image;
     */
    public ImageBack(Image image) {
        this.photo = image;
    }

    /**
     * draws the object on the surface.
     *
     * @param d current surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.photo);
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
