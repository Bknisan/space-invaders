package complexobjects;
/**
 *
 */
public class Counter {
    private int myValue;

    /**
     * class constructor.
     */
    public Counter() {
        this.myValue = 0;
    }

    /**
     * @param number added value to current count.
     */
    public void increase(int number) {
        this.myValue = this.myValue + number;
    }

    /**
     * @param number decresed value to current count.
     */
    public void decrease(int number) {
        this.myValue = this.myValue - number;
    }

    /**
     * @return value of this counter.
     */
    public int getValue() {
        return this.myValue;
    }
}