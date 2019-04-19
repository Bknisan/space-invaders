package gamemechanisim;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import complexobjects.Block;
import complexobjects.Counter;
import complexobjects.Paddle;
import detectorsany.BallRemover;
import detectorsany.BlockRemover;
import detectorsany.LivesIndicator;
import detectorsany.ScoreIndicator;
import gamelevels.CountDownAnimation;
import gamelevels.Invaders;
import gamelevels.PauseScreen;
import geometryprimitives.Ball;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import typeof.Collidable;
import typeof.LevelInformation;
import typeof.Sprite;
import typeof.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * the game class.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle gamePaddle;
    private Counter gameBlockCounter;
    private BlockRemover cleaner;
    private BallRemover ballCleaner;
    private ScoreIndicator myScore;
    private LivesIndicator myLives;
    private boolean running;
    private AnimationRunner runner;
    private biuoop.KeyboardSensor gameKey;
    private LevelInformation playedLevel;
    private AliensShot enemys;
    private AliensMove enemyMove;
    private int calls;
    private int callShots;
    private int whatIstheLevel;

    /**
     * GameLevel class constructor.
     *
     * @param preScore     what is my score.
     * @param preLives     how many lives i got left.
     * @param currentLevel wanted level to play.
     * @param runner       animation runner.
     * @param keyBoard     game keyboard sensor.
     */
    public GameLevel(LevelInformation currentLevel, ScoreIndicator preScore,
                     LivesIndicator preLives, AnimationRunner runner, KeyboardSensor keyBoard) {
        this.playedLevel = currentLevel;
        this.gameBlockCounter = new Counter();
        this.gameBlockCounter.increase(playedLevel.numberOfBlocksToRemove());
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.cleaner = new BlockRemover(this, this.gameBlockCounter);
        this.ballCleaner = new BallRemover(this);
        this.cleaner.whatIsTheScore().increase(preScore.getScoreCounter().getValue());
        this.myScore = preScore;
        this.myLives = preLives;
        this.runner = runner;
        this.gameKey = keyBoard;
        this.running = true;
        this.gamePaddle = new Paddle(keyBoard, currentLevel.paddleSpeed(),
                currentLevel.paddleWidth(), this.environment, this);
        this.enemys = new AliensShot();
        this.enemyMove = new AliensMove();
        this.calls = 0;
        this.callShots = 0;
        this.whatIstheLevel = 0;
    }

    /**
     * function that add's an object to the colidables list.
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * function that add's an object to the sprites list.
     *
     * @param s sprite object
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * function that initializing a new game structure. creating blocks and the ball game and adding
     * them to the relevant lists.
     */
    public void initialize() {
        this.sprites.addSprite(playedLevel.getBackground());
        this.gamePaddle.getThisOne().addHitListener(cleaner);
        this.gamePaddle.getThisOne().addHitListener(ballCleaner);
        this.gamePaddle.addToGame(this);
        List<Block> levelBlocks = playedLevel.blocks();
        for (int i = 0; i < levelBlocks.size(); i++) {
            levelBlocks.get(i).addHitListener(cleaner);
            levelBlocks.get(i).addHitListener(ballCleaner);
            levelBlocks.get(i).addToGame(this);
        }
        Block lowerIndicator = new Block(new Rectangle(new Point(0, 600), 800, 15), Color.cyan,
                Color.black, 0);
        lowerIndicator.addToGame(this);
        lowerIndicator.addHitListener(ballCleaner);
        Block indicatores = new Block(new Rectangle(new Point(0, 0), 800, 15), Color.white,
                Color.black, 0);
        indicatores.addToGame(this);
        indicatores.addHitListener(ballCleaner);
        myLives.addToGame(this);
        myScore.addToGame(this);
        List<Block> shieldLeft = this.buildShield(new Point(100, 500), 5, 40);
        List<Block> shieldCentral = this.buildShield(new Point(320, 500), 5, 40);
        List<Block> shieldRight = this.buildShield(new Point(540, 500), 5, 40);
        this.addListToGame(shieldLeft);
        this.addListToGame(shieldCentral);
        this.addListToGame(shieldRight);
    }

    /**
     * function that starts the animation loop.
     */
    public void playOneTurn() {
        this.runner.run(new CountDownAnimation(4, 3, this.sprites, playedLevel.levelName()));
        this.runner.run(this);
        for (int i = 0; i < this.sprites.getMySprites().size(); i++) {
            if (this.sprites.getMySprites().get(i) instanceof Ball) {
                ((Ball) this.sprites.getMySprites().get(i)).removeFromGame(this);
            }
        }
        if (this.getgameBlockCounter().getValue() == 0) {
            this.getgameBlockCounter().increase(this.sprites.getMyBlocks().size());
        }
        if (this.sprites.getMyBlocks().size() > 0) {
            this.myLives.getNumOfLives().decrease(1);
        }
    }

    /**
     * @return the member of the current game, the enviroment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * @param c the collidable we want to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * @param s the sprite we want to remove.
     */
    public void removeSprite(Sprite s) {
        List<Sprite> modified = new ArrayList<>(getSprites());
        modified.remove(s);
        this.sprites.setMySprites(modified);

    }


    /**
     * @return the game sprites list.
     */
    public List<Sprite> getSprites() {
        return this.sprites.getMySprites();
    }


    /**
     * @param d  the game surface.
     * @param dt time passed since last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        callShots++;
        calls++;
        // game-specific logic
        this.sprites.drawAllOn(d);
        d.setColor(Color.black);
        d.drawText(500, 12, "Level Name: " + this.playedLevel.levelName(), 13);
        this.sprites.notifyAllTimePassed(dt);
        if (this.gameKey.isPressed("p")) {
            this.runner.run(new PauseScreen(this.gameKey));
        }
        if (this.gameKey.isPressed(KeyboardSensor.SPACE_KEY) && this.callShots >= 25) {
            Ball shot = new Ball(new Point((gamePaddle.getCollisionRectangle().getUpperLeft().getX()
                    + gamePaddle.getWidth() / 2),
                    (gamePaddle.getCollisionRectangle().getUpperLeft().getY() - 3)),
                    2, Color.white, environment);
            shot.setVelocity(0, -300);
            shot.addToGame(this);
            this.callShots = 0;
        }
        this.myScore.setScoreCounter(cleaner.whatIsTheScore());
        if ((this.gameBlockCounter.getValue() == 0) || this.enemyMove.getLowetEdge(this.sprites.getMyBlocks()) > 500) {
            this.running = false;
        } else if (calls == 100 && this.sprites.getMyBlocks().size() > 0) {
            Ball alienShot = this.enemys.makeShout(this.sprites.getMyBlocks(), this.environment);
            alienShot.setVelocity(0, 250);
            alienShot.addToGame(this);
            calls = 0;
        } else if (this.enemyMove.getLowetEdge(this.sprites.getMyBlocks()) <= 500) {
            this.enemyMove.moveThem(this.sprites.getMyBlocks());
        }
    }

    /**
     * @return yes or no if the animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * making the game run valid again.
     */
    public void setRunning() {
        this.running = true;
        this.sprites.setPositions((Invaders) this.playedLevel);
    }

    /**
     * @return how many block still left to destroy.
     */
    public Counter getgameBlockCounter() {
        return this.gameBlockCounter;
    }

    /**
     * @param start  start point of the shield.
     * @param height shield height.
     * @param width  shield width.
     * @return list of complete shield built of pixel blocks.
     */
    public List<Block> buildShield(Point start, int height, int width) {
        List<Block> shield = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Block pixel = new Block(new Rectangle(new Point(start.getX() + j * 4, start.getY() + i * 4),
                        4, 4), Color.cyan, Color.cyan, 1);
                shield.add(pixel);
            }
        }
        return shield;
    }

    /**
     * @param myShield wanted shield.
     */
    public void addListToGame(List<Block> myShield) {
        for (Block aMyShield : myShield) {
            aMyShield.addToGame(this);
            aMyShield.addHitListener(cleaner);
            aMyShield.addHitListener(ballCleaner);
        }
    }

    /**
     *
     */
    public void nextLevel() {
        this.whatIstheLevel++;
        this.playedLevel = new Invaders();
        this.gameBlockCounter = new Counter();
        this.gameBlockCounter.increase(50);
        this.enemys = new AliensShot();
        this.enemyMove = new AliensMove();
        for (int i = 0; i < this.whatIstheLevel; i++) {
            this.enemyMove.setCoeficient();
        }
        this.calls = 0;
        this.callShots = 0;
        this.cleaner = new BlockRemover(this, this.gameBlockCounter);
        this.cleaner.whatIsTheScore().increase(this.myScore.getScoreCounter().getValue());
        List<Block> levelBlocks = playedLevel.blocks();
        for (int i = 0; i < levelBlocks.size(); i++) {
            levelBlocks.get(i).addHitListener(cleaner);
            levelBlocks.get(i).addHitListener(ballCleaner);
            levelBlocks.get(i).addToGame(this);
        }
        this.gamePaddle.getThisOne().addHitListener(cleaner);
    }

}
