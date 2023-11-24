package main.structures.graph;

public class Edge<T> implements Comparable<Edge<T>> {
    private final T target;
    private final double distance;

    public Edge(T target, double distance) {
        this.target = target;
        this.distance = distance;
    }

    public T getTarget() {
        return target;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Edge<T> o) {
        return Double.compare(this.distance, o.distance);
    }
}
