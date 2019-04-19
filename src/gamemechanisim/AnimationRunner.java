package gamemechanisim;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * animation main class.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * constructor.
     *
     * @param wanted the game gui.
     * @param frames velocity of imaging.
     */
    public AnimationRunner(GUI wanted, int frames) {
        this.gui = wanted;
        this.framesPerSecond = frames;
    }

    /**
     * @param animation currently performed animation.
     */
    public void run(Animation animation) {
        double dt = 0.01666666666;
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, dt);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * @return the game gui.
     */
    public GUI getGui() {
        return this.gui;
    }
}
