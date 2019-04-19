package complexobjects;

import biuoop.DrawSurface;
import gamemechanisim.GameLevel;
import geometryprimitives.Ball;
import geometryprimitives.Line;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import typeof.Collidable;
import typeof.HitListener;
import typeof.HitNotifier;
import typeof.Sprite;

import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private Designer frameColor;
    private Designer fillColor;
    private Map<Integer, Designer> frameDesigners;
    private Map<Integer, Designer> fillDesigners;
    private Line upperHorizontal;
    private Line lowerHorizontal;
    private Line leftVertical;
    private Line rightVertical;
    private int hits;
    private List<HitListener> hitListeners;


    /**
     * block constructor.
     *
     * @param lifes       the lifes of the block
     * @param freshBlock  the expected size of thr block
     * @param frameColor  frame work color of the block
     * @param myFillColor the color of the block
     */
    public Block(Rectangle freshBlock, Color myFillColor, Color frameColor, int lifes) {
        this.hitListeners = new ArrayList<>();
        this.hits = lifes;
        this.block = freshBlock;
        this.frameColor = new FrameColor(frameColor);
        this.fillColor = new FillColor(myFillColor);
        this.fillDesigners = new HashMap<>();
        this.frameDesigners = new HashMap<>();
        this.leftVertical = new Line(freshBlock.getUpperLeft(), freshBlock.getLowerLeft());
        this.rightVertical = new Line(freshBlock.getUpperRight(), freshBlock.getLowerRight());
        this.upperHorizontal = new Line(freshBlock.getUpperLeft(), freshBlock.getUpperRight());
        this.lowerHorizontal = new Line(freshBlock.getLowerLeft(), freshBlock.getLowerRight());
    }

    /**
     * @param xpos upper left x.
     * @param ypos upper left y.
     */
    public Block(int xpos, int ypos) {
        this.block = new Rectangle(new Point(xpos, ypos), 1.0D, 1.0D);
        this.hits = 1;
        this.fillDesigners = new HashMap<>();
        this.frameDesigners = new HashMap<>();
        this.hitListeners = new ArrayList<>();
        this.fillColor = new EmptyColor();
        this.frameColor = new EmptyColor();

    }

    /**
     * @return the shape of the block as a rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * function that calcultes a new velocity of the game ball depending on the collision point.
     *
     * @param collisionPoint  the collision points of the ball with a block.
     * @param currentVelocity the current velocity of the ball.
     * @param hitter          the ball that hitted some block.
     * @return the new velocity of the ball after the impact
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.leftVertical.start().getX() != 0 && this.block.getUpperLeft().getY() != 585) {
            if (this.hits > 0) {
                this.hits--;
            }
        }
        if ((leftVertical.isThePointThere(collisionPoint)) && (lowerHorizontal.isThePointThere(collisionPoint))) {
            this.notifyHit(hitter);
            return new Velocity(-Math.abs(currentVelocity.xDirection()), Math.abs(currentVelocity.yDirection()));
        }
        if ((leftVertical.isThePointThere(collisionPoint)) && (upperHorizontal.isThePointThere(collisionPoint))) {
            this.notifyHit(hitter);
            return new Velocity(-Math.abs(currentVelocity.xDirection()), -Math.abs(currentVelocity.yDirection()));
        }
        if ((rightVertical.isThePointThere(collisionPoint)) && (lowerHorizontal.isThePointThere(collisionPoint))) {
            this.notifyHit(hitter);
            return new Velocity(Math.abs(currentVelocity.xDirection()), Math.abs(currentVelocity.yDirection()));
        }
        if ((rightVertical.isThePointThere(collisionPoint)) && (upperHorizontal.isThePointThere(collisionPoint))) {
            this.notifyHit(hitter);
            return new Velocity(Math.abs(currentVelocity.xDirection()), -Math.abs(currentVelocity.yDirection()));
        }
        if ((leftVertical.isThePointThere(collisionPoint)) || (rightVertical.isThePointThere(collisionPoint))) {
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.xDirection(), currentVelocity.yDirection());
        } else {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.xDirection(), -currentVelocity.yDirection());
        }
    }

    /**
     * @param hitsMy number of lives.
     * @param newOne new designer.
     */
    public void frameDesignerToAdd(int hitsMy, Designer newOne) {
        this.frameDesigners.put(hitsMy, newOne);
    }

    /**
     * @param hitsMy number of lives.
     * @param newOne new designer.
     */
    public void fillDesignerToAdd(int hitsMy, Designer newOne) {
        this.fillDesigners.put(hitsMy, newOne);
    }

    /**
     * functions that draws the block on the surface.
     *
     * @param surface game surface.
     */
    public void drawOn(DrawSurface surface) {
        if (this.fillDesigners.containsKey(this.hits)) {
            this.fillDesigners.get(this.hits).drawIt(surface, this);
        } else {
            this.fillColor.drawIt(surface, this);
        }
        if (this.frameDesigners.containsKey(hits)) {
            (this.frameDesigners.get(this.hits)).drawIt(surface, this);
        } else {
            this.frameColor.drawIt(surface, this);
        }
    }

    /**
     * currentlly doing nothing.
     *
     * @param dt time passed since last call.
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * function that add's the block to the sprites list and to the colidables list.
     *
     * @param g current game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * function that removes existing block from wanted game.
     *
     * @param game the game from we want to remove the block.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * hit listeners list getter.
     *
     * @return list of hit listeners to the block
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * @param hl listener that we want add to the list.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * @param hl listener that we want to remove from the list.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);

    }

    /**
     * function that notifies all the listeners about the hit.
     *
     * @param hitter the ball that clashed into some block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * lifes getter.
     *
     * @return number of block lifes.
     */
    public int getHits() {
        return hits;
    }

    /**
     * lifes setter.
     *
     * @param newLifes new lifes of the block.
     */
    public void setHits(int newLifes) {
        hits = newLifes;
    }

    /**
     * @return the frame color.
     */
    public Designer getFrameColor() {
        return frameColor;
    }

    /**
     * @param frameColorMy the wanted frame color.
     */
    public void setFrameColor(Designer frameColorMy) {
        this.frameColor = frameColorMy;
    }

    /**
     * @return the fill color.
     */
    public Designer getFillColor() {
        return fillColor;
    }

    /**
     * @param fillColorMy the wanted fill color.
     */
    public void setFillColor(Designer fillColorMy) {
        this.fillColor = fillColorMy;
    }

    /**
     *
     */
    public abstract interface Designer {
        /**
         * @param d          given surface.
         * @param whatToDraw given block.
         */
        void drawIt(DrawSurface d, Block whatToDraw);
    }

    /**
     *
     */
    public static class FrameColor implements Designer {
        private Color frameColor;

        /**
         * @param color wanted frame color.
         */
        public FrameColor(Color color) {
            this.frameColor = color;
        }

        /**
         * @param d          given surface.
         * @param whatToDraw given block.
         */
        @Override
        public void drawIt(DrawSurface d, Block whatToDraw) {
            d.setColor(this.frameColor);
            d.drawRectangle((int) whatToDraw.block.getUpperLeft().getX(),
                    (int) whatToDraw.block.getUpperLeft().getY(), (int) whatToDraw.block.getWidth(),
                    (int) whatToDraw.block.getHeight());
        }
    }

    /**
     * inside fill color class.
     */
    public static class FillColor implements Designer {
        private Color fillColor;

        /**
         * @param color wanted fill color.
         */
        public FillColor(Color color) {
            this.fillColor = color;
        }

        /**
         * @param d          given surface.
         * @param whatToDraw given block.
         */
        @Override
        public void drawIt(DrawSurface d, Block whatToDraw) {
            d.setColor(this.fillColor);
            d.fillRectangle((int) whatToDraw.block.getUpperLeft().getX(),
                    (int) whatToDraw.block.getUpperLeft().getY(), (int) whatToDraw.block.getWidth(),
                    (int) whatToDraw.block.getHeight());
        }
    }

    /**
     * static class.
     */
    private static class EmptyColor
            implements Designer {
        /**
         * @param d          given surface.
         * @param whatToDraw given block.
         */
        @Override
        public void drawIt(DrawSurface d, Block whatToDraw) {

        }
    }

    /**
     * static class.
     */
    public static class ImageDrawer
            implements Designer {
        private Image photo;

        /**
         * @param photoToDraw wanted photo.
         */
        public ImageDrawer(Image photoToDraw) {
            this.photo = photoToDraw;
        }

        /**
         * @param d          given surface.
         * @param whatToDraw given block.
         */
        public void drawIt(DrawSurface d, Block whatToDraw) {
            d.drawImage((int) whatToDraw.block.getUpperLeft().getX(),
                    (int) whatToDraw.block.getUpperLeft().getY(), this.photo);
        }
    }

    /**
     * @param height new block height.
     */
    public void setHeight(int height) {
        this.block = new Rectangle(this.block.getUpperLeft(), this.block.getWidth(), height);
        this.lowerHorizontal = new Line(block.getLowerLeft(), block.getLowerRight());
        this.leftVertical = new Line(block.getUpperLeft(), block.getLowerLeft());
        this.rightVertical = new Line(block.getUpperRight(), block.getLowerRight());

    }

    /**
     * @param width new block width
     */
    public void setWidth(int width) {
        this.upperHorizontal = new Line(block.getUpperLeft(), block.getUpperRight());
        this.block = new Rectangle(this.block.getUpperLeft(), width, this.block.getHeight());
    }

    /**
     *
     * @param newOne new rectangle to set.
     */
    public void setRect(Rectangle newOne) {
        this.block = newOne;
    }
}
