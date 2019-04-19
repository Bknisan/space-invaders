package gamelevels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamemechanisim.Animation;

/**
 *
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * @param k the game key board sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * @param dt time passed since last call.
     * @param d  the game surface.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {

            this.stop = true;
        }
    }

    /**
     * @return yes or no if the pause screen need to disappear.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
