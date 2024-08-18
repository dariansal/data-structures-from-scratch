package src.main.java.datastructures;
import java.util.Arrays;

public class Heap {
    private static final int INITIAL_CAPACITY = 20;

    private int[] nodes = new int[INITIAL_CAPACITY];
    int i = 0; // index of last element of heap

    // involves bubbling up
    public void add(int number) {
        ensureCapacity();

        nodes[i] = number;
        int currentIndex = i;
        // Need separate variable so that you can follow along the node that
        // you bubble up without losing count of your last index in the array
        while (i > 0 && nodes[currentIndex] > nodes[parentIndex(currentIndex)]) {
            // while current node is bigger than it's parent, swap their values
            int parentIndex = parentIndex(currentIndex); // (currentI - 1/2)
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex; // new index is index of previous parent
            // Now that we swapped nodes by bubbling our node up, we need our current index
            // back on the node that we moved up
        }
        i++;

    }

    // involves bubbling down
    public int remove() {
        if (i == 0)
            throw new IllegalArgumentException("Empty HEAP");

        int removedElement = nodes[0];
        nodes[0] = nodes[i];
        nodes[i] = 0;
        int currentIndex = 0;
        // start at root and go down

        while (nodes[currentIndex] < maxChildValue(currentIndex)) {
            // while the node is less than its largest child, swap and bubble node down
            int largerChildIndex = largerChildIndex(currentIndex); // (2*currentI +1) or + 2
            swap(currentIndex, largerChildIndex);
            currentIndex = largerChildIndex;
            // Current index changes because we moved our node down
        }
        i--;
        return removedElement;
        // we deleted a node

    }

    private void ensureCapacity() {
        if (i == nodes.length) {
            nodes = Arrays.copyOf(nodes, 2 * nodes.length);
            // Arrays.copyOf(arrayTobeCopied, newLengthofArray)
        }
    }

    public boolean isEmpty() {
        return i == 0;
    }

    private void swap(int i, int j) {
        int temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
        // Gives index of parent for your current node
    }

    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    private int maxChildValue(int index) {
        return Math.max(nodes[leftChildIndex(index)], nodes[rightChildIndex(index)]);
    }

    private int largerChildIndex(int index) {
        return (nodes[leftChildIndex(index)] > nodes[rightChildIndex(index)]) ? leftChildIndex(index)
                : rightChildIndex(index);
        // if leftChild is greater, return index of leftchild, else, return rightchild
    }

    @Override
    public String toString() {
        return Arrays.toString(nodes);
    }

}
