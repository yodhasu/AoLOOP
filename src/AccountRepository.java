import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    // Instance variable for managing CSV operations
    private final CSVManager csvManager;

    /**
     * Constructor that initializes the AccountRepository with a file path.
     * It creates the CSVManager instance and checks if the account file exists.
     * 
     * @param filePath The path to the CSV file storing account data.
     */
    public AccountRepository(String filePath) {
        this.csvManager = new CSVManager(filePath); // Initialize CSVManager with the given file path
        ensureFileExists(filePath); // Ensure that the file exists or create a new one
    }

    /**
     * Checks if the account file exists. If not, it creates a new file with a header row.
     * 
     * @param filePath The path of the file to check.
     */
    private void ensureFileExists(String filePath) {
        File file = new File(filePath); // Create a File object from the given file path
        if (!file.exists()) {
            try {
                System.out.println("Account file not found. Creating a new one...");
                // Create a new file with the header row for CSV (username, password, role)
                csvManager.createCSV(List.of("username,password,role"));
            } catch (IOException e) {
                // Handle error in case of an issue while creating the file
                System.err.println("Error creating account file: " + e.getMessage());
            }
        }
    }

    /**
     * Retrieves all accounts stored in the CSV file.
     * 
     * @return A list of Account objects representing all the accounts.
     * @throws IOException If there is an issue reading the CSV file.
     * @throws CsvException If there is a problem parsing the CSV file.
     */
    public List<Account> getAllAccounts() throws IOException, CsvException {
        // Read all rows from the CSV file
        List<String> rows = csvManager.readCSV();
        List<Account> accounts = new ArrayList<>();
        
        // Iterate over each row and convert it into an Account object
        for (String row : rows) {
            String[] columns = row.split(","); // Split the row by commas
            if (columns.length == 3 && !"username".equalsIgnoreCase(columns[0])) { // Skip the header row
                // Add the account if it's valid (username, password, role)
                accounts.add(new Account(columns[0], columns[1], columns[2]));
            }
        }
        return accounts;
    }

    /**
     * Saves a new account to the CSV file.
     * 
     * @param account The Account object to be saved.
     * @throws IOException If there is an error appending the account to the CSV file.
     */
    public void saveAccount(Account account) throws IOException {
        // Prepare the account data as a CSV row
        List<String> row = List.of(String.join(",", account.getUsername(), account.getPassword(), account.getRole()));
        // Append the new account data to the CSV file
        csvManager.appendCSV(row);
    }

    /**
     * Finds an account by its username.
     * 
     * @param username The username to search for.
     * @return The Account object if found, or null if no account with the given username exists.
     * @throws IOException If there is an error reading the CSV file.
     * @throws CsvException If there is a problem parsing the CSV file.
     */
    public Account findByUsername(String username) throws IOException, CsvException {
        // Loop through all accounts and return the account with the matching username
        for (Account account : getAllAccounts()) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                return account; // Return the account if a match is found
            }
        }
        return null; // Return null if no matching account is found
    }
}
