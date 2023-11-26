package main.structures.heap;

public class MinHeap<T extends Comparable<T>> {

    private static final int DEFAULT_CAPACITY = 9999;
    private T[] heap;
    private int size;

    public MinHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MinHeap(int capacity) {
        heap = (T[]) new Comparable[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T data) {
        if (heap.length == size) {
            resize(2 * heap.length);
        }
        heap[size] = data;
        int index = size;
        size++;
        while (index > 0 && heap[index].compareTo(heap[parent(index)]) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    public T pop() {
        if (isEmpty()) {
            throw new NullPointerException("Heap is empty");
        }
        T data = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return data;
    }

    public void remove(T destinationInfo) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(destinationInfo)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NullPointerException("Heap does not contain " + destinationInfo);
        }
        heap[index] = heap[size - 1];
        size--;
        heapify(index);
    }

    public T peek() {
        if (isEmpty()) {
            throw new NullPointerException("Heap is empty");
        }
        return heap[0];
    }

    public boolean contains(T data) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(data)) {
                return true;
            }
        }
        return false;
    }

    public void buildHeap(T[] array) {
        heap = array;
        size = array.length;
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MinHeap {\n");
        for (int i = 0; i < size; i++) {
            sb.append("\t").append(heap[i]).append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    public String graphViz() {
StringBuilder sb = new StringBuilder("digraph MinHeap {\n");
        sb.append("\tnode [shape=record]\n");
        for (int i = 0; i < size; i++) {
            sb.append("\t").append(i).append(" [label=\"").append(heap[i]).append("\"]\n");
        }
        for (int i = 0; i < size; i++) {
            if (left(i) < size) {
                sb.append("\t").append(i).append(" -> ").append(left(i)).append("\n");
            }
            if (right(i) < size) {
                sb.append("\t").append(i).append(" -> ").append(right(i)).append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private void heapify(int index) {
        int left = left(index);
        int right = right(index);
        int smallest = index;

        if (left < size && heap[left].compareTo(heap[smallest]) < 0) {
            smallest = left;
        }

        if (right < size && heap[right].compareTo(heap[smallest]) < 0) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Comparable[capacity];
        if (size >= 0) System.arraycopy(heap, 0, temp, 0, size);
        heap = temp;
    }

    private void swap(int index1, int index2) {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return (2 * index) + 1;
    }

    private int right(int index) {
        return (2 * index) + 2;
    }

}