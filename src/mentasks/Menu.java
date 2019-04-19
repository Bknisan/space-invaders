package mentasks;
import gamemechanisim.Animation;

/**
 * @param <T>
 */
public interface Menu<T> extends Animation {
    /**
     * @param key       stop when this key is pressed.
     * @param message   the message yo print.
     * @param returnVal the value to return after the stop key is pressed.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return current status of the game.
     */
    T getStatus();

    /**
     * @param key     stop when this key is pressed.
     * @param message the message yo print.
     * @param subMenu wanted seb menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     *
     */
    void beginAgain();

}
