package gamelevels;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamemechanisim.Animation;
import typeof.HighScoresTable;

import java.awt.Color;

/**
 *
 */
public class HighScoresAnimation implements Animation {
    private String endKeyAnimation;
    private HighScoresTable scoresTable;
    private boolean stop;
    private KeyboardSensor myBoard;
    /**
     * constructor.
     *
     * @param scores current high scores table.
     * @param endKey key to end the animation.
     * @param keyBoard game key board.
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey, KeyboardSensor keyBoard) {
        this.endKeyAnimation = endKey;
        this.scoresTable = scores;
        this.stop = false;
        this.myBoard = keyBoard;
    }

    /**
     * @param d  the game surface.
     * @param dt how long since last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.yellow);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(30, 40, "High score Records", 50);
        d.drawText(35, 80, "Name:", 30);
        d.drawText(300, 80, "Score:", 30);
        for (int i = 0; i < this.scoresTable.getHighScores().size(); i++) {
            d.drawText(35, 150 + (i * 40),
                    Integer.toString(i + 1) + ". " + scoresTable.getHighScores().get(i).getName(), 30);
            d.drawText(300, 150 + (i * 40),
                    Integer.toString(scoresTable.getHighScores().get(i).getScore()), 30);
        }
        d.drawText(300, 550, "(Press " + this.endKeyAnimation + " To Exit)", 20);
    }

    /**
     * @return true of false if the animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     *
     */
    public void reset() {
        this.stop = false;
    }
}
