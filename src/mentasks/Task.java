package mentasks;

/**
 * @param <T>
 */
public interface Task<T> {
    /**
     * @return true or false if i should continue the menu.
     */
    Boolean run();
}
