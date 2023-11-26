package main;

import main.algorithms.aStar.AStar;
import main.algorithms.dijkstra.Dijkstra;
import main.algorithms.prim.Prim;
import main.models.Connection;
import main.models.Station;
import main.sorts.MergeSorter;
import main.sorts.SelectionSorter;
import main.structures.graph.Edge;
import main.structures.graph.Graph;
import main.structures.hashtable.HashTable;
import main.structures.linkedlist.LinkedList;
import main.structures.linkedlist.Node;
import main.structures.tree.AVLTree;
import main.tools.ConnectionReader;
import main.tools.StationReader;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;


public class RailwayManager {
    private final main.structures.linkedlist.LinkedList<Station> stations;
    private final main.structures.linkedlist.LinkedList<Connection> connections;
    private final AVLTree<Station> tree;
    private final Graph<Station> graph;

    public RailwayManager() throws IOException {
        stations = new main.structures.linkedlist.LinkedList<>();
        StationReader.readStations("resources/stations.csv").forEach(stations::add);

        connections = new main.structures.linkedlist.LinkedList<>();
        ConnectionReader.readConnections("resources/tracks.csv").forEach(connections::add);

        tree = new AVLTree<>();
        Node<Station> current = stations.getHead();
        while (current != null) {
            tree.add(current.getData());
            current = current.getNext();
        }

        graph = new Graph<>();

        current = stations.getHead();
        while (current != null) {
            graph.addVertex(current.getData());
            current = current.getNext();
        }

        current = stations.getHead();
        while (current != null) {
            Node<Connection> currentConnection = connections.getHead();
            while (currentConnection != null) {
                Station station1 = findStationByCode(currentConnection.getData().getStart());
                if (station1 == null) {
                    currentConnection = currentConnection.getNext();
                    continue;
                }
                if (station1.equals(current.getData())) {
                    Station station2 = findStationByCode(currentConnection.getData().getEnd());
                    if (station2 != null) {
                        graph.addEdge(station1, station2, currentConnection.getData().getDistance());
                    }
                }
                currentConnection = currentConnection.getNext();
            }
            current = current.getNext();
        }
    }

    public void run() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("\n\n");
            System.out.println("[1] LINEAR SEARCH OP ARRAYLIST VAN STATIONS");
            System.out.println("[2] BINARY SEARCH OP ARRAYLIST VAN STATIONS");
            System.out.println("[3] SELECTION SORT OP ARRAYLIST VAN CONNECTIONS");
            System.out.println("[4] MERGE SORT OP ARRAYLIST VAN CONNECTIONS");
            System.out.println("[5] ZOEKEN IN AVL TREE VAN STATIONS");
            System.out.println("[6] DIJKSTRA OP GRAAF VAN STATIONS");
            System.out.println("[7] A* OP GRAAF VAN STATIONS");
            System.out.println("[8] PRIM OP GRAAF VAN STATIONS");
            System.out.println("[9] PRINT GRAPHVIZ VAN GRAAF VAN STATIONS");
            System.out.println("[10] PRINT GRAPHVIZ VAN AVL TREE VAN STATIONS");


            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> linearSearchStations();
                case 2 -> binarySearchStations();
                case 3 -> selectionSortConnections();
                case 4 -> mergeSortConnections();
                case 5 -> searchTree();
                case 6 -> runDijkstra();
                case 7 -> runAStar();
                case 8 -> runPrim();
                case 9 -> System.out.println(graph.graphViz());
                case 10 -> System.out.println(tree.graphViz());

                default -> running = false;
            }
        }
    }

    private Station findStationByCode(String code) {
        Node<Station> current = stations.getHead();
        while (current != null) {
            if (current.getData().getCode().equalsIgnoreCase(code)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    private void linearSearchStations() {
        Station station = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geef de naam van het station: ");
        String name = scanner.nextLine();

        long startTime = System.nanoTime();
        Node<Station> current = stations.getHead();
        while (current != null) {
            if (current.getData().getNameShort().equals(name)) {
                station = current.getData();
                break;
            }
            current = current.getNext();
        }
        long endTime = System.nanoTime();

        if (station == null) {
            System.out.println("Station niet gevonden");
        } else {
            System.out.println("Station gevonden: " + station);
        }

        System.out.println("Tijd: " + (endTime - startTime) + " ns\n\n");
    }

    private void binarySearchStations() {
        Station station = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geef de naam van het station: ");
        String name = scanner.nextLine();

        long startTime = System.nanoTime();
        int low = 0;
        int high = stations.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (stations.get(mid).getNameShort().equals(name)) {
                low = mid + 1;
            } else if (stations.get(mid).getNameShort().equals(name)) {
                high = mid - 1;
            } else {
                station = stations.get(mid);
                break;
            }
        }
        long endTime = System.nanoTime();

        if (station == null) {
            System.out.println("Station niet gevonden");
        } else {
            System.out.println("Station gevonden: " + station);
        }

        System.out.println("Tijd: " + (endTime - startTime) + " ns\n\n");
    }

    private void selectionSortConnections() {
        SelectionSorter<Connection> sorter = new SelectionSorter<>();

        long startTime = System.nanoTime();
        LinkedList<Connection> sortedConnections = sorter.sort(connections);
        long endTime = System.nanoTime();

        System.out.println("Originele lijst: " + connections);
        System.out.println("Gesorteerde lijst: " + sortedConnections);
        System.out.println("Tijd: " + (endTime - startTime) + " ns\n\n");
    }

    private void mergeSortConnections() {
        MergeSorter<Connection> sorter = new MergeSorter<>();

        long startTime = System.nanoTime();
        LinkedList<Connection> sortedConnections = sorter.sort(connections);
        long endTime = System.nanoTime();

        System.out.println("Originele lijst: " + connections);
        System.out.println("Gesorteerde lijst: " + sortedConnections);
        System.out.println("Tijd: " + (endTime - startTime) + " ns\n\n");
    }

    private void searchTree() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geef de naam van het station: ");
        String name = scanner.nextLine();
        Function<Station, String> propertyExtractor = Station::getNameShort;

        long startTime = System.nanoTime();
        Station station = tree.get(name, propertyExtractor);
        long endTime = System.nanoTime();

        if (station == null) {
            System.out.println("Station niet gevonden");
        } else {
            System.out.println("Station gevonden: " + station);
        }

        System.out.println("Tijd: " + (endTime - startTime) + " ns\n\n");
    }

    private void runDijkstra() {
        Dijkstra<Station> dijkstra = new Dijkstra<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geef de code van het startstation: ");
        String code = scanner.nextLine();

        long startTime = System.nanoTime();
        HashTable<Station, Double> distances = dijkstra.dijkstra(graph, findStationByCode(code));
        long endTime = System.nanoTime();

        System.out.println("Afstanden: " + distances);
        System.out.println("Tijd: " + (endTime - startTime) + " ns\n\n");
    }

    private void runAStar() {
        AStar<Station> aStar = new AStar<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geef de code van het startstation: ");
        Station start = findStationByCode(scanner.nextLine());
        System.out.println("Geef de code van het eindstation: ");
        Station end = findStationByCode(scanner.nextLine());

        long startTime = System.nanoTime();
        Function<Station, Double> heuristic = (station) -> {
            double latitude = station.getGeolocation().latitude();
            double longitude = station.getGeolocation().longitude();
            assert end != null;
            return Math.sqrt(Math.pow(latitude - end.getGeolocation().latitude(), 2) + Math.pow(longitude - end.getGeolocation().longitude(), 2));
        };
        LinkedList<Station> path = aStar.aStar(graph, start, end, heuristic);
        long endTime = System.nanoTime();

        System.out.println("Pad vanaf " + start + " naar " + end + ":\n " + path);
        System.out.println("Tijd: " + (endTime - startTime) + " ns\n\n");
    }

    private void runPrim() {
        Prim<Station> prim = new Prim<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geef de code van het startstation: ");
        Station start = findStationByCode(scanner.nextLine());

        long startTime = System.nanoTime();
        HashTable<Station, Edge<Station>> result = prim.prim(graph, start);
        long endTime = System.nanoTime();

        System.out.println("Resultaat: " + result);
        System.out.println("Tijd: " + (endTime - startTime) + " ns\n\n");
    }
}
