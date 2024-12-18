import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public class AccountService {
    // Instance variable for the AccountRepository that handles data persistence
    private final AccountRepository repository;

    /**
     * Constructor that initializes the AccountService with a given AccountRepository.
     * 
     * @param repository The AccountRepository used for data operations (find, save, etc.).
     */
    public AccountService(AccountRepository repository) {
        this.repository = repository; // Initialize the repository to interact with account data
    }

    /**
     * Attempts to log in by verifying the username and password.
     * 
     * @param username The username provided during login.
     * @param password The password provided during login.
     * @return true if the username exists and the password matches, false otherwise.
     * @throws IOException If there is an error reading the account data from the repository.
     * @throws CsvException If there is an error parsing the CSV file.
     */
    public boolean login(String username, String password) throws IOException, CsvException {
        // Find the account by username
        Account account = repository.findByUsername(username);
        
        // Check if the account exists and the password matches
        return account != null && account.getPassword().equals(password);
    }

    /**
     * Retrieves the role of the account based on the username.
     * 
     * @param username The username for which the role is requested.
     * @return The role of the account (e.g., "User", "Admin") if found, or null if no account exists.
     * @throws IOException If there is an error reading the account data from the repository.
     * @throws CsvException If there is an error parsing the CSV file.
     */
    public String getRole(String username) throws IOException, CsvException {
        // Find the account by username
        Account account = repository.findByUsername(username);
        
        // Return the role if the account exists, otherwise return null
        return (account != null) ? account.getRole() : null;
    }

    /**
     * Registers a new account by saving the provided username, password, and role.
     * 
     * @param username The username for the new account.
     * @param password The password for the new account.
     * @param role The role (e.g., "User", "Admin") for the new account.
     * @throws IOException If there is an error saving the account data to the repository.
     */
    public void register(String username, String password, String role) throws IOException {
        // Create a new Account object with the provided data
        Account account = new Account(username, password, role);
        
        // Save the new account to the repository
        repository.saveAccount(account);
    }
}
