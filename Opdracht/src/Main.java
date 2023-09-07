import tools.CSVReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CSVReader reader = new CSVReader();
        try {
            reader.readCSV("resources/stations.csv").forEach(records -> {
                for (String record:
                     records) {
                    System.out.println(record);
                }
                System.out.println("");
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}