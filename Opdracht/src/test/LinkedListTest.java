package test;

import main.structures.linkedlist.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    private LinkedList<String> list;

    @BeforeEach
    public void setUp() {
        list = new LinkedList<>();
    }

    @Test
    void testAddAndSize() {
        assertTrue(list.isEmpty());

        list.add("1");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());

        list.add("2");
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveByData() {
        list.add("1");
        list.add("2");
        list.add("3");

        assertEquals(3, list.size());
        assertEquals("2", list.remove("2"));
        assertNull(list.remove("4")); // non-existing element
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveByIndex() {
        list.add("1");
        list.add("2");
        list.add("3");

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1)); // negative index
        assertEquals("1", list.remove(0));
        assertNull(list.remove(5)); // index greater than size
        assertEquals(2, list.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());

        list.add("1");
        assertFalse(list.isEmpty());

        list.remove("1");
        assertTrue(list.isEmpty());
    }

    @Test
    void testGetByData() {
        list.add("1");
        list.add("2");
        list.add("3");

        assertEquals("2", list.get("2"));
        assertNull(list.get("4")); // non-existing element
    }

    @Test
    void testGetByIndex() {
        list.add("1");
        list.add("2");
        list.add("3");

        assertEquals("1", list.get(0));
        assertNull(list.get(3));
    }

    @Test
    void testContains() {
        list.add("1");
        list.add("2");

        assertTrue(list.contains("1"));
        assertFalse(list.contains("3")); // non-existing element
    }

    @Test
    void testAddFirst() {
        list.addFirst("1");
        assertEquals(1, list.size());
        assertEquals("1", list.getHead().getData());

        list.addFirst("2");
        assertEquals(2, list.size());
        assertEquals("2", list.getHead().getData());
    }

    @Test
    void testToString() {
        list.add("1");
        list.add("2");

        String expected = "LinkedList: [\n1\n2\n]";
        assertEquals(expected, list.toString());
    }
}
