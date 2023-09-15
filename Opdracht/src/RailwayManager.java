import enums.StationType;
import models.Connection;
import models.Station;
import tools.CSVReader;
import tools.ConnectionReader;
import tools.StationReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class RailwayManager {
    private final List<Station> stations;
    private final List<Connection> connections;

    public RailwayManager() throws IOException {
        stations = StationReader.readStations();
        connections = ConnectionReader.readConnections();
        sortStations();
    }

    public void sortStations() {
        System.out.println("[SYSTEM] Sorting all Stations by name");
        Instant start = Instant.now();
        stations.sort(Comparator.comparing(Station::getNameShort));
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("[SYSTEM] All Stations sorted in " + timeElapsed + "ms");
    }

    public Station searchStationLinear(String stationName) {
        System.out.println("[SYSTEM] Searching for station " + stationName);
        Instant start = Instant.now();

        for (Station station : stations) {
            if (Objects.equals(station.getNameShort(), stationName)) {
                Instant end = Instant.now();
                long timeElapsed = Duration.between(start, end).toMillis();
                int index = stations.indexOf(station);
                System.out.println("[SYSTEM] Found Item at index " + index + " in " + timeElapsed + "ms");
                return station;
            }
        }
        return null;
    }

    public Station searchStationBinary(String stationName) {
        System.out.println("[SYSTEM] Searching for station " + stationName);
        Instant start = Instant.now();

        int low = 0;
        int high = stations.size() - 1;

        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (Objects.equals(stations.get(mid).getNameShort(), stationName)) {
                Instant end = Instant.now();
                long timeElapsed = Duration.between(start, end).toMillis();
                System.out.println("[SYSTEM] Found Item at index " + mid + " in " + timeElapsed + "ms");
                return stations.get(mid);
            } else if (stations.get(mid).getNameShort().compareTo(stationName) > 0) {
                high = mid - 1;
            } else if (stations.get(mid).getNameShort().compareTo(stationName) < 0) {
                low = mid + 1;
            }
        }
        return null;
    }
}
