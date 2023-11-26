package main.algorithms.dijkstra;

public class DijkstraNode<T extends Comparable<T>> implements Comparable<DijkstraNode<T>> {
    private final T node;
    private final double distance;

    public DijkstraNode(T node, double distance) {
        this.node = node;
        this.distance = distance;
    }

    public T getNode() {
        return node;
    }

    @Override
    public int compareTo(DijkstraNode<T> other) {
        return Double.compare(this.distance, other.distance);
    }
}
