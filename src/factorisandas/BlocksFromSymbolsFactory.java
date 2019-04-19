package factorisandas;

import complexobjects.Block;

import java.util.HashMap;
import java.util.Map;


/**
 * block factory class.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     *
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new HashMap<>();
        this.blockCreators = new HashMap<>();
    }
    // returns true if 's' is a valid space symbol.

    /**
     * @param s a symbol in the text file.
     * @return true or false if s is a space.
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }
    // returns true if 's' is a valid block symbol.

    /**
     * @param s a symbol in the text file.
     * @return true or false if s is block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).

    /**
     * @param s    symbol of a block.
     * @param xpos the upper left x coordinate.
     * @param ypos the upper left y coordinate.
     * @return completed block by the specifications.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    // Returns the width in pixels associated with the given spacer-symbol.

    /**
     * @param s a spacers symbol
     * @return the width of the space.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * @param s     associated string.
     * @param width wanted space.
     */
    public void addSpaceSymbol(String s, int width) {
        this.spacerWidths.put(s, width);
    }

    /**
     * @param s      associated string.
     * @param wanted wanted creator.
     */
    public void addBlockSymbol(String s, BlockCreator wanted) {
        this.blockCreators.put(s, wanted);
    }
}
