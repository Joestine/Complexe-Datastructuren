package main.algorithms.dijkstra;

public class Node<T> implements Comparable<Node<T>> {
    private final T vertex;
    private final double distance;

    public Node(T vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public T getVertex() {
        return vertex;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.distance, other.distance);
    }
}