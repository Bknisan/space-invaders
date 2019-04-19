package typeof;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * high score table class.
 */
public class HighScoresTable implements Serializable {
    private List<ScoreInfo> myTable;
    private int tableSize;

    /**
     * @param size number of high scorers in the table.
     */
    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.myTable = new ArrayList<>();
        this.tableSize = size;
    }

    /**
     * @param score new high scorer to add.
     */
    // Add a high-score.
    public void add(ScoreInfo score) {
        this.myTable.add(score);
        this.sortList();
        if (this.myTable.size() > this.getTableSize()) {
            this.myTable.remove(5);
        }
    }

    /**
     * @return list of high scorers sorted.
     */
    // Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        Collections.sort(this.myTable);
        return this.myTable;
    }


    /**
     * @param score current score.
     * @return the rank on the table of the current score;
     */
    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        int currentRank = 1;
        this.sortList();
        for (ScoreInfo aMyTable : this.myTable) {
            if (aMyTable.getScore() > score) {
                currentRank++;
            }
        }
        return currentRank;
    }

    /**
     *
     */
    // Clears the table
    public void clear() {
        List<ScoreInfo> copyOfMine = this.myTable;
        this.myTable.removeAll(copyOfMine);
    }

    /**
     * @param filename file to read from.
     * @throws IOException throws exception if the file does not exist.
     */
    // Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        ObjectInputStream reader = null;
        try {
            reader = new ObjectInputStream(new FileInputStream("highscores.txt"));
            this.myTable = ((HighScoresTable) reader.readObject()).getHighScores();
            this.tableSize = ((HighScoresTable) reader.readObject()).getTableSize();
        } catch (Exception e) {
            this.myTable = loadFromFile(filename).getHighScores();
            this.tableSize = loadFromFile(filename).getTableSize();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    /**
     * @param filename file to write on.
     * @throws IOException throws exception if the file does not exist.
     */
    // Save table data to the specified file.
    public void save(File filename) throws IOException {
        ObjectOutputStream writer = null;
        try {
            writer = new ObjectOutputStream(new FileOutputStream("highscores.txt"));
            writer.writeObject(this);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * @param filename file to read from.
     * @return table of high scores.
     */
    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        if (!filename.exists()) {
            return new HighScoresTable(5);
        }
        ObjectInputStream reader = null;
        try {
            reader = new ObjectInputStream(new FileInputStream("highscores.txt"));
            return (HighScoresTable) reader.readObject();
        } catch (Exception e) {
            return new HighScoresTable(5);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("cant close the file");
                }
            }
        }
    }

    /**
     * sorting function by score.
     */
    public void sortList() {
        Collections.sort(this.myTable);
    }

    /**
     * @return size of the table.
     */
    public int getTableSize() {
        return this.tableSize;
    }

}
