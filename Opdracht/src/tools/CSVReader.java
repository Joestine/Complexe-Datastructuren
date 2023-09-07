package tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public List<String[]> readCSV(String path) throws IOException {
        try {
            Scanner scanner = new Scanner(new File(path));
            List<String[]> records = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String record = scanner.nextLine();
                records.add(record.split(","));
            }
            return records;
        } catch (IOException e) {
            throw new IOException("Could not find file!");
        }

    }
}
