package main.algorithms.aStar;

import main.structures.graph.Edge;
import main.structures.graph.Graph;
import main.structures.hashtable.HashTable;
import main.structures.heap.MinHeap;
import main.structures.linkedlist.LinkedList;
import main.structures.linkedlist.Node;

import java.util.function.Function;

public class AStar<T extends Comparable<T>> {
    public LinkedList<T> aStar(Graph<T> graph, T source, T destination, Function<T, Double> heuristic) {
        HashTable<T, Double> costSoFar = new HashTable<>();
        HashTable<T, T> cameFrom = new HashTable<>();
        MinHeap<AStarNode<T>> heap = new MinHeap<>();
        HashTable<T, LinkedList<Edge<T>>> adjacencyList = graph.getAdjacencyList();

        for (T node : adjacencyList.keySet()) {
            costSoFar.put(node, Double.MAX_VALUE);
        }
        costSoFar.put(source, 0.0);
        heap.push(new AStarNode<>(source, null, 0.0, heuristic.apply(source)));

        while (!heap.isEmpty()) {
            AStarNode<T> current = heap.pop();
            T currentNode = current.getNode();

            if (currentNode.equals(destination)) {
                break;
            }

            Node<Edge<T>> currentEdge = adjacencyList.get(currentNode).getHead();
            while (currentEdge != null) {
                T nextNode = currentEdge.getData().destination();
                double newCost = current.getCost() + currentEdge.getData().weight();

                if (newCost < costSoFar.getOrDefault(nextNode, Double.MAX_VALUE)) {
                    costSoFar.put(nextNode, newCost);
                    cameFrom.put(nextNode, currentNode);
                    heap.push(new AStarNode<>(nextNode, currentNode, newCost, newCost + heuristic.apply(nextNode)));
                }
                currentEdge = currentEdge.getNext();
            }
        }
        return reconstructPath(cameFrom, source, destination);
    }

    private LinkedList<T> reconstructPath(HashTable<T, T> cameFrom, T source, T destination) {
        LinkedList<T> path = new LinkedList<>();
        T current = destination;

        while (!current.equals(source)) {
            path.addFirst(current);
            current = cameFrom.get(current);
        }
        path.addFirst(source);

        return path;
    }
}
