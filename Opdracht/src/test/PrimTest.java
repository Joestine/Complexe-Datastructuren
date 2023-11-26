package test;

import main.algorithms.prim.Prim;
import main.structures.graph.Edge;
import main.structures.graph.Graph;
import main.structures.hashtable.HashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrimTest {
    private Graph<String> graph;
    private Prim<String> primAlgorithm;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
        primAlgorithm = new Prim<>();
    }

    @Test
    void testPrimAlgorithm() {
        // Setup a simple graph
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("B", "C", 2.0);
        graph.addEdge("C", "A", 3.0);

        HashTable<String, Edge<String>> mst = primAlgorithm.prim(graph, "A");

        assertEquals(2, mst.size());
        assertNotNull(mst.get("B"));
        assertEquals(1.0, mst.get("B").weight());
        assertNotNull(mst.get("C"));
        assertEquals(2.0, mst.get("C").weight());
    }
}
