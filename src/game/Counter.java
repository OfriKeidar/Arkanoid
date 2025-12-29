// 211767561 Ofri Keidar
package game;

/**
 * class represent the counter in the game with basic operations.
 */
public class Counter {
    private int counter;

    /**
     * we initialize the counter to start from 0.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * we add number to current count.
     * @param number what we want to add.
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }
    /**
     * we subtract number from current count.
     * @param number what we want to subtract.
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    // get current count.

    /**
     * in that method we get the current count.
     * @return the count.
     */
    public int getValue() {
        return this.counter;
    }
}
