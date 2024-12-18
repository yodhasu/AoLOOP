public class Account {
    // Private final fields for the username, password, and role of the account
    private final String username;
    private final String password;
    private final String role;

    /**
     * Constructor to initialize an Account object.
     * 
     * @param username The username for the account
     * @param password The password for the account
     * @param role The role associated with the account (e.g., User or Admin)
     */
    public Account(String username, String password, String role) {
        this.username = username; // Initialize the username
        this.password = password; // Initialize the password
        this.role = role; // Initialize the role
    }

    /**
     * Gets the username of the account.
     * 
     * @return The username of the account
     */
    public String getUsername() {
        return username; // Return the username
    }

    /**
     * Gets the password of the account.
     * 
     * @return The password of the account
     */
    public String getPassword() {
        return password; // Return the password
    }

    /**
     * Gets the role of the account.
     * 
     * @return The role of the account (e.g., User or Admin)
     */
    public String getRole() {
        return role; // Return the role
    }
}
