package main.algorithms.AStar;

import main.structures.graph.Edge;
import main.structures.graph.Graph;
import main.structures.heap.MinHeap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class AStar<T> {
    public List<T> aStar(Graph<T> graph, T start, T end, Function<T, Double> heuristic) {
        Map<T, Node<T>> allNodes = new HashMap<>();
        MinHeap<Node<T>> minHeap = new MinHeap<>();

        Node<T> startNode = new Node<>(start, 0.0, heuristic.apply(start), null);
        allNodes.put(start, startNode);
        minHeap.insert(startNode);

        while (!minHeap.isEmpty()) {
            Node<T> currentNode = minHeap.delete();

            if (currentNode.getVertex().equals(end)) {
                return reconstructPath(currentNode);
            }

            for (Edge<T> edge : graph.getEdges(currentNode.getVertex())) {
                T adjacentVertex = edge.getTarget();
                double tentativeDistance = currentNode.getDistanceFromStart() + edge.getDistance();

                Node<T> adjacentNode = allNodes.computeIfAbsent(
                        adjacentVertex,
                        vertex -> new Node<>(vertex, Double.MAX_VALUE, heuristic.apply(vertex), null)
                );

                if (tentativeDistance < adjacentNode.getDistanceFromStart()) {
                    adjacentNode.setDistanceFromStart(tentativeDistance);
                    adjacentNode.setPrevious(currentNode);
                    if (!minHeap.contains(adjacentNode)) {
                        minHeap.insert(adjacentNode);
                    }
                }
            }
        }
        return null;
    }

    private List<T> reconstructPath(Node<T> node) {
        LinkedList<T> path = new LinkedList<>();
        while (node != null) {
            path.addFirst(node.getVertex());
            node = node.getPrevious();
        }
        return path;
    }
}
