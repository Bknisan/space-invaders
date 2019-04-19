package gamelevels;

import biuoop.DrawSurface;
import gamemechanisim.Animation;

/**
 *
 */
public class EndScreen implements Animation {
    private String message;
    private int gameScore;
    private boolean stop;

    /**
     * @param message printted message.
     * @param score   game score.
     */
    public EndScreen(String message, int score) {
        this.message = message;
        this.gameScore = score;
        this.stop = false;
    }

    /**
     * @param dt time passed since last call.
     * @param d  game surface.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2,
                this.message + Integer.toString(this.gameScore), 32);
        d.drawText(20, 550, "(press space to exit)", 25);
    }

    /**
     * @return true or false if the game should end.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
