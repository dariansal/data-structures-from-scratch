package src.main.java.datastructures;
import java.util.*;

public class StackCustom {

    private int[] stack;

    public StackCustom(int capacity) {
        stack = new int[capacity];
    }

    private int count;

    public void push(int input) {
        if (count == stack.length)
            throw new StackOverflowError(); // stack is full
        stack[count++] = input; // count increments after execution of line
    }

    public int pop() {
        if (count == 0)
            throw new IllegalStateException();

        return stack[--count]; // count decrements before execution
        // the element is technically still in the stack array, but it is not printed
        // because in the toString everything up to but not including the element at the
        // count index is printed, so the current element that is popped will not be
        // printed

    }

    public int peek() {
        if (count == 0)
            throw new IllegalStateException();

        return stack[count - 1];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        int[] content = Arrays.copyOfRange(stack, 0, count);
        // copyOfRange copies the array from 0 index to count index (excluding count)
        return Arrays.toString(content);
    }
}
