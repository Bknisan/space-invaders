package mentasks;

/**
 *
 */
public class Selection {
    private String stopKey;
    private String printedLine;
    private Object whatVal;

    /**
     * constructor.
     *
     * @param key  stop key.
     * @param line line to print.
     * @param val  returned value.
     */
    public Selection(String key, String line, Object val) {
        this.printedLine = line;
        this.stopKey = key;
        this.whatVal = val;
    }

    /**
     * @return stop key.
     */
    public String getStopKey() {
        return stopKey;
    }

    /**
     * @return title.
     */
    public String getPrintedLine() {
        return printedLine;
    }

    /**
     * @return wanted val.
     */
    public Object getWhatVal() {
        return whatVal;
    }
}
