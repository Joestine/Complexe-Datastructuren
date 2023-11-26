package test;

import main.algorithms.aStar.AStar;
import main.structures.graph.Graph;
import main.structures.linkedlist.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class AStarTest {
    private Graph<String> graph;
    private AStar<String> aStarAlgorithm;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
        aStarAlgorithm = new AStar<>();
    }

    @Test
    void testAStarAlgorithm() {
        // Setup a simple graph
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("B", "C", 2.0);
        graph.addEdge("C", "D", 3.0);
        graph.addEdge("A", "D", 10.0);

        Function<String, Double> heuristic = node -> 0.0;

        LinkedList<String> path = aStarAlgorithm.aStar(graph, "A", "D", heuristic);

        assertNotNull(path);
        assertEquals(4, path.size());
        assertEquals("A", path.get(0));
        assertEquals("D", path.get(3));
    }

}
