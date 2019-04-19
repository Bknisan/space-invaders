package gamelevels;

import biuoop.DrawSurface;
import gamemechanisim.Animation;
import typeof.SpriteCollection;

import java.awt.Color;

/**
 *
 */
public class CountDownAnimation implements Animation {
    private double howLong;
    private int fromWhere;
    private SpriteCollection firstScreen;
    private String levelName;

    /**
     * @param numOfSeconds how many seconds should i count.
     * @param countFrom    from what number should i count.
     * @param gameScreen   current sprites on the game.
     * @param myLevel      the name of current level.
     */
    public CountDownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, String myLevel) {
        this.howLong = numOfSeconds * 1000;
        this.fromWhere = countFrom;
        this.firstScreen = gameScreen;
        this.levelName = myLevel;
    }

    /**
     * @param d  my surface.
     * @param dt time passed since last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        int startingFont = 50;
        this.firstScreen.drawAllOn(d);
        d.setColor(Color.black);
        d.drawText(500, 12, "Level Name: " + this.levelName, 13);
        d.setColor(Color.PINK);
        if (howLong <= 1000) {
            d.drawText(370, 300, "GO!", startingFont + 50);
        } else {
            d.drawText(400, 300, Integer.toString(this.fromWhere), startingFont);
        }
        setHowLong();
        if (this.howLong == 3000 || this.howLong == 2000 || this.howLong == 1000) {
            setFromWhere();
        }
    }

    /**
     * first digit setter.
     */
    public void setFromWhere() {
        this.fromWhere = this.fromWhere - 1;
    }

    /**
     * how many seconds to count setter.
     */
    public void setHowLong() {
        this.howLong = this.howLong - 25;
    }

    /**
     * @return true or if the count down should stop.
     */
    @Override
    public boolean shouldStop() {
        if (this.howLong >= 1) {
            return false;
        }
        return true;
    }
}
