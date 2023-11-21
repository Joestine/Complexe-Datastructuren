package tests;

import main.structures.heap.MinHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {
    MinHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new MinHeap<>();
    }

    @Test
    void testDefaultConstructor() {
        MinHeap<Integer> heap = new MinHeap<>();
        assertTrue(heap.isEmpty());
    }

    @Test
    void testConstructorWithCapacity() {
        MinHeap<Integer> heap = new MinHeap<>(10);
        assertTrue(heap.isEmpty());
    }

    @Test
    void testInsert() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.push(3);
        heap.push(2);
        heap.push(1);
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.pop());
    }

    @Test
    void testInsertWithResize() {
        MinHeap<Integer> heap = new MinHeap<>(2);
        heap.push(2);
        heap.push(1);
        heap.push(3);
        assertEquals(3, heap.size());
    }

    @Test
    void testDeleteSingleElement() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.push(1);
        assertEquals(1, heap.pop());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testDeleteMultipleElements() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.push(3);
        heap.push(2);
        heap.push(1);
        assertEquals(1, heap.pop());
        assertEquals(2, heap.pop());
    }

    @Test
    void testDeleteFromEmptyHeap() {
        MinHeap<Integer> heap = new MinHeap<>();
        assertThrows(NullPointerException.class, heap::pop);
    }

    @Test
    void testSize() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.push(1);
        heap.push(2);
        assertEquals(2, heap.size());
    }

    @Test
    void testIsEmpty() {
        MinHeap<Integer> heap = new MinHeap<>();
        assertTrue(heap.isEmpty());
        heap.push(1);
        assertFalse(heap.isEmpty());
    }

    @Test
    void testContains() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.push(1);
        heap.push(2);
        assertTrue(heap.contains(1));
        assertFalse(heap.contains(3));
    }
}
