package main.structures.graph;

import main.algorithms.AStar.Node;

import java.util.*;
import java.util.function.Predicate;

public class Graph<T> {
    private final Map<T, Set<Edge<T>>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.put(vertex, new HashSet<>());
    }

    public void addEdge(T vertex1, T vertex2, double distance) {
        if (!adjacencyList.containsKey(vertex1)) {
            addVertex(vertex1);
        }
        if (!adjacencyList.containsKey(vertex2)) {
            addVertex(vertex2);
        }

        adjacencyList.get(vertex1).add(new Edge<>(vertex2, distance));
    }

    public void removeVertex(T vertex) {
        adjacencyList.values().forEach(e -> e.remove(vertex));
        adjacencyList.remove(vertex);
    }

    public void removeEdge(T vertex1, T vertex2) {
        if (adjacencyList.containsKey(vertex1)) {
            adjacencyList.get(vertex1).removeIf(edge -> edge.getTarget().equals(vertex2));
        }
    }

    public List<T> breadthFirstSearchWithCondition(T start, Predicate<T> condition) {
        if (start == null || condition == null) {
            throw new NullPointerException("Start vertex or condition cannot be null");
        }
        if (adjacencyList.isEmpty()) {
            return Collections.emptyList();
        }

        List<T> matchingNodes = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            if (condition.test(vertex)) {
                matchingNodes.add(vertex);
            }
            for (Edge<T> edge : adjacencyList.get(vertex)) {
                if (!visited.contains(edge.getTarget())) {
                    queue.add(edge.getTarget());
                    visited.add(edge.getTarget());
                }
            }
        }
        return matchingNodes;
    }

    public Set<Edge<T>> getEdges(T vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptySet());
    }

    public Map<T, Set<Edge<T>>> getAdjacencyList() {
        return adjacencyList;
    }
}
