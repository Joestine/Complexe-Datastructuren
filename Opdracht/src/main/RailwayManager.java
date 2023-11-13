package main;

import main.models.Connection;
import main.models.Geolocation;
import main.models.Station;
import main.tools.ConnectionReader;
import main.tools.StationReader;
import main.structures.tree.AVLTree;

import java.io.IOException;
import java.util.*;

public class RailwayManager {
    private final List<Station> stations;
    private final List<Connection> connections;

    public RailwayManager() throws IOException {
        stations = StationReader.readStations("resources/stations.csv");
        connections = ConnectionReader.readConnections("resources/tracks.csv");
    }
}
