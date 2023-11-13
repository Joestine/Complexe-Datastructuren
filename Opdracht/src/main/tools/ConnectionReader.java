package main.tools;

import main.models.Connection;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ConnectionReader {
    public static List<Connection> readConnections(String filename) throws FileNotFoundException {
        System.out.println("[SYSTEM] Reading all Connections");
        ArrayList<Connection> connections = new ArrayList<>();
        Instant start = Instant.now();
        new CSVReader(filename).readCSV().forEach(line -> {
            if (!validateConnection(line)) return;
            String[] args = line.split(",");
            connections.add(new Connection(args[0], args[1], Integer.parseInt(args[2])));
        });
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("[SYSTEM] All Connections read in " + timeElapsed + "ms");
        return connections;
    }

    private static boolean validateConnection(String line) {
        Pattern pattern = Pattern.compile("^[A-Za-z]+,[A-Za-z]+,\\d+,\\d+,\\d+$");
        return pattern.matcher(line).matches();
    }
}
