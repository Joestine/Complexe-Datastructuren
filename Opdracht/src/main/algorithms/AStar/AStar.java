//package main.algorithms.AStar;
//
//import main.structures.graph.Edge;
//import main.structures.graph.Graph;
//import main.structures.hashtable.HashTable;
//import main.structures.heap.MinHeap;
//
//import java.util.*;
//import java.util.function.BiFunction;
//
//public class AStar<T extends Comparable<T>> {
//    private final BiFunction<T, T, Double> heuristicFunction;
//    private final BiFunction<T, T, Double> distanceFunction;
//
//    public AStar(BiFunction<T, T, Double> heuristicFunction, BiFunction<T, T, Double> distanceFunction) {
//        this.heuristicFunction = heuristicFunction;
//        this.distanceFunction = distanceFunction;
//    }
//
//    public LinkedList<T> findPath(Graph<T> graph, T start, T goal) {
//        HashTable<T, T> cameFrom = new HashTable<>();
//        HashTable<T, Double> gScore = new HashTable<>();
//        HashTable<T, Double> fScore = new HashTable<>();
//        MinHeap<Node<T>> openSet = new MinHeap<>();
//
//        gScore.put(start, 0.0);
//        fScore.put(start, heuristicFunction.apply(start, goal));
//        openSet.push(new Node<>(start, fScore.get(start)));
//
//        while (!openSet.isEmpty()) {
//            T current = openSet.pop().vertex;
//
//            if (current.equals(goal)) {
//                return reconstructPath(cameFrom, goal);
//            }
//
//            main.structures.linkedlist.Node<Edge<T>> edges = graph.getEdges(current).getHead();
//            while (edges != null) {
//                T neighbor = edges.getTarget();
//                double tentativeGScore = gScore.getOrDefault(current, Double.MAX_VALUE) + distanceFunction.apply(current, neighbor);
//
//                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
//                    cameFrom.put(neighbor, current);
//                    gScore.put(neighbor, tentativeGScore);
//                    fScore.put(neighbor, gScore.get(neighbor) + heuristicFunction.apply(neighbor, goal));
//
//                    Node<T> neighborNode = new Node<>(neighbor, fScore.get(neighbor));
//                    if (!openSet.contains(neighborNode)) {
//                        openSet.push(neighborNode);
//                    }
//                }
//                edges = edges.getNext();
//            }
//        }
//
//        return new LinkedList<>(); // Return empty list if no path found
//    }
//
//    private LinkedList<T> reconstructPath(HashTable<T, T> cameFrom, T current) {
//        LinkedList<T> path = new LinkedList<>();
//        while (current != null) {
//            path.addFirst(current);
//            current = cameFrom.get(current);
//        }
//        return path;
//    }
//}