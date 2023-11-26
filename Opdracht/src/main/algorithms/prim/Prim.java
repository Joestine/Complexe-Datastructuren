package main.algorithms.prim;

import main.structures.graph.Edge;
import main.structures.graph.Graph;
import main.structures.hashtable.HashTable;
import main.structures.heap.MinHeap;
import main.structures.linkedlist.LinkedList;
import main.structures.linkedlist.Node;

import java.util.HashSet;
import java.util.Set;

public class Prim<T extends Comparable<T>> {
    public HashTable<T, Edge<T>> prim(Graph<T> graph, T start) {
        HashTable<T, LinkedList<Edge<T>>> adjacencyList = graph.getAdjacencyList();
        HashTable<T, Edge<T>> result = new HashTable<>();
        HashTable<T, PrimNode<T>> nodeTable = new HashTable<>();
        MinHeap<PrimNode<T>> heap = new MinHeap<>();
        Set<T> inResult = new HashSet<>();

        for (T node : adjacencyList.keySet()) {
            PrimNode<T> primNode = new PrimNode<>(node, null, node.equals(start) ? 0.0 : Double.MAX_VALUE);
            nodeTable.put(node, primNode);
            heap.push(primNode);
        }

        while (!heap.isEmpty()) {
            PrimNode<T> current = heap.pop();
            T currentNode = current.getNode();
            inResult.add(currentNode);

            if (current.getPrevious() != null) {
                result.put(currentNode, new Edge<>(currentNode, current.getPrevious(), current.getWeight()));
            }

            Node<Edge<T>> currentEdge = adjacencyList.get(currentNode).getHead();
            while (currentEdge != null) {
                T destination = currentEdge.getData().destination();
                if (!inResult.contains(destination) && nodeTable.get(destination).getWeight() > currentEdge.getData().weight()) {
                    PrimNode<T> destinationInfo = nodeTable.get(destination);
                    heap.remove(destinationInfo);
                    destinationInfo = new PrimNode<>(destination, currentNode, currentEdge.getData().weight());
                    nodeTable.put(destination, destinationInfo);
                    heap.push(destinationInfo);
                }
                currentEdge = currentEdge.getNext();
            }
        }
        return result;
    }
}
