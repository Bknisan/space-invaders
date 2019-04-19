package factorisandas;

import complexobjects.Block;

/**
 * simple block creator class.
 */
public class SimpleBlockCreator implements BlockCreator {

    /**
     * @param xpos upper left x coordinate.
     * @param ypos upper left y coordinate.
     * @return new block by the specifications.
     */
    @Override
    public Block create(int xpos, int ypos) {

        return new Block(xpos, ypos);
    }

}
