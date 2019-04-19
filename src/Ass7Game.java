
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamelevels.Invaders;
import gamelevels.HighScoresAnimation;
import gamelevels.KeyPressStoppableAnimation;
import gamemechanisim.AnimationRunner;
import gamemechanisim.GameFlow;
import mentasks.Task;
import mentasks.MenuAnimation;
import mentasks.ExitTask;
import mentasks.StartGame;
import mentasks.ShowHiScoresTask;
import typeof.HighScoresTable;
import typeof.LevelInformation;
import mentasks.Menu;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * running class.
 */
public class Ass7Game {
    /**
     * main execution method.
     *
     * @param args levels you want to add.
     */
    public static void main(String[] args) {
        HighScoresTable myScores = HighScoresTable.loadFromFile(new File("highscores.txt"));
        GUI gui = new GUI("arkanoid", 800, 600);
        KeyboardSensor kb = gui.getKeyboardSensor();
        AnimationRunner myRunner = new AnimationRunner(gui, 60);
        KeyPressStoppableAnimation highScores = new KeyPressStoppableAnimation(kb,
                "space", (new HighScoresAnimation(myScores,
                "space", kb)));
        MenuAnimation<Task<Void>> general = new MenuAnimation<>("Slippers Invaders", kb, myRunner);
        GameFlow myFlow = new GameFlow(myRunner, kb, myScores);
        List<LevelInformation> myLevels = new ArrayList<>();
        myLevels.add(new Invaders());
        general.addSelection("s", "start game", new StartGame(myFlow, myLevels));
        general.addSelection("h", "high scores", new ShowHiScoresTask(myRunner, highScores));
        general.addSelection("q", "quit", new ExitTask());
        Boolean runIt = true;
        while (runIt) {
            myRunner.run(general);
            if (general.getStatus() instanceof Menu) {
                myRunner.run(general);
                Task task = (Task) general.getStatus();
                task.run();
                highScores.reset();
            } else {
                Task task = (Task) general.getStatus();
                runIt = task.run();
                highScores.reset();
            }
            general.beginAgain();
        }
        gui.close();
    }
}