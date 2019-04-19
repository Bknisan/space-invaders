package factorisandas;

import complexobjects.Block;

/**
 *
 */
public class BlockDecoretor implements BlockCreator {
    /**
     * @param original current creator.
     * @param key      key in the hash map.
     * @param val      value in the hash map.
     * @return new block creator.
     */
    public static BlockCreator addNewSet(BlockCreator original, String key, String val) {
        if (key.equals("height")) {
            return new ChangeBlockHeight(original, val);
        } else if (key.equals("width")) {
            return new ChangeBlockWidth(original, val);
        } else if (key.equals("hit_points")) {
            return new ChangeHitPoint(original, val);
        } else if (key.startsWith("fill") || key.equals("stroke")) {
            Integer hitPointsValue = null;
            boolean isFill = key.startsWith("fill");

            int dividerIndex = key.indexOf("-");
            if (dividerIndex != -1) {
                hitPointsValue = Integer.parseInt(key.substring(dividerIndex + 1));
            }
            return new ChangeBlockColors(original, val, isFill, hitPointsValue);
        }
        throw new RuntimeException("not supported value");
    }

    /**
     * @param xpos upper left x coordinate.
     * @param ypos upper left y coordinate.
     * @return new block by the specifications.
     */
    @Override
    public Block create(int xpos, int ypos) {
        return null;
    }
}
