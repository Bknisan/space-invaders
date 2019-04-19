package fileraeders;

import complexobjects.Block;
import complexobjects.Velocity;

import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.IOException;

import gamelevels.ColorBackGround;
import gamelevels.ImageBack;
import typeof.LevelInformation;
import typeof.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * file reader main class.
 */
public class LevelSpecificationReader implements LevelInformation {
    private String levelName = "level_name";
    private String levelStart = "START_LEVEL";
    private String levelEnd = "END_LEVEL";
    private String blocksBuild = "START_BLOCKS";
    private String blocksEnd = "END_BLOCKS";
    private String ballSpeeds = "ball_velocities";
    private String background = "background";
    private String paddleSpeed = "paddle_speed";
    private String paddleWidth = "paddle_width";
    private String startX = "blocks_start_x";
    private String startY = "blocks_start_y";
    private String blocksHeight = "row_height";
    private String numOfBlocks = "num_blocks";
    private String blocksDefDirectory = "block_definitions";
    private String levelNameWanted;
    private int paddleSpeedReal;
    private int paddleWidthReal;
    private Sprite backGround;
    private List<Velocity> velocities;
    private List<LevelInformation> listOfLevels;
    private List<LevelSet> possibleSets;
    private List<Block> levelBlocks;
    private int howManyToRemove;
    private BlockDefinitionsReader newReader;

    /**
     *
     */
    public LevelSpecificationReader() {
        this.levelBlocks = new ArrayList<>();
        this.listOfLevels = new ArrayList<>();
        this.possibleSets = new ArrayList<>();
        this.velocities = new ArrayList<>();
        this.newReader = new BlockDefinitionsReader();

    }

    /**
     * @param reader textual file reader.
     * @return list of levels according to the specifications.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        this.reset();
        boolean blockReader = false;
        int startXValue = 0;
        int startYValue = 0;
        int rowHeight = 0;
        int levelRowIndex = 0;
        LineNumberReader readIt = null;
        LevelSpecificationReader thisLevel = new LevelSpecificationReader();
        try {
            readIt = new LineNumberReader(reader);
            String currentLine;
            while ((currentLine = readIt.readLine()) != null) {
                if (!(currentLine.startsWith("#") && currentLine.startsWith(""))) {
                    if (!blockReader) {
                        if (currentLine.startsWith(levelName)) {
                            thisLevel.levelNameWanted = currentLine.substring(currentLine.indexOf(":") + 1);
                        } else if (currentLine.startsWith(blocksDefDirectory)) {
                            String blocksdefPlace = currentLine.substring(currentLine.indexOf(":") + 1);
                            thisLevel.newReader.blockDefReader(blocksdefPlace);
                        } else if (currentLine.startsWith(levelStart)) {
                            thisLevel = new LevelSpecificationReader();
                        } else if (currentLine.startsWith(blocksBuild)) {
                            blockReader = true;
                            levelRowIndex = Integer.valueOf(0);
                        } else if (currentLine.startsWith(startX)) {
                            startXValue = Integer.parseInt(currentLine.substring(currentLine.indexOf(":") + 1));
                        } else if (currentLine.startsWith(startY)) {
                            startYValue = Integer.parseInt(currentLine.substring(currentLine.indexOf(":") + 1));
                        } else if (currentLine.startsWith(blocksHeight)) {
                            rowHeight = Integer.valueOf(currentLine.substring(currentLine.indexOf(":") + 1));
                        } else if (currentLine.startsWith(ballSpeeds)) {
                            String s = currentLine.substring(currentLine.indexOf(":") + 1);
                            String[] velocitiesMy = s.split(" ");
                            for (String velocity : velocitiesMy) {
                                String[] directions = velocity.split(",");
                                thisLevel.velocities.add(Velocity.fromAngleAndSpeed(Double.parseDouble(directions[0])
                                        , Double.parseDouble(directions[1])));
                            }
                        } else if (currentLine.startsWith(paddleSpeed)) {
                            thisLevel.paddleSpeedReal =
                                    Integer.parseInt(currentLine.substring(currentLine.indexOf(":") + 1));
                        } else if (currentLine.startsWith(paddleWidth)) {
                            thisLevel.paddleWidthReal =
                                    Integer.parseInt(currentLine.substring(currentLine.indexOf(":") + 1));
                        } else if (currentLine.startsWith(numOfBlocks)) {
                            thisLevel.howManyToRemove =
                                    Integer.parseInt(currentLine.substring(currentLine.indexOf(":") + 1));
                        } else if (currentLine.startsWith(levelEnd)) {
                            this.addLevel(thisLevel);
                            levelRowIndex = 0;
                            thisLevel = null;
                            blockReader = false;
                            newReader = null;
                        } else if (currentLine.startsWith(background)) {
                            InputStream is = null;
                            String s = currentLine.substring(currentLine.indexOf(":") + 1);
                            if (s.startsWith("color")) {
                                thisLevel.backGround = new ColorBackGround(new ColorsParser().colorFromString(s));
                            } else {
                                String substring = currentLine.substring(currentLine.indexOf("(") + 1,
                                        currentLine.indexOf(")"));
                                is = ClassLoader.getSystemClassLoader().getResourceAsStream(substring);
                                BufferedImage image = ImageIO.read(is);
                                thisLevel.backGround = new ImageBack(image);
                            }
                        }
                    } else if (currentLine.startsWith(blocksEnd)) {
                        blockReader = false;
                    } else {
                        int currentX = startXValue;
                        for (int i = 0; i < currentLine.length(); i++) {
                            String symbol = currentLine.substring(i, i + 1);
                            int currentY = (rowHeight * levelRowIndex) + startYValue;
                            if (thisLevel.newReader.getMyFactory().isSpaceSymbol((symbol))) {
                                currentX += thisLevel.newReader.getMyFactory().getSpaceWidth(symbol);
                            } else {
                                Block b = thisLevel.newReader.getMyFactory().getBlock(symbol, currentX, currentY);
                                currentX = (int) (currentX + b.getCollisionRectangle().getWidth());
                                thisLevel.addBlock(b);
                            }
                        }
                        if (!currentLine.equals("")) {
                            levelRowIndex += 1;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("null pointer");

        } finally {
            if (readIt != null) {
                try {
                    readIt.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listOfLevels;
    }

    /**
     * @param newLevel new readerd level.
     */
    public void addLevel(LevelInformation newLevel) {
        this.listOfLevels.add(newLevel);
    }

    /**
     * @param reader textual file reader.
     */
    public void readLevelSets(java.io.Reader reader) {
        LineNumberReader lineIndicator = new LineNumberReader(reader);
        String line;
        int lineNumber = 1;
        LevelSet newGameSet = null;
        try {
            line = lineIndicator.readLine();
            while (!line.isEmpty()) {
                if (lineNumber % 2 == 1) {
                    newGameSet = new LevelSet();
                    newGameSet.setSetKey(line.substring(0, 1));
                    newGameSet.setDescription(line.substring(2));
                } else {
                    newGameSet.setDefinitionsPath(line);
                    possibleSets.add(newGameSet);
                }
                line = lineIndicator.readLine();
                if (line == null) {
                    break;
                }
                if (line.isEmpty()) {
                    line = lineIndicator.readLine();
                }
                lineNumber++;
            }
        } catch (Exception e) {
            throw new RuntimeException("couldnt read line");
        } finally {
            try {
                lineIndicator.close();
            } catch (Exception ignored) {
                System.out.println("something wrong");
            }
        }
    }

    /**
     * @return all the posiblle sets.
     */
    public List<LevelSet> getPossibleSets() {
        return this.possibleSets;
    }

    /**
     * @return number of balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return this.velocities.size();
    }

    /**
     * @return list of the ball velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     * @return the paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeedReal;
    }

    /**
     * @return the paddle width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidthReal;
    }

    /**
     * @return the level name.
     */
    @Override
    public String levelName() {
        return this.levelNameWanted;
    }

    /**
     * @return the level background.
     */
    @Override
    public Sprite getBackground() {
        return this.backGround;
    }

    /**
     * @return list of blocks in the level.
     */
    @Override
    public List<Block> blocks() {
        return this.levelBlocks;
    }

    /**
     * @return number of blocks in the level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.howManyToRemove;
    }

    /**
     * @param e new block to the list
     */
    public void addBlock(Block e) {
        this.levelBlocks.add(e);
    }

    /**
     * function that resets all the fields.
     */
    public void reset() {
        this.levelBlocks = new ArrayList<>();
        this.listOfLevels = new ArrayList<>();
        this.possibleSets = new ArrayList<>();
        this.velocities = new ArrayList<>();
        this.newReader = new BlockDefinitionsReader();
    }

}
