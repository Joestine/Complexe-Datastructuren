package main.algorithms.prim;

public class PrimNode<T extends Comparable<T>> implements Comparable<PrimNode<T>> {
    private final T node;
    private final T previous;
    private final double weight;

    public PrimNode(T vertex, T predecessor, double weight) {
        this.node = vertex;
        this.previous = predecessor;
        this.weight = weight;
    }

    public T getNode() {
        return node;
    }

    public T getPrevious() {
        return previous;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(PrimNode<T> other) {
        return Double.compare(this.weight, other.weight);
    }
}
