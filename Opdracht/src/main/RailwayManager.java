package main;

import main.models.Connection;
import main.models.Station;
import main.sorts.MergeSorter;
import main.sorts.SelectionSorter;
import main.tools.ConnectionReader;
import main.tools.StationReader;

import java.io.IOException;
import java.util.*;

public class RailwayManager {
    private final List<Station> stations;
    private final List<Station> sortedStations;
    private final List<Connection> connections;

    public RailwayManager() throws IOException {
        stations = StationReader.readStations("resources/stations.csv");
        sortedStations = new ArrayList<>(stations);
        sortedStations.sort(Comparator.comparing(Station::getNameShort));
        connections = ConnectionReader.readConnections("resources/tracks.csv");
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
            System.out.println();

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> linearSearchStations();
                case 2 -> binarySearchStations();
                case 3 -> selectionSortConnections();
                case 4 -> mergeSortConnections();

                default -> running = false;
            }
        }
    }

    public void linearSearchStations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zoek een station op naam: ");
        String name = scanner.nextLine();
        System.out.println("Zoeken...");

        int index = linearSearchStations(name);

        if (index == -1) {
            System.out.println("Station niet gevonden");
        } else {
            System.out.println("Station gevonden op index " + index);
        }
        System.out.println("\n\n");
    }

    public int linearSearchStations(String name) {
        for (int i = 0; i < sortedStations.size(); i++) {
            Station station = sortedStations.get(i);

            if (station.getNameShort().equals(name)) {
                return i;
            }
        }

        return -1;
    }

    public void binarySearchStations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zoek een station op naam: ");
        String name = scanner.nextLine();
        System.out.println("Zoeken...");

        int index = binarySearchStations(name);

        if (index == -1) {
            System.out.println("Station niet gevonden");
        } else {
            System.out.println("Station gevonden op index " + index);
        }
        System.out.println("\n\n");
    }

    public int binarySearchStations(String name) {
        int low = 0;
        int high = sortedStations.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Station station = sortedStations.get(mid);

            if (station.getNameShort().equals(name)) {
                return mid;
            } else if (station.getNameShort().compareTo(name) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public void selectionSortConnections() {
        SelectionSorter<Connection> sorter = new SelectionSorter<>();
        System.out.println("Sorting...");
        List<Connection> sortedConnections = sorter.sort(connections);
        System.out.println("Sorted!\nConnections:" + connections + "\nSorted connections: " + sortedConnections);
        System.out.println("\n\n");
    }

    public void mergeSortConnections() {
        MergeSorter<Connection> sorter = new MergeSorter<>();
        System.out.println("Sorting...");
        List<Connection> sortedConnections = sorter.sort(connections);
        System.out.println("Sorted!\nConnections:" + connections + "\nSorted connections: " + sortedConnections);
        System.out.println("\n\n");
    }
}
