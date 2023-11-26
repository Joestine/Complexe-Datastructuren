package main.algorithms.dijkstra;

import main.structures.graph.Edge;
import main.structures.graph.Graph;
import main.structures.hashtable.HashTable;
import main.structures.heap.MinHeap;
import main.structures.linkedlist.LinkedList;
import main.structures.linkedlist.Node;

public class Dijkstra<T extends Comparable<T>> {
    public HashTable<T, Double> dijkstra(Graph<T> graph, T start) {
        HashTable<T, Double> distances = new HashTable<>();
        MinHeap<DijkstraNode<T>> heap = new MinHeap<>();

        HashTable<T, LinkedList<Edge<T>>> adjacencyList = graph.getAdjacencyList();

        for (T node : adjacencyList.keySet()) {
            distances.put(node, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);
        heap.push(new DijkstraNode<>(start, 0.0));

        while (!heap.isEmpty()) {
            DijkstraNode<T> current = heap.pop();
            T node = current.getNode();

            Node<Edge<T>> currentNode = adjacencyList.get(node).getHead();
            while (currentNode != null) {
                Edge<T> edge = currentNode.getData();
                T destination = edge.destination();
                double newDist = distances.get(node) + edge.weight();
                if (newDist < distances.get(destination)) {
                    distances.put(destination, newDist);
                    heap.push(new DijkstraNode<>(destination, newDist));
                }
                currentNode = currentNode.getNext();
            }
        }
        return distances;
    }
}
