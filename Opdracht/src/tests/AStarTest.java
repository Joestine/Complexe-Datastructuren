package tests;

import main.algorithms.AStar.AStar;
import main.structures.graph.Graph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AStarTest {
    @Test
    void testSimplePath() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2, 1.0);
        graph.addEdge(2, 3, 1.0);
        Function<Integer, Double> heuristic = vertex -> {
            if (vertex == 3) {
                return 0.0;
            }
            return Double.MAX_VALUE;
        };
        AStar<Integer> aStar = new AStar<>();
        List<Integer> result = aStar.aStar(graph, 1, 3, heuristic);
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    void testNoPath() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2, 1.0);
        graph.addEdge(2, 3, 1.0);
        Function<Integer, Double> heuristic = vertex -> {
            if (vertex == 3) {
                return 0.0;
            }
            return Double.MAX_VALUE;
        };
        AStar<Integer> aStar = new AStar<>();
        List<Integer> result = aStar.aStar(graph, 1, 4, heuristic);
        assertNull(result);
    }

    @Test
    void testSingleNodeGraph() {
        Graph<Integer> graph = new Graph<>();
        Function<Integer, Double> heuristic = vertex -> {
            if (vertex == 1) {
                return 0.0;
            }
            return Double.MAX_VALUE;
        };
        AStar<Integer> aStar = new AStar<>();
        List<Integer> result = aStar.aStar(graph, 1, 1, heuristic);
        assertEquals(List.of(1), result);
    }

    @Test
    void testHeuristicImpact() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2, 1.0);
        graph.addEdge(2, 3, 1.0);
        Function<Integer, Double> heuristic = vertex -> {
            if (vertex == 3) {
                return 100.0;
            }
            return Double.MAX_VALUE;
        };
        AStar<Integer> aStar = new AStar<>();
        List<Integer> result = aStar.aStar(graph, 1, 3, heuristic);
        assertEquals(Arrays.asList(1, 2, 3), result);

        heuristic = vertex -> {
            if (vertex == 3) {
                return 0.0;
            }
            return Double.MAX_VALUE;
        };
        result = aStar.aStar(graph, 1, 3, heuristic);
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    void testComplexPath() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2, 1.0);
        graph.addEdge(1, 3, 2.0);
        graph.addEdge(2, 4, 1.0);
        graph.addEdge(3, 4, 1.0);
        graph.addEdge(4, 5, 1.0);
        Function<Integer, Double> heuristic = vertex -> {
            if (vertex == 5) {
                return 0.0;
            }
            return Double.MAX_VALUE;
        };
        AStar<Integer> aStar = new AStar<>();
        List<Integer> result = aStar.aStar(graph, 1, 5, heuristic);
        assertEquals(Arrays.asList(1, 2, 4, 5), result);
    }
}
