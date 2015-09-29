import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INITIAL_CAPACITY = 2;
    private static final int CAPACITY_MULTIPLIER = 2;
    private static final int REDUCE_MARGIN = 4;

    private Item[] queue;
    private int size;


    public RandomizedQueue() {
        queue = (Item[]) new Object[INITIAL_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        validateItem(item);
        if (size == queue.length) {
            enlargeCapacity();
        }
        queue[size++] = item;
    }

    public Item dequeue() {
        validateGetOperation();
        int lastIndex = size - 1;
        swap(StdRandom.uniform(size), lastIndex);
        Item item = queue[lastIndex];
        size--;
        if (size > 0 && size <= queue.length / REDUCE_MARGIN) {
            reduceCapacity();
        }
        return item;
    }

    public Item sample() {
        validateGetOperation();
        return queue[StdRandom.uniform(size)];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandIter<>(queue, size);
    }

    private void swap(int i, int j) {
        Item swap = queue[i];
        queue[i] = queue[j];
        queue[j] = swap;
    }

    private void enlargeCapacity() {
        Item[] newQueue = (Item[]) new Object[queue.length * CAPACITY_MULTIPLIER];
        System.arraycopy(queue, 0, newQueue, 0, queue.length);
        queue = newQueue;
    }

    private void reduceCapacity() {
        Item[] newQueue = (Item[]) new Object[queue.length / CAPACITY_MULTIPLIER];
        System.arraycopy(queue, 0, newQueue, 0, size);
        queue = newQueue;
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    private void validateGetOperation() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
    }

    private static class RandIter<Item> implements Iterator<Item> {

        private Item[] innerQueue;
        private int position;

        public RandIter(Item[] inputArray, int actualLength) {
            innerQueue = (Item[]) new Object[actualLength];
            System.arraycopy(inputArray, 0, innerQueue, 0, actualLength);
            shuffleInnerQueue();
        }

        @Override
        public boolean hasNext() {
            return position != innerQueue.length;
        }

        @Override
        public Item next() {
            validateNextOperation();
            position++;
            return innerQueue[position - 1];
        }

        private void validateNextOperation() {
            if (position == innerQueue.length) {
                throw new NoSuchElementException();
            }
        }

        private void shuffleInnerQueue() {
            for (int i = 0; i < innerQueue.length; i++) {
                swap(i, StdRandom.uniform(i + 1));
            }
        }

        private void swap(int i, int j) {
            Item swap = innerQueue[i];
            innerQueue[i] = innerQueue[j];
            innerQueue[j] = swap;
        }

    }

    public static void main(String[] args) {
    }

}
