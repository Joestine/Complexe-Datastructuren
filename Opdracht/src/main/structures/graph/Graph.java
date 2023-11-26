package main.structures.graph;

import main.structures.hashtable.HashTable;
import main.structures.linkedlist.LinkedList;
import main.structures.linkedlist.Node;
import main.tools.FormatForGraphViz;

public class Graph<T extends Comparable<T>> {
    private final HashTable<T, LinkedList<Edge<T>>> adjacencyList;

    public Graph() {
        adjacencyList = new HashTable<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.put(vertex, new LinkedList<>());
    }

    public void addEdge(T source, T destination, double weight) {
        if (!adjacencyList.contains(source)) {
            addVertex(source);
        }

        if (!adjacencyList.contains(destination)) {
            addVertex(destination);
        }

        adjacencyList.get(source).add(new Edge<>(source, destination, weight));
    }

    public String toString() {
        StringBuilder graphviz = new StringBuilder();
        graphviz.append("Graph {\n");
        for (T node : adjacencyList.keySet()) {
            Node<Edge<T>> current = adjacencyList.get(node).getHead();
            while (current != null) {
                graphviz.append("\t");
                graphviz.append(node.toString());
                graphviz.append(" --> ");
                graphviz.append(current.getData().destination().toString());
                graphviz.append(" [distance=\"");
                graphviz.append(current.getData().weight());
                graphviz.append("\"];\n");
                current = current.getNext();
            }
        }
        graphviz.append("}");
        return graphviz.toString();
    }

    public String graphViz() {
        StringBuilder graphviz = new StringBuilder();
        graphviz.append("digraph G {\n");
        for (T node : adjacencyList.keySet()) {
            Node<Edge<T>> current = adjacencyList.get(node).getHead();
            while (current != null) {
                graphviz.append("\t");
                graphviz.append(FormatForGraphViz.formatForGraphviz(node.toString()));
                graphviz.append(" -> ");
                graphviz.append(FormatForGraphViz.formatForGraphviz(current.getData().destination().toString()));
                graphviz.append(" [label=\"");
                graphviz.append(current.getData().weight());
                graphviz.append("\"];\n");
                current = current.getNext();
            }
        }
        graphviz.append("}");
        return graphviz.toString();
    }

    public HashTable<T, LinkedList<Edge<T>>> getAdjacencyList() {
        return adjacencyList;
    }
}
