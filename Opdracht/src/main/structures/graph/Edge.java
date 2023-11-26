package main.structures.graph;

public record Edge<T>(T source, T destination, double weight) implements Comparable<Edge<T>> {

    @Override
    public int compareTo(Edge<T> o) {
        return Double.compare(weight, o.weight);
    }
}
