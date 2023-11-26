package test;

import main.algorithms.dijkstra.Dijkstra;
import main.structures.graph.Graph;
import main.structures.hashtable.HashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTest {
    private Graph<String> graph;
    private Dijkstra<String> dijkstraAlgorithm;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
        dijkstraAlgorithm = new Dijkstra<>();
    }

    @Test
    void testDijkstraAlgorithm() {
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("B", "C", 2.0);
        graph.addEdge("C", "A", 3.0);

        HashTable<String, Double> shortestPaths = dijkstraAlgorithm.dijkstra(graph, "A");

        assertEquals(3, shortestPaths.size());
        assertEquals(0.0, shortestPaths.get("A"));
        assertEquals(1.0, shortestPaths.get("B"));
        assertEquals(3.0, shortestPaths.get("C"));
    }

}
