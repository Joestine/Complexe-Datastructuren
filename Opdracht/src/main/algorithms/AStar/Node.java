package main.algorithms.AStar;

public class Node<T> implements Comparable<Node<T>> {
    private final T vertex;
    private double distanceFromStart;
    private final double distanceToEnd;
    private Node<T> previous;

    public Node(T vertex, double distanceFromStart, double distanceToEnd, Node<T> previous) {
        this.vertex = vertex;
        this.distanceFromStart = distanceFromStart;
        this.distanceToEnd = distanceToEnd;
        this.previous = previous;
    }

    public T getVertex() {
        return vertex;
    }

    public double getDistanceFromStart() {
        return distanceFromStart;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    @Override
    public int compareTo(Node<T> node) {
        return Double.compare(this.distanceFromStart + this.distanceToEnd, node.distanceFromStart + node.distanceToEnd);
    }

    public void setPrevious(Node<T> node) {
        this.previous = node;
    }

    public void setDistanceFromStart(double tentativeDistance) {
        this.distanceFromStart = tentativeDistance;
    }
}
