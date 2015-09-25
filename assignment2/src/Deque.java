import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        validateItem(item);
        if (first == null) {
            first = new Node<>(null, item, null);
            last = first;
        } else {
            Node<Item> newHead = new Node<>(null, item, first);
            first.prev = newHead;
            first = newHead;
        }
        size++;
    }

    public void addLast(Item item) {
        validateItem(item);
        if (last == null) {
            last = new Node<>(null, item, null);
            first = last;
        } else {
            Node<Item> newLast = new Node<>(last, item, null);
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    public Item removeFirst() {
        validateDeletion();
        Item item = first.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        validateDeletion();
        Item item = last.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator<>(first);
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    private void validateDeletion() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
    }


    private static class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> nextNode;

        public DequeIterator(Node<Item> first) {
            nextNode = first;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public Item next() {
            validateNextOperation();
            Item item = nextNode.item;
            nextNode = nextNode.next;
            return item;
        }

        private void validateNextOperation() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
        }
    }

    private static class Node<Item> {
        Item item;
        Node<Item> prev;
        Node<Item> next;

        public Node(Node<Item> prev, Item item, Node<Item> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {

    }

}
