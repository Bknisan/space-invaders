package typeof;

import biuoop.DrawSurface;
import complexobjects.Block;
import gamelevels.Invaders;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * the spritecollection class.
 */
public class SpriteCollection {
    private List<Sprite> mySprites;

    /**
     * constructor.
     */
    public SpriteCollection() {
        mySprites = new ArrayList<>();
    }

    /**
     * @param s new sprite expected to be added.
     */
    public void addSprite(Sprite s) {
        this.mySprites.add(s);
    }

    /**
     * @param s some sprite we want to remove.
     */
    public void removeSprite(Sprite s) {
        this.mySprites.remove(s);
    }

    /**
     * function that make the ball and the paddle move.
     *
     * @param dt time passed since last call.
     */
    public void notifyAllTimePassed(double dt) {
        for (Sprite mySprite : mySprites) {
            mySprite.timePassed(dt);
        }

    }

    /**
     * function that draws all the sprites on the surface.
     *
     * @param d my surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite mySprite : mySprites) {
            mySprite.drawOn(d);
        }

    }

    /**
     * @return list of my sprites.
     */
    public List<Sprite> getMySprites() {
        return mySprites;
    }

    /**
     * @param modified modified sprite list after a deletion.
     */
    public void setMySprites(List<Sprite> modified) {
        mySprites = modified;
    }

    /**
     * @return return list of blocks in the sprite collection.
     */
    public List<Block> getMyBlocks() {
        List<Block> myList = new ArrayList<>();
        for (int i = 0; i < getMySprites().size(); i++) {
            if (getMySprites().get(i) instanceof Block) {
                if (((Block) getMySprites().get(i)).getCollisionRectangle().getWidth() == 40
                        && ((Block) getMySprites().get(i)).getCollisionRectangle().getHeight() == 30) {
                    myList.add((Block) getMySprites().get(i));
                }
            }
        }
        return myList;
    }

    /**
     * @param current invaders level.
     */
    public void setPositions(Invaders current) {
        List<Block> whatLeft = getMyBlocks();
        Map<Block, Point> firstOnes = current.getPrimePositions();
        for (int i = 0; i < whatLeft.size(); i++) {
            if (firstOnes.containsKey(whatLeft.get(i))) {
                whatLeft.get(i).setRect(new Rectangle(firstOnes.get(whatLeft.get(i)), 40, 30));
            }
        }


    }

}

