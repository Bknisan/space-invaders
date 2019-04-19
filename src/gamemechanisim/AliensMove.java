package gamemechanisim;

import complexobjects.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.util.List;

/**
 * aliens move class.
 */
public class AliensMove {
    private boolean rightOrLeft;
    private int calls;
    private int whenToMove;
    private int coeficient;
    private int maxCoeficient;

    /**
     * class constructor.
     */
    public AliensMove() {
        this.rightOrLeft = true;
        this.calls = 0;
        this.coeficient = 8;
        this.maxCoeficient = 70;
        this.whenToMove = 15;


    }

    /**
     * @param existedAliens list of existed block.
     */
    public void moveThem(List<Block> existedAliens) {
        this.calls++;
        if (calls == whenToMove && (getRightestEdge(existedAliens) < 800) && rightOrLeft) {
            for (int i = 0; i < existedAliens.size(); i++) {
                Point upperNew = new Point(existedAliens.get(i).getCollisionRectangle().getUpperLeft().getX()
                        + coeficient, existedAliens.get(i).getCollisionRectangle().getUpperLeft().getY());
                existedAliens.get(i).setRect(new Rectangle(upperNew,
                        existedAliens.get(i).getCollisionRectangle().getWidth(),
                        existedAliens.get(i).getCollisionRectangle().getHeight()));
            }
            calls = 0;
        }
        if (calls == whenToMove && (getLeftestEdge(existedAliens) <= 800) && !rightOrLeft) {
            for (int i = 0; i < existedAliens.size(); i++) {
                Point upperNew = new Point(existedAliens.get(i).getCollisionRectangle().getUpperLeft().getX()
                        - coeficient, existedAliens.get(i).getCollisionRectangle().getUpperLeft().getY());
                existedAliens.get(i).setRect(new Rectangle(upperNew,
                        existedAliens.get(i).getCollisionRectangle().getWidth(),
                        existedAliens.get(i).getCollisionRectangle().getHeight()));
            }
            calls = 0;
        } else if ((getRightestEdge(existedAliens) >= 800) || (getLeftestEdge(existedAliens) <= 0)) {
            this.rightOrLeft = !this.rightOrLeft;
            if (this.coeficient < this.maxCoeficient) {
                this.coeficient = this.coeficient + 5;
            }
            for (int i = 0; i < existedAliens.size(); i++) {
                Point upperNew = new Point(existedAliens.get(i).getCollisionRectangle().getUpperLeft().getX(),
                        existedAliens.get(i).getCollisionRectangle().getUpperLeft().getY() + 30);
                existedAliens.get(i).setRect(new Rectangle(upperNew,
                        existedAliens.get(i).getCollisionRectangle().getWidth(),
                        existedAliens.get(i).getCollisionRectangle().getHeight()));
            }
            calls = whenToMove - 1;
        }
    }

    /**
     * @param aliens existed enemies.
     * @return rightest alien x coordinate.
     */
    public int getRightestEdge(List<Block> aliens) {
        int first = 0;
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).getCollisionRectangle().getUpperRight().getX() > first) {
                first = (int) aliens.get(i).getCollisionRectangle().getUpperRight().getX();
            }
        }
        return first;
    }

    /**
     * @param aliens existed enemies.
     * @return leftest x coordinate.
     */
    public int getLeftestEdge(List<Block> aliens) {
        int first = 800;
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).getCollisionRectangle().getUpperLeft().getX() < first) {
                first = (int) aliens.get(i).getCollisionRectangle().getUpperLeft().getX();
            }
        }
        return first;
    }

    /**
     * @param aliens existed enemies.
     * @return lowest y coordinate.
     */
    public int getLowetEdge(List<Block> aliens) {
        int first = 0;
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).getCollisionRectangle().getLowerRight().getY() > first) {
                first = (int) aliens.get(i).getCollisionRectangle().getLowerLeft().getY();
            }
        }
        return first;
    }

    /**
     * upgrading current level speed and moving speed.
     */
    public void setCoeficient() {
        if (this.whenToMove - 3 > 0) {
            this.whenToMove = this.whenToMove - 3;
        }
        this.coeficient = this.coeficient + 5;
    }

}