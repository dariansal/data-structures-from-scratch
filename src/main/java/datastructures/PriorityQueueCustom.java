package src.main.java.datastructures;
public class PriorityQueueCustom {
    private Heap heap = new Heap();

    public void enqueue(int item) {
        heap.add(item);
    }

    public int dequeue() {
        return heap.remove();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

}
