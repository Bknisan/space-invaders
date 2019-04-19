package factorisandas;
import complexobjects.Block;

/**
 * block creator interface.
 */
public interface BlockCreator {
    /**
     * @param xpos upper left x coordinate.
     * @param ypos upper left y coordinate.
     * @return new block by the specifications.
     */
    Block create(int xpos, int ypos);

}
