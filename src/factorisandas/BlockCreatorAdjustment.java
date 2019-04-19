package factorisandas;

import complexobjects.Block;

/**
 *
 */
public class BlockCreatorAdjustment implements BlockCreator {
    private BlockCreator myCreator;

    /**
     * @param existed current creator.
     */
    public BlockCreatorAdjustment(BlockCreator existed) {
        this.myCreator = existed;
    }

    /**
     * @param xpos upper left x coordinate.
     * @param ypos upper left y coordinate.
     * @return new block by the specifications.
     */
    @Override
    public Block create(int xpos, int ypos) {
        return this.myCreator.create(xpos, ypos);
    }
}
