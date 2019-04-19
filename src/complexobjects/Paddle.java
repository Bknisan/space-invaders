package complexobjects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamemechanisim.GameEnvironment;
import gamemechanisim.GameLevel;
import geometryprimitives.Ball;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import typeof.Collidable;
import typeof.Sprite;

import java.awt.Color;

/**
 * the paddle class.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle myPaddle;
    private double mySpeed;
    private static int getUpperLeftY = 590;
    private int width;
    private Block thisOne;

    /**
     * paddle class constructor.
     *
     * @param width      wanted paddle width.
     * @param speed      wanted paddle speed.
     * @param gameSensor game keyboard sensor.
     * @param my         game environment of the game.
     * @param myLevel    game level object.
     */
    public Paddle(KeyboardSensor gameSensor, int speed, int width, GameEnvironment my, GameLevel myLevel) {
        this.mySpeed = speed;
        this.width = width;
        int getGetUpperLeftX = (780 - this.width) / 2;
        this.myPaddle = new Rectangle(new Point(getGetUpperLeftX, getUpperLeftY),
                width, 10);
        this.keyboard = gameSensor;
        thisOne = new Block(myPaddle, Color.red, Color.black, 1);
    }

    /**
     * function the moves the paddle to the left if it is still between the borders.
     *
     * @param dt time passed since last call.
     */
    public void moveLeft(double dt) {
        double adjustedSpeed = this.mySpeed * dt;
        if (this.myPaddle.getUpperLeft().getX() - adjustedSpeed > 0) {
            myPaddle.getUpperLeft().setPosition(
                    myPaddle.getUpperLeft().getX() - adjustedSpeed,
                    myPaddle.getUpperLeft().getY());
            myPaddle.getLowerLeft().setPosition(
                    myPaddle.getLowerLeft().getX() - adjustedSpeed,
                    myPaddle.getLowerLeft().getY());
            myPaddle.getUpperRight().setPosition(
                    myPaddle.getUpperRight().getX() - adjustedSpeed,
                    myPaddle.getUpperRight().getY());
            myPaddle.getLowerRight().setPosition(
                    myPaddle.getLowerRight().getX() - adjustedSpeed,
                    myPaddle.getLowerRight().getY());
            this.thisOne.setRect(myPaddle);
        } else {
            myPaddle.getUpperLeft().setPosition(0, getUpperLeftY);
            myPaddle.getLowerLeft().setPosition(0, getUpperLeftY + 10);
            myPaddle.getUpperRight().setPosition(this.width, getUpperLeftY);
            myPaddle.getLowerRight().setPosition(this.width, getUpperLeftY + 10);
            this.thisOne.setRect(myPaddle);
        }
    }

    /**
     * function the moves the paddle to the right if it is still between the borders.
     *
     * @param dt time passed since last call.
     */
    public void moveRight(double dt) {
        double adjustedSpeed = this.mySpeed * dt;
        if (this.myPaddle.getUpperRight().getX() + adjustedSpeed < 800) {
            myPaddle.getUpperLeft().setPosition(
                    myPaddle.getUpperLeft().getX() + adjustedSpeed,
                    myPaddle.getUpperLeft().getY());
            myPaddle.getLowerLeft().setPosition(
                    myPaddle.getLowerLeft().getX() + adjustedSpeed,
                    myPaddle.getLowerLeft().getY());
            myPaddle.getUpperRight().setPosition(
                    myPaddle.getUpperRight().getX() + adjustedSpeed,
                    myPaddle.getUpperRight().getY());
            myPaddle.getLowerRight().setPosition(
                    myPaddle.getLowerRight().getX() + adjustedSpeed,
                    myPaddle.getLowerRight().getY());
            this.thisOne.setRect(myPaddle);
        } else {
            myPaddle.getUpperLeft().setPosition(800 - this.width, getUpperLeftY);
            myPaddle.getLowerLeft().setPosition(800 - this.width, getUpperLeftY + 10);
            myPaddle.getUpperRight().setPosition(800, getUpperLeftY);
            myPaddle.getLowerRight().setPosition(800, getUpperLeftY + 10);
            this.thisOne.setRect(myPaddle);
        }

    }

    /**
     * function that sensetive to the keys left and right and calling the the moveleft/moveright
     * functions depending on the bottom.
     *
     * @param dt time passed since last call.
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        }
    }

    /**
     * @param d current surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle((int) this.myPaddle.getUpperLeft().getX(), (int) this.myPaddle.getUpperLeft().getY(),
                (int) this.myPaddle.getWidth(), (int) this.myPaddle.getHeight());
    }

    /**
     * @return the shape of the collided rect.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.myPaddle;
    }

    /**
     * function the changes the ball velocity depending on the part the ball hitted the paddle.
     *
     * @param collisionPoint  the collision point with the ball.
     * @param currentVelocity the current velocity of the ball.
     * @param hitter          the ball that hitted the paddle.
     * @return new velocity after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.thisOne.hit(hitter, collisionPoint, currentVelocity);
        return currentVelocity;
    }

    /**
     * function that adds the paddle object to the sprites and the collidable lists.
     *
     * @param g the game created by the game class
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * @return paddle wifth.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return paddle block object.
     */
    public Block getThisOne() {
        return this.thisOne;
    }


}