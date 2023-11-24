package main;

import main.models.Connection;
import main.models.Station;
import main.sorts.MergeSorter;
import main.sorts.SelectionSorter;
import main.structures.graph.Graph;
import main.structures.linkedlist.LinkedList;
import main.structures.linkedlist.Node;
import main.tools.ConnectionReader;
import main.tools.StationReader;

import java.io.IOException;
import java.util.*;


public class RailwayManager {
    private final main.structures.linkedlist.LinkedList<Station> stations;
    private final main.structures.linkedlist.LinkedList<Connection> connections;
    private final Graph<Station> graph;

    public RailwayManager() throws IOException {
        stations = new main.structures.linkedlist.LinkedList<>();
        StationReader.readStations("resources/teststations.csv").forEach(stations::add);

        connections = new main.structures.linkedlist.LinkedList<>();
        ConnectionReader.readConnections("resources/testtracks.csv").forEach(connections::add);

        System.out.println("Stations: " + stations + "length: " + stations.size());
        System.out.println("Connections: " + connections + "length: " + connections.size());

        graph = new Graph<>();
        Node<Station> current = stations.getHead();
        while (current != null) {
            graph.addVertex(current.getData());
            current = current.getNext();
        }

        current = stations.getHead();
        while (current != null) {
            Node<Connection> connection = connections.getHead();
            while (connection != null) {
                if (connection.getData().getStart().equalsIgnoreCase(current.getData().getCode())) {
                    Station endStation = findStationByCode(current, connection.getData().getEnd());
                    if (endStation != null) {
                        graph.addEdge(current.getData(), endStation, connection.getData().getDistance());
                    }
                }
                connection = connection.getNext();
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
            System.out.println("[5] LAAT GRAAF ZIEN");
            System.out.println();

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> linearSearchStations();
                case 2 -> binarySearchStations();
                case 3 -> selectionSortConnections();
                case 4 -> mergeSortConnections();
                case 5 -> showGraph();

                default -> running = false;
            }
        }
    }

    private Station findStationByCode(Node<Station> head, String code) {
        Node<Station> current = head;
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

    private void showGraph() {
        System.out.println(graph.graphViz());
    }
}
