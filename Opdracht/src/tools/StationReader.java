package tools;

import enums.StationType;
import models.Station;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class StationReader {
    public static List<Station> readStations() throws FileNotFoundException {
        System.out.println("[SYSTEM] Reading all Stations");
        ArrayList<Station> stations = new ArrayList<>();
        Instant start = Instant.now();
        new CSVReader("resources/stations.csv").skipHeader().readCSV().forEach(line -> {
            if (!validateStation(line)) return;
            String[] args = line.split(",");
            StationType type = null;
            switch (args[8]) {
                case "stoptreinstation" -> type = StationType.LOCAL_STATION;
                case "knooppuntStoptreinstation" -> type = StationType.LOCAL_STATION_HUB;
                case "sneltreinstation" -> type = StationType.EXPRESS_STATION;
                case "knooppuntSneltreinstation" -> type = StationType.EXPRESS_STATION_HUB;
                case "intercitystation" -> type = StationType.INTERCITY_STATION;
                case "knooppuntIntercitystation" -> type = StationType.INTERCITY_STATION_HUB;
                case "megastation" -> type = StationType.MEGA_STATION;
            }
            assert type != null;
            stations.add(new Station(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2]), args[3], args[4], args[5], args[6], args[7], type, Double.parseDouble(args[9]), Double.parseDouble(args[10])));
        });
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("[SYSTEM] All Stations read in " + timeElapsed + "ms");
        return stations;
    }

    private static boolean validateStation(String line) {
        Pattern pattern = Pattern.compile(
                "^\\d+,[A-Z]+,\\d{7},\"?(.+?),\"?(.+?),\"?(.+?),\"?(.+?),NL," +
                        "((stoptreinstation)|(knooppuntStoptreinstation)|(sneltreinstation)|(knooppuntSneltreinstation)|" +
                        "(intercitystation)|(knooppuntIntercitystation)|(megastation)),\\d+(\\.\\d+)?,\\d+(\\.\\d+)?$");
        return pattern.matcher(line).matches();
    }
}
