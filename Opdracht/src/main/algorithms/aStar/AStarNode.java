package main.algorithms.aStar;

public class AStarNode<T extends Comparable<T>> implements Comparable<AStarNode<T>> {
    private final T node;
    private final T previous;
    private final double cost;
    private final double estimatedTotalCost;

    public AStarNode(T vertex, T previous, double cost, double estimatedTotalCost) {
        this.node = vertex;
        this.previous = previous;
        this.cost = cost;
        this.estimatedTotalCost = estimatedTotalCost;
    }

    public T getNode() {
        return node;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public int compareTo(AStarNode<T> other) {
        return Double.compare(this.estimatedTotalCost, other.estimatedTotalCost);
    }
}
