package gamemechanisim;

import complexobjects.Block;
import geometryprimitives.Ball;
import geometryprimitives.Point;

import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * alien shot main class.
 */
public class AliensShot {
    /**
     * @param existedAliens list of existing blocks
     * @param myEn          my game environment.
     * @return new ball representing the shot.
     */
    public Ball makeShout(List<Block> existedAliens, GameEnvironment myEn) {
        Random rand = new Random();
        int whichAlien = rand.nextInt(existedAliens.size());
        Block thisOne = existedAliens.get(whichAlien);
        for (int i = 0; i < existedAliens.size(); i++) {
            if (existedAliens.get(i).getCollisionRectangle().getUpperLeft().getX()
                    == thisOne.getCollisionRectangle().getUpperLeft().getX()
                    && existedAliens.get(i).getCollisionRectangle().getUpperLeft().getY()
                    > thisOne.getCollisionRectangle().getUpperLeft().getY()) {
                thisOne = existedAliens.get(i);
            }
        }
        return new Ball(new Point(thisOne.getCollisionRectangle().getLowerLeft().getX()
                + (thisOne.getCollisionRectangle().getWidth() / 2),
                thisOne.getCollisionRectangle().getLowerRight().getY() + 5), 4, Color.RED, myEn);
    }
}