package factorisandas;
import complexobjects.Block;


/**
 *
 */
public class ChangeBlockHeight extends BlockCreatorAdjustment {
    private int height;

    /**
     *
     * @param existed current creator.
     * @param val string val.
     */
    public ChangeBlockHeight(BlockCreator existed, String val) {
        super(existed);
        this.height = Integer.parseInt(val);

    }
    /**
     * @param xpos upper left x coordinate.
     * @param ypos upper left y coordinate.
     * @return new block by the specifications.
     */
    @Override
    public Block create(int xpos, int ypos) {
        Block newBlock = super.create(xpos, ypos);
        newBlock.setHeight(this.height);
        return newBlock;
    }
}
