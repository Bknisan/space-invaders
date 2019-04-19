package gamelevels;

import complexobjects.Block;
import complexobjects.Velocity;
import factorisandas.ChangeBlockHeight;
import factorisandas.ChangeBlockWidth;
import factorisandas.SimpleBlockCreator;
import factorisandas.ChangeBlockColors;
import geometryprimitives.Point;
import typeof.LevelInformation;
import typeof.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * main class.
 */
public class Invaders implements LevelInformation {
    private int balls;
    private List<Velocity> ballsVelocity;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite backGround;
    private int numOfBlocksToRemove;
    private Map<Block, Point> primePositions;

    /**
     * constructor.
     */
    public Invaders() {
        this.paddleSpeed = 300;
        this.paddleWidth = 100;
        this.levelName = "Shiny Crocs Attack";
        this.backGround = new InvadersBack();
        this.numOfBlocksToRemove = 50;
        this.primePositions = new HashMap<>();
    }


    /**
     * @return number of balls in the game.
     */
    @Override
    public int numberOfBalls() {
        return this.balls;
    }

    /**
     * @return list of ball velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballsVelocity;
    }

    /**
     * @return the paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return the paddle width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return the level name.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return the game background.
     */
    @Override
    public Sprite getBackground() {
        return this.backGround;
    }

    /**
     * @return list of the block in the game.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        List<Color> levelColor = new ArrayList<>();
        levelColor.add(Color.gray);
        levelColor.add(Color.red);
        levelColor.add(Color.yellow);
        levelColor.add(Color.green);
        levelColor.add(Color.white);
        levelColor.add(Color.pink);
        levelColor.add(Color.cyan);
        int startX = 10;
        int startY = 20;
        int width = 40;
        int height = 30;
        ChangeBlockWidth my = new ChangeBlockWidth(new ChangeBlockHeight(new SimpleBlockCreator(),
                "30"), "40");
        ChangeBlockColors myPattern = new ChangeBlockColors(my,
                "image(crocs.jpg)", true, 1);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                Block myBlock = myPattern.create(startX + (j * width) + (10 * j),
                        startY + (i * height) + 10 * i);
                blocks.add(myBlock);
                this.primePositions.put(myBlock, new Point(startX + (j * width) + (10 * j),
                        startY + (i * height) + 10 * i));
            }
        }
        return blocks;
    }

    /**
     * @return how many blocks the level contains.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocksToRemove;
    }

    /**
     * @return first upper left points of all blocks.
     */
    public Map<Block, Point> getPrimePositions() {
        return primePositions;
    }
}
