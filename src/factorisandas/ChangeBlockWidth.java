package factorisandas;

import complexobjects.Block;

/**
 *
 */
public class ChangeBlockWidth extends BlockCreatorAdjustment {
    private int width;

    /**
     * @param existed current creator.
     * @param val     wanted new width.
     */
    public ChangeBlockWidth(BlockCreator existed, String val) {
        super(existed);
        this.width = Integer.parseInt(val);

    }

    /**
     * @param xpos upper left x coordinate.
     * @param ypos upper left y coordinate.
     * @return new block by the specifications.
     */
    @Override
    public Block create(int xpos, int ypos) {
        Block newBlock = super.create(xpos, ypos);
        newBlock.setWidth(this.width);
        return newBlock;
    }


}
