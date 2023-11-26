package test;

import main.structures.tree.AVLTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {
    private AVLTree<Integer> avlTree;

    @BeforeEach
    public void setUp() {
        avlTree = new AVLTree<>();
    }

    @Test
    void testInsertAndSize() {
        assertTrue(avlTree.isEmpty());
        avlTree.add(10);
        assertFalse(avlTree.isEmpty());
        assertEquals(1, avlTree.size());

        avlTree.add(20);
        avlTree.add(5);
        assertEquals(3, avlTree.size());
    }

    @Test
    void testDelete() {
        avlTree.add(10);
        avlTree.add(20);
        avlTree.add(5);
        avlTree.remove(10);

        assertEquals(2, avlTree.size());
        // Additional checks to ensure tree balancing
    }

    @Test
    void testSearch() {
        avlTree.add(10);
        avlTree.add(20);
        avlTree.add(5);

        Function<Integer, String> propertyExtractor = String::valueOf;
        assertEquals(10, avlTree.get("10", propertyExtractor));
        assertNull(avlTree.get("15", propertyExtractor)); // key not present
    }

    @Test
    void testIsEmpty() {
        assertTrue(avlTree.isEmpty());
        avlTree.add(10);
        assertFalse(avlTree.isEmpty());
    }

    @Test
    void testGraphViz() {
        avlTree.add(10);
        avlTree.add(20);
        String expected = "digraph G {\n10 -> 20;\n}";
        assertEquals(expected, avlTree.graphViz());
    }

    @Test
    void testRightRotation() {
        avlTree.add(30);
        avlTree.add(20);
        avlTree.add(10); // This should trigger a right rotation

        // Verifying the structure after right rotation
        String expectedStructure = "digraph G {\n20 -> 10;\n20 -> 30;\n}";
        assertEquals(expectedStructure, avlTree.graphViz());
    }

    @Test
    void testLeftRotation() {
        avlTree.add(10);
        avlTree.add(20);
        avlTree.add(30); // This should trigger a left rotation

        // Verifying the structure after left rotation
        String expectedStructure = "digraph G {\n20 -> 10;\n20 -> 30;\n}";
        assertEquals(expectedStructure, avlTree.graphViz());
    }

    @Test
    void testLeftRightRotation() {
        avlTree.add(30);
        avlTree.add(10);
        avlTree.add(20); // This should trigger a left-right rotation

        // Verifying the structure after left-right rotation
        String expectedStructure = "digraph G {\n20 -> 10;\n20 -> 30;\n}";
        assertEquals(expectedStructure, avlTree.graphViz());
    }

    @Test
    void testRightLeftRotation() {
        avlTree.add(10);
        avlTree.add(30);
        avlTree.add(20); // This should trigger a right-left rotation

        // Verifying the structure after right-left rotation
        String expectedStructure = "digraph G {\n20 -> 10;\n20 -> 30;\n}";
        assertEquals(expectedStructure, avlTree.graphViz());
    }

    @Test
    void testContains() {
        avlTree.add(10);
        avlTree.add(20);
        avlTree.add(30);
        assertTrue(avlTree.contains(10));
        assertTrue(avlTree.contains(20));
        assertTrue(avlTree.contains(30));
        assertFalse(avlTree.contains(40));
    }


}
