package test;

import main.structures.heap.MinHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {
    private MinHeap<Integer> minHeap;

    @BeforeEach
    public void setUp() {
        minHeap = new MinHeap<>();
    }

    @Test
    void testPushAndSize() {
        assertTrue(minHeap.isEmpty());

        minHeap.push(10);
        assertEquals(1, minHeap.size());

        minHeap.push(5);
        assertEquals(2, minHeap.size());
    }

    @Test
    void testPop() {
        minHeap.push(10);
        minHeap.push(5);

        assertEquals(5, minHeap.pop()); // Min element
        assertEquals(1, minHeap.size());
    }

    @Test
    void testPeek() {
        minHeap.push(10);
        minHeap.push(5);

        assertEquals(5, minHeap.peek()); // Min element without removing
        assertEquals(2, minHeap.size());
    }

    @Test
    void testContains() {
        minHeap.push(10);
        minHeap.push(5);

        assertTrue(minHeap.contains(5));
        assertFalse(minHeap.contains(20)); // Element not in heap
    }

    @Test
    void testRemove() {
        minHeap.push(10);
        minHeap.push(5);
        minHeap.remove(10);

        assertEquals(1, minHeap.size());
        assertFalse(minHeap.contains(10));
    }

    @Test
    void testPopFromEmptyHeap() {
        assertThrows(NullPointerException.class, () -> minHeap.pop());
    }

    @Test
    void testPeekEmptyHeap() {
        assertThrows(NullPointerException.class, () -> minHeap.peek());
    }

    @Test
    void testRemoveNonExistentElement() {
        minHeap.push(10);
        assertThrows(NullPointerException.class, () -> minHeap.remove(20));
    }

    @Test
    void testBuildHeap() {
        Integer[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        minHeap.buildHeap(array);

        assertEquals(9, minHeap.size());
        assertEquals(1, minHeap.peek()); // Min element after heapify
    }

    // Additional tests for checking resizing, heap integrity, etc...
}
