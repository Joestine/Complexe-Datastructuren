package main.structures.graph;

import main.structures.hashtable.HashTable;
import main.structures.hashtable.Node;
import main.structures.linkedlist.LinkedList;

public class Graph<T extends Comparable<T>> {
    private final HashTable<T, LinkedList<Edge<T>>> adjacencyList;

    public Graph() {
        adjacencyList = new HashTable<>();
    }

    public void addVertex(T vertex) {
        if (!adjacencyList.contains(vertex)) {
            adjacencyList.put(vertex, new LinkedList<>());
        }
    }

    public void addEdge(T vertex1, T vertex2, double distance) {
        addVertex(vertex1);
        addVertex(vertex2);
        adjacencyList.get(vertex1).add(new Edge<>(vertex2, distance));
    }

    public void removeVertex(T vertex) {
        for (Node<T, LinkedList<Edge<T>>> bucket : adjacencyList.getBuckets()) {
            Node<T, LinkedList<Edge<T>>> currentBucket = bucket;
            while (currentBucket != null) {
                removeEdgeFromLinkedList(currentBucket.value, vertex);
                currentBucket = currentBucket.next;
            }
        }
        adjacencyList.remove(vertex);
    }

    private void removeEdgeFromLinkedList(LinkedList<Edge<T>> list, T vertex) {
        if (list != null) {
            main.structures.linkedlist.Node<Edge<T>> current = list.getHead();
            while (current != null && current.getNext() != null) {
                if (current.getNext().getData().getTarget().equals(vertex)) {
                    current.setNext(current.getNext().getNext());
                } else {
                    current = current.getNext();
                }
            }
            if (list.getHead() != null && list.getHead().getData().getTarget().equals(vertex)) {
                list.removeFirst();
            }
        }
    }

    public void removeEdge(T vertex1, T vertex2) {
        LinkedList<Edge<T>> edges = adjacencyList.get(vertex1);
        if (edges != null) {
            main.structures.linkedlist.Node<Edge<T>> current = edges.getHead();
            while (current != null && current.getNext() != null) {
                if (current.getNext().getData().getTarget().equals(vertex2)) {
                    current.setNext(current.getNext().getNext());
                } else {
                    current = current.getNext();
                }
            }
            if (edges.getHead() != null && edges.getHead().getData().getTarget().equals(vertex2)) {
                edges.removeFirst();
            }
        }
    }

    public LinkedList<Edge<T>> getEdges(T vertex) {
        return adjacencyList.getOrDefault(vertex, new LinkedList<>());
    }

    public HashTable<T, LinkedList<Edge<T>>> getAdjacencyList() {
        return adjacencyList;
    }

    public String graphViz() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Node<T, LinkedList<Edge<T>>> bucket : adjacencyList.getBuckets()) {
            Node<T, LinkedList<Edge<T>>> currentBucket = bucket;
            while (currentBucket != null) {
                main.structures.linkedlist.Node<Edge<T>> current = currentBucket.value.getHead();
                while (current != null) {
                    sb.append("\t").append(currentBucket.key).append(" -- ").append(current.getData().getTarget()).append(" [label=\"").append(current.getData().getDistance()).append("\"];\n");
                    current = current.getNext();
                }
                currentBucket = currentBucket.next;
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
