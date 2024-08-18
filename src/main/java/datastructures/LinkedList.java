package src.main.java.datastructures;
import java.util.NoSuchElementException;

public class LinkedList {

    private class Node {
        private int info;
        private Node next;

        public Node(int data) {
            info = data;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public void addLast(int item) {
        var node = new Node(item);
        if (isEmpty()) // if list is empty
            first = last = node;
        else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public void addFirst(int item) {
        var node = new Node(item); // create node with data (item)
        if (isEmpty()) {
            first = last = node;
        } else {
            node.next = first; // node points to what first points to (links first and second node)
            first = node; // first now points to the added node
        }
        size--;
    }

    public int indexOf(int item) {
        int index = 0;
        Node current = first;
        while (current != null) { // while we are still looking at node
            if (current.info == item) { // if data of current node is what we are looking for
                return index; // we found index we are looking for, exit loop
            }
            current = current.next; // if not, move current to the next node
            index++; // increment index
        }
        return -1; // returns this if we can't find index of node with data
    }

    public boolean contains(int item) {
        return indexOf(item) != -1; // if index isn't -1, then it's in the linked list
    }

    public void removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException(); // throws this exception

        if (first == last) { // single item in list
            first = last = null;
            size = 0;
            return; // used to exit method
        }
        // two or greater nodes in LinkedList
        Node current = first.next; // current points to second node
        first.next = null; // removes link bewteen first and second node
        first = current; // first points to second node

        size--;
    }

    public void removeLast() {
        if (isEmpty())
            throw new NoSuchElementException(); // throws this exception

        if (first == last) // single node in list
            first = last = null;
        else {
            var previous = getPrevious(last);
            last = previous;
            last.next = null; // same thing as previous.next = null
        }

        size--;
    }

    public int[] convertToArray() {
        int[] array = new int[size];
        var current = first;
        var i = 0;
        while (current != null) {
            array[i++] = current.info; // i increments after loop execution
            current = current.next;
        }
        return array;
    }

    public void reverse() {
        if (isEmpty()) {
            return; // exits method because we can't reverse empty list
        }

        var firstTemp = last;
        Node previous;

        while (last != first) {
            previous = getPrevious(last); // moving previous to node before last
            previous.next = last.next; // previous points to what last points to
            last.next = previous; // links last node to the node before it
            last = previous; // moves last reference to point at the previous node
        }
        first = firstTemp; // moves first to the beginning of reversed list

    }

    public int getKthFromtheEnd(int k) {
        var current = first;
        var after = first; // start both nodes at beginning
        while (k - 1 > 0) {
            after = after.next; // after moves to next node k-1 times
            k--;
            if (after == null) {// k is greater than size of linked list
                throw new IllegalArgumentException();
                // after should be a certain distance away from current node, shouldn't be past
                // the last node
                // you could also write if (k>size) only if you know size of linked list
            }
        }
        for (int i = 0; i < k - 1; i++) {
            after = after.next;
        }
        while (after != last) { // move both nodes one spot up until after gets to end of list
            after = after.next;
            current = current.next;
        }

        return current.info; // return the data of current bcause that is kth node from end
    }

    private Node getPrevious(Node node) { // whatever node we give, it will return previous node
        var current = first;
        while (current != null) {
            if (current.next == node) {
                return current;
            }
            current = current.next;
        }
        return null;

    }

    private boolean isEmpty() { // implementation detail don't want it accessible out of class
        return first == null;
    }

}
