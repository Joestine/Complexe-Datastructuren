package tests;

import main.algorithms.dijkstra.Dijkstra;
import main.structures.graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DijkstraTest {
    private Dijkstra<Integer> dijkstra;

    @BeforeEach
    void setUp() {
        dijkstra = new Dijkstra<>();
    }

    @Test
    void testSimpleGraph() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 1.0);
        graph.addEdge(2, 3, 2.0);
        graph.addEdge(1, 3, 4.0);

        var result = dijkstra.dijkstra(graph, 1);

        assertEquals(0.0, result.get(1));
        assertEquals(1.0, result.get(2));
        assertEquals(3.0, result.get(3));
    }

    @Test
    void testGraphWithDisconnectedNodes() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2, 1.0);
        graph.addVertex(3);

        var result = dijkstra.dijkstra(graph, 1);

        assertEquals(0.0, result.get(1));
        assertEquals(1.0, result.get(2));
        assertEquals(Double.MAX_VALUE, result.get(3));
    }

    @Test
    void testGraphWithNoEdges() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);

        var result = dijkstra.dijkstra(graph, 1);

        assertEquals(0.0, result.get(1));
        assertEquals(Double.MAX_VALUE, result.get(2));
    }

    @Test
    void testGraphWithSingleNode() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);

        var result = dijkstra.dijkstra(graph, 1);

        assertEquals(0.0, result.get(1));
    }

    @Test
    void testInvalidInput() {
        Graph<Integer> graph = null;

        assertThrows(NullPointerException.class, () -> dijkstra.dijkstra(graph, 1));
    }

    @Test
    void testComplexGraph() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2, 1.0);
        graph.addEdge(2, 3, 2.0);
        graph.addEdge(1, 3, 10.0);
        graph.addEdge(3, 4, 1.0);

        var result = dijkstra.dijkstra(graph, 1);

        assertEquals(0.0, result.get(1));
        assertEquals(1.0, result.get(2));
        assertEquals(3.0, result.get(3));
        assertEquals(4.0, result.get(4));
    }
}
