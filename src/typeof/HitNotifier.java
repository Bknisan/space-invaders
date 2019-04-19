package typeof;

/**
 * Listener pattern.
 */
public interface HitNotifier {
    /**
     * @param hl new listener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * @param hl existed listener to remove.
     */
    void removeHitListener(HitListener hl);
}
