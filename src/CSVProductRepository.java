import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;

/**
 * Responsible for managing product data from a CSV file.
 */
public class CSVProductRepository implements ProductRepository {
    private final CSVManager csvManager;

    /**
     * Initializes a new instance of CSVProductRepository with the specified CSV file path.
     * 
     * @param csvFilePath The path to the CSV file containing product data.
     */
    public CSVProductRepository(String csvFilePath) {
        this.csvManager = new CSVManager(csvFilePath);
    }

    /**
     * Loads product data from the CSV file into a list of strings.
     * 
     * @return A list of product data strings.
     * @throws IOException If an I/O error occurs while reading the CSV file.
     */
    @Override
    public List<String> load() throws IOException {
        try {
            // Attempt to read the CSV file using the CSVManager
            return csvManager.readCSV();
        } catch (CsvException e) {
            // Wrap the CsvException in an IOException for further handling
            throw new IOException("Error reading CSV", e);
        }
    }

    /**
     * Saves product data to the CSV file.
     * 
     * @param data A list of product data strings to be saved.
     * @throws IOException If an I/O error occurs while writing to the CSV file.
     */
    @Override
    public void save(List<String> data) throws IOException {
        csvManager.createCSV(data);
    }

    /**
     * Appends product data to the existing CSV file.
     * 
     * @param data A list of product data strings to be appended.
     * @throws IOException If an I/O error occurs while writing to the CSV file.
     */
    @Override
    public void append(List<String> data) throws IOException {
        csvManager.appendCSV(data);
    }
}