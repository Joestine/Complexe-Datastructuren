import enums.StationType;
import tools.CSVReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class RailwayManager {
    private final List<Station> stations;
    public final List<String> stationNames;
    private final List<Connection> connections;

    public RailwayManager() throws IOException {
        stations = new ArrayList<>();
        stationNames = new ArrayList<>();
        connections = new ArrayList<>();
        readStations();
        sortStationNames();
        readConnections();
    }

    public void readConnections() throws FileNotFoundException {
        System.out.println("[SYSTEM] Reading all Connections");
        Instant start = Instant.now();
        new CSVReader("resources/tracks.csv").readCSV().forEach(line -> connections.add(new Connection(line[0], line[1])));
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("[SYSTEM] All Connections read in " + timeElapsed + "ms");
    }

    public void readStations() throws FileNotFoundException {
        System.out.println("[SYSTEM] Reading all Stations");
        Instant start = Instant.now();
        new CSVReader("resources/stations.csv").skipHeader().readCSV().forEach(line -> {
            StationType type = null;
            switch (line[8]) {
                case "stoptreinstation" -> type = StationType.LOCAL_STATION;
                case "knooppuntStoptreinstation" -> type = StationType.LOCAL_STATION_HUB;
                case "sneltreinstation" -> type = StationType.EXPRESS_STATION;
                case "knoppuntSneltreinstation" -> type = StationType.EXPRESS_STATION_HUB;
                case "intercitystation" -> type = StationType.INTERCITY_STATION;
                case "knooppuntIntercitystation" -> type = StationType.INTERCITY_STATION_HUB;
                case "megastation" -> type = StationType.MEGA_STATION;
            }
            assert type != null;
            stations.add(new Station(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]), line[3], line[4], line[5], line[6], line[7], type, Double.parseDouble(line[9]), Double.parseDouble(line[10])));
            if (!stationNames.contains(line[1])) stationNames.add(line[1]);
        });
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("[SYSTEM] All Stations read in " + timeElapsed + "ms");
    }

    public void sortStationNames() {
        System.out.println("[SYSTEM] Sorting all Station Names");
        Instant start = Instant.now();
        Collections.sort(stationNames);
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("[SYSTEM] All Station Names sorted in " + timeElapsed + "ms");
    }

    public String searchStationName(String station) {
        System.out.println("[SYSTEM] Searching for station " + station);
        Instant start = Instant.now();

        int low = 0;
        int high = stationNames.size() - 1;

        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (Objects.equals(stationNames.get(mid), station)) {
                Instant end = Instant.now();
                long timeElapsed = Duration.between(start, end).toMillis();
                System.out.println("[SYSTEM] Found Item at index " + mid + " in " + timeElapsed + "ms");
                return stationNames.get(mid);
            } else if (stationNames.get(mid).compareTo(station) > 0) {
                high = mid - 1;
            } else if (stationNames.get(mid).compareTo(station) < 0) {
                low = mid + 1;
            }
        }
        throw new NoSuchElementException("Station Name cannot be found!");
    }
}
