package tests;

import main.structures.graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    Graph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new Graph<>();
    }

    @Test
    void testGraphInitialization() {
        assertTrue(graph.getAdjacencyList().isEmpty());
    }

    @Test
    void testAddVertex() {
        graph.addVertex("A");
        assertTrue(graph.getAdjacencyList().containsKey("A"));
    }

    @Test
    void testAddDuplicateVertex() {
        graph.addVertex("A");
        graph.addVertex("A");
        assertEquals(1, graph.getAdjacencyList().size());
    }

    @Test
    void testAddEdgeNewVertices() {
        graph.addEdge("A", "B", 1.0);
        assertTrue(graph.getAdjacencyList().containsKey("A"));
        assertTrue(graph.getAdjacencyList().containsKey("B"));
    }

    @Test
    void testAddEdgeExistingVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 1.0);
        assertEquals(1, graph.getEdges("A").size());
    }

    @Test
    void testAddEdgeDuplicateVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("A", "B", 2.0);
        assertEquals(2, graph.getEdges("A").size());
        assertEquals(0, graph.getEdges("B").size());
    }

    @Test
    void testRemoveExistingVertex() {
        graph.addVertex("A");
        graph.removeVertex("A");
        assertTrue(graph.getAdjacencyList().isEmpty());
    }

    @Test
    void testRemoveNonExistingVertex() {
        graph.addVertex("A");
        graph.removeVertex("B");
        assertEquals(1, graph.getAdjacencyList().size());
    }

    @Test
    void testRemoveExistingEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 1.0);
        graph.removeEdge("A", "B");
        assertTrue(graph.getEdges("A").isEmpty());
        assertTrue(graph.getEdges("B").isEmpty());
    }

    @Test
    void testRemoveNonExistingEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 1.0);
        graph.removeEdge("B", "A");
        assertEquals(1, graph.getEdges("A").size());
        assertEquals(0, graph.getEdges("B").size());
    }

    @Test
    void testBFSWithValidCondition() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("A", "C", 1.0);
        graph.addEdge("B", "D", 1.0);
        graph.addEdge("C", "D", 1.0);

        Predicate<String> condition = vertex -> vertex.equals("D");
        assertEquals(1, graph.breadthFirstSearchWithCondition("A", condition).size());
    }

    @Test
    void testBFSWithInvalidCondition() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("A", "C", 1.0);
        graph.addEdge("B", "D", 1.0);
        graph.addEdge("C", "D", 1.0);

        Predicate<String> condition = vertex -> vertex.equals("E");
        assertEquals(0, graph.breadthFirstSearchWithCondition("A", condition).size());
    }

    @Test
    void testBFSWithEmptyGraph() {
        Predicate<String> condition = vertex -> vertex.equals("A");
        assertEquals(0, graph.breadthFirstSearchWithCondition("A", condition).size());
    }

    @Test
    void testBFSWithEmptyCondition() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("A", "C", 1.0);
        graph.addEdge("B", "D", 1.0);
        graph.addEdge("C", "D", 1.0);

        Predicate<String> condition = vertex -> vertex.equals("");
        assertEquals(0, graph.breadthFirstSearchWithCondition("A", condition).size());
    }

    @Test
    void testBFSWithNullCondition() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("A", "C", 1.0);
        graph.addEdge("B", "D", 1.0);
        graph.addEdge("C", "D", 1.0);

        assertThrows(NullPointerException.class, () -> graph.breadthFirstSearchWithCondition("A", null));
    }

    @Test
    void testBFSWithNullStart() {
        Predicate<String> condition = vertex -> vertex.equals("A");
        assertThrows(NullPointerException.class, () -> graph.breadthFirstSearchWithCondition(null, condition));
    }

    @Test
    void testGetEdgesWithExistingVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 1.0);
        assertEquals(1, graph.getEdges("A").size());
    }

    @Test
    void testGetEdgesWithNonExistingVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 1.0);
        assertEquals(0, graph.getEdges("C").size());
    }

    @Test
    void testGetAdjecencyList() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 1.0);
        assertEquals(2, graph.getAdjacencyList().size());
    }
}
