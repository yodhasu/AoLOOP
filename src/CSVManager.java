import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages CSV file operations, including reading, creating, and appending data.
 */
public class CSVManager {
    private final String csvFile; // The path to the CSV file.

    /**
     * Initializes the CSVManager with the path to the CSV file.
     * 
     * @param csvFile The path to the CSV file.
     */
    public CSVManager(String csvFile) {
        this.csvFile = csvFile;
    }

    /**
     * Reads the CSV file and returns its contents as a list of strings.
     * 
     * @return A list of strings representing the rows in the CSV file.
     * @throws IOException  If an I/O error occurs while reading the file.
     * @throws CsvException If an error occurs while processing the CSV data.
     */
    public List<String> readCSV() throws IOException, CsvException {
        List<String> rows = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            List<String[]> rawRows = reader.readAll();
            for (String[] row : rawRows) {
                // Convert each row from an array of strings to a single string.
                rows.add(String.join(",", row));
            }
        }
        return rows;
    }

    /**
     * Creates a new CSV file with the specified data.
     * 
     * @param data A list of strings representing the rows to write to the CSV file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void createCSV(List<String> data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            for (String row : data) {
                // Split each row into an array of strings and write it to the CSV file.
                writer.writeNext(row.split(","));
            }
        }
    }

    /**
     * Appends data to an existing CSV file.
     * 
     * @param data A list of strings representing the rows to append to the CSV file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void appendCSV(List<String> data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {
            for (String row : data) {
                // Split each row into an array of strings and append it to the CSV file.
                writer.writeNext(row.split(","));
            }
        }
    }
}