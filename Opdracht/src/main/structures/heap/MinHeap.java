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