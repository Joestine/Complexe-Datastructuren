package main.algorithms.dijkstra;

import main.structures.graph.Edge;
import main.structures.graph.Graph;
import main.structures.heap.MinHeap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra<T> {
    public Map<T, Double> dijkstra(Graph<T> graph, T root) {
        Map<T, Double> shortestDistances = new HashMap<>();
        Set<T> settled = new HashSet<>();
        MinHeap<Node<T>> minHeap = new MinHeap<>();

        for (T vertex : graph.getAdjacencyList().keySet()) {
            shortestDistances.put(vertex, Double.MAX_VALUE);
        }
        minHeap.push(new Node<>(root, 0.0));
        shortestDistances.put(root, 0.0);

        while (!minHeap.isEmpty()) {
            Node<T> currentNode = minHeap.pop();
            T currentVertex = currentNode.getVertex();
            settled.add(currentVertex);

            if (graph.getEdges(currentVertex) == null) {
                continue;
            }
            for (Edge<T> edge : graph.getEdges(currentVertex)) {
                T adjacentVertex = edge.getTarget();
                double distance = edge.getDistance();

                if (!settled.contains(adjacentVertex)) {
                    double newDistance = shortestDistances.get(currentVertex) + distance;
                    if (newDistance < shortestDistances.get(adjacentVertex)) {
                        shortestDistances.put(adjacentVertex, newDistance);
                        minHeap.push(new Node<>(adjacentVertex, newDistance));
                    }
                }
            }
        }
        return shortestDistances;
    }
}
