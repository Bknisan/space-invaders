package gamemechanisim;

import biuoop.DialogManager;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import complexobjects.Counter;
import detectorsany.LivesIndicator;
import detectorsany.ScoreIndicator;
import gamelevels.EndScreen;
import gamelevels.HighScoresAnimation;
import gamelevels.KeyPressStoppableAnimation;
import typeof.HighScoresTable;
import typeof.LevelInformation;
import typeof.ScoreInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class GameFlow {
    private KeyboardSensor keyBoard;
    private AnimationRunner myRunner;
    private ScoreIndicator myScore;
    private LivesIndicator myLives;
    private HighScoresTable myHighscores;

    /**
     * class constructor.
     *
     * @param ar              the animation runner.
     * @param ks              the keyboard sensor.
     * @param scoresCurrently existing high score table.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable scoresCurrently) {
        this.keyBoard = ks;
        this.myRunner = ar;
        this.myLives = new LivesIndicator(7);
        this.myScore = new ScoreIndicator(new Counter());
        this.myHighscores = scoresCurrently;

    }

    /**
     * @param levels wanted levels to be played.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelinfo : levels) {
            GameLevel current = new GameLevel(levelinfo, myScore, myLives, myRunner, keyBoard);
            current.initialize();
            while (!current.shouldStop()) {
                current.playOneTurn();
                if (myLives.getNumOfLives().getValue() > 0 && current.getgameBlockCounter().getValue() > 0) {
                    current.setRunning();
                }
                if (myLives.getNumOfLives().getValue() > 0 && current.getgameBlockCounter().getValue() == 0) {
                    current.nextLevel();
                    current.setRunning();
                }
            }
            if (myLives.getNumOfLives().getValue() == 0) {
                break;
            }
        }
        if (myLives.getNumOfLives().getValue() == 0) {
            DrawSurface d = myRunner.getGui().getDrawSurface();
            String loser = "Game Over. Your score is ";
            myRunner.run(new KeyPressStoppableAnimation(this.keyBoard, "space",
                    new EndScreen(loser, this.myScore.getScoreCounter().getValue())));
        } else {
            DrawSurface d = myRunner.getGui().getDrawSurface();
            String winner = "You Win! Your score is ";
            myRunner.run(new KeyPressStoppableAnimation(this.keyBoard, "space", new EndScreen(winner,
                    this.myScore.getScoreCounter().getValue())));
        }
        if (this.myHighscores.getHighScores().isEmpty() || this.myScore.getScoreCounter().getValue()
                > this.myHighscores.getHighScores().get(myHighscores.getHighScores().size() - 1).getScore()
                || this.myHighscores.getHighScores().size() < 5) {
            DialogManager playerName = this.myRunner.getGui().getDialogManager();
            String name = playerName.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo newPlayer = new ScoreInfo(name, this.myScore.getScoreCounter().getValue());
            this.myHighscores.add(newPlayer);
            try {
                this.myHighscores.save(new File("highscores.txt"));
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        myRunner.run(new KeyPressStoppableAnimation(keyBoard, "space",
                new HighScoresAnimation(myHighscores, "space", keyBoard)));

        this.reset();
    }

    /**
     *
     */
    public void reset() {
        this.myLives = new LivesIndicator(7);
        this.myScore = new ScoreIndicator(new Counter());
    }

}
