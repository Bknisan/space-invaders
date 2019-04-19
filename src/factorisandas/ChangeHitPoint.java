package factorisandas;

import complexobjects.Block;

/**
 *
 */
public class ChangeHitPoint extends BlockCreatorAdjustment {
    private int hits;

    /**
     * @param existed current block creator.
     * @param val     wanted new number of hits.
     */
    public ChangeHitPoint(BlockCreator existed, String val) {
        super(existed);
        this.hits = Integer.parseInt(val);
    }

    /**
     * @param xpos upper left x coordinate.
     * @param ypos upper left y coordinate.
     * @return new block by the specifications.
     */
    @Override
    public Block create(int xpos, int ypos) {
        Block newBlock = super.create(xpos, ypos);
        newBlock.setHits(this.hits);
        return newBlock;
    }
}
