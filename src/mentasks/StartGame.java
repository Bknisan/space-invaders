package mentasks;
import gamemechanisim.GameFlow;
import typeof.LevelInformation;

import java.util.List;

/**
 *
 */
public class StartGame implements Task {
    private GameFlow startGame;
    private List<LevelInformation> mylevels;

    /**
     * constructor.
     *
     * @param game   game flow object.
     * @param levels levels i should run.
     */
    public StartGame(GameFlow game, List<LevelInformation> levels) {
        this.startGame = game;
        this.mylevels = levels;
    }

    @Override
    public Boolean run() {
        startGame.runLevels(this.mylevels);
        return true;
    }
}
