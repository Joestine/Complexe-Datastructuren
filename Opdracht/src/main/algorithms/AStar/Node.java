package main.algorithms.AStar;

public class Node<T> implements Comparable<Node<T>> {
    T vertex;
    double fScore;

    Node(T vertex, double fScore) {
        this.vertex = vertex;
        this.fScore = fScore;
    }

    @Override
    public int compareTo(Node<T> other) {
        return Double.compare(this.fScore, other.fScore);
    }
}