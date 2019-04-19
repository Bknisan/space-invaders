package factorisandas;

import complexobjects.Block;
import fileraeders.ColorsParser;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 */
public class ChangeBlockColors extends BlockCreatorAdjustment {
    private boolean fillOrFrame;
    private int hits;
    private Block.Designer myDesigner;

    /**
     * @param existed   current creator.
     * @param val       wanted color to change.
     * @param hitPoints wanted hit points to the color.
     * @param isItFill fill or frame.
     */
    public ChangeBlockColors(BlockCreator existed, String val, boolean isItFill, Integer hitPoints) {
        super(existed);
        this.fillOrFrame = isItFill;
        if (hitPoints == null) {
            this.hits = -1;
        } else {
            this.hits = hitPoints;
        }
        this.myDesigner = whatIsTheDesigner(val, isItFill);

    }

    /**
     * @param xpos upper left x coordinate.
     * @param ypos upper left y coordinate.
     * @return new block.
     */
    public Block create(int xpos, int ypos) {
        Block newBlock = super.create(xpos, ypos);
        if (this.fillOrFrame) {
            if (this.hits == -1) {
                newBlock.setFillColor(this.myDesigner);
            } else {
                newBlock.fillDesignerToAdd(this.hits, this.myDesigner);
            }
        } else if (this.hits == -1) {
            newBlock.setFrameColor(this.myDesigner);
        } else {
            newBlock.frameDesignerToAdd(this.hits, this.myDesigner);
        }
        return newBlock;
    }

    /**
     * @param val      color value.
     * @param isItFill fill frame or image.
     * @return new designer.
     */
    private Block.Designer whatIsTheDesigner(String val, boolean isItFill) {
        ColorsParser myParser = new ColorsParser();
        Block.Designer result = null;
        if (!val.startsWith("image(")) {
            Color color = myParser.colorFromString(val);
            if (isItFill) {
                result = new Block.FillColor(color);
            } else {
                result = new Block.FrameColor(color);
            }
        } else {
            if ((val.startsWith("image(")) && (val.endsWith(")"))) {
                String param = val.substring(val.indexOf("(") + 1, val.length() - 1);
                if (!fillOrFrame) {
                    throw new RuntimeException("Image type not supported for stroke");
                }
                InputStream is = null;
                try {
                    is = ClassLoader.getSystemClassLoader().getResourceAsStream(param);
                    BufferedImage myIm = ImageIO.read(is);
                    result = new Block.ImageDrawer(myIm);
                } catch (IOException e) {
                    throw new RuntimeException("Failed loading image: " + param, e);
                }
                return result;
            }

        }
        return result;
    }
}