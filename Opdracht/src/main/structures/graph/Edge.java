package main.structures.graph;

public class Edge<T> {
    private final T target;
    private final double distance;

    public Edge(T target, double weight) {
        this.target = target;
        this.distance = weight;
    }

    public T getTarget() {
        return target;
    }

    public double getDistance() {
        return distance;
    }
}
