package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private final Scanner scanner;

    public CSVReader(String path) throws FileNotFoundException {
        scanner = new Scanner(new File(path));
    }

    public CSVReader skipHeader() {
        scanner.nextLine();
        return this;
    }

    public List<String[]> readCSV() {
        List<String[]> records = new ArrayList<>();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String record = scanner.nextLine();
            records.add(record.split(","));
        }
        return records;
    }
}
