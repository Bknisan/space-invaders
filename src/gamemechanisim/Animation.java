package gamemechanisim;

import biuoop.DrawSurface;

/**
 * animation interface.
 */
public interface Animation {
    /**
     * @param d  the game surface.
     * @param dt how long since last call.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * @return true of false if the animation should stop.
     */
    boolean shouldStop();
}
