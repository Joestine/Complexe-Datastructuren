package test;

import main.structures.graph.Graph;
import main.structures.graph.Edge;
import main.structures.hashtable.HashTable;
import main.structures.linkedlist.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    private Graph<String> graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    void testAddVertex() {
        graph.addVertex("A");
        HashTable<String, LinkedList<Edge<String>>> adjList = graph.getAdjacencyList();
        assertTrue(adjList.contains("A"));
        assertTrue(adjList.get("A").isEmpty());
    }

    @Test
    void testAddEdge() {
        graph.addEdge("A", "B", 1.0);
        HashTable<String, LinkedList<Edge<String>>> adjList = graph.getAdjacencyList();
        assertFalse(adjList.get("A").isEmpty());
        assertEquals("B", adjList.get("A").getHead().getData().destination());
        assertEquals(1.0, adjList.get("A").getHead().getData().weight());
    }

    @Test
    void testGraphViz() {
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("B", "C", 2.0);

        String expectedViz = "Graph {\n\tA -> B [distance=\"1.0\"];\n\tB -> C [distance=\"2.0\"];\n}";
        assertEquals(expectedViz, graph.graphViz());
    }

}
