package gamelevels;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamemechanisim.Animation;

/**
 *
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor gameSensor;
    private String stopKey;
    private Animation whatScreen;
    private boolean stop;
    private boolean isAlreadyPressed;
    /**
     * @param sensor key board sensor.
     * @param key stop key.
     * @param animation animation to implement.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.gameSensor = sensor;
        this.stopKey = key;
        this.whatScreen = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    /**
     * @param d  the game surface.
     * @param dt how long since last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.whatScreen.doOneFrame(d, dt);
        if (this.gameSensor.isPressed(stopKey) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.gameSensor.isPressed(stopKey)) {
            this.isAlreadyPressed = false;
        }
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
       this.isAlreadyPressed = true;
    }
}
