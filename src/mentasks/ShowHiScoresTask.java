package mentasks;
import gamemechanisim.Animation;
import gamemechanisim.AnimationRunner;

/**
 *
 */
public class ShowHiScoresTask implements Task {
    private Animation scores;
    private AnimationRunner runThis;


    /**
     * constructor.
     *
     * @param runner              animation runner.
     * @param highScoresAnimation animation we want to run.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runThis = runner;
        this.scores = highScoresAnimation;
    }

    /**
     * @return nothing to return.
     */
    @Override
    public Boolean run() {
        this.runThis.run(this.scores);
        return true;
    }
}
