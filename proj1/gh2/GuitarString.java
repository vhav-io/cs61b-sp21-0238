package gh2;

import deque.ArrayDeque;
import deque.Deque;
// TODO: maybe more imports

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString implements Deque<Double> {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);

        // You can use LinkedListDeque here instead if you prefer.
        // ArrayDeque is generally slightly faster for this specific algorithm.
        buffer = new ArrayDeque<>();

        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int currentSize = buffer.size();

        // Remove all current elements and replace them with random noise
        for (int i = 0; i < currentSize; i++) {
            buffer.removeFirst();
            double r = Math.random() - 0.5;
            buffer.addLast(r);
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double removedValue = buffer.removeFirst();
        double nextValue = buffer.get(0); // This is the new front value after removal

        double newDouble = ((removedValue + nextValue) / 2.0) * DECAY;

        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
        
    }

    @Override
    public void addFirst(Double item) {

    }

    @Override
    public void addLast(Double item) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return Deque.super.isEmpty();
    }

    @Override
    public void printDeque() {

    }

    @Override
    public Double removeFirst() {
        return 0.0;
    }

    @Override
    public Double removeLast() {
        return 0.0;
    }

    @Override
    public Double get(int index) {
        return 0.0;
    }
}
    // TODO: Remove all comments that say TODO when you're done.
