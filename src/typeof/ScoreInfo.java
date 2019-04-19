package typeof;

import java.io.Serializable;

/**
 * score info class.
 */
public class ScoreInfo implements Comparable<ScoreInfo>, Serializable {
    private String name;
    private int gameScore;

    /**
     * constructor.
     *
     * @param name  the name of the high scorer.
     * @param score the score of the player.
     */
    public ScoreInfo(String name, int score) {
        this.gameScore = score;
        this.name = name;
    }

    /**
     * @return the name of the high scorer.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the score of the player.
     */
    public int getScore() {
        return this.gameScore;
    }

    @Override
    public int compareTo(ScoreInfo o) {
        if (this.getScore() > o.getScore()) {
            return -1;
        } else if (o.getScore() > this.getScore()) {
            return 1;
        }
        return 0;
    }
}
