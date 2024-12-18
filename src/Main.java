import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Define the file path for account data and initialize necessary objects
        String accountFilePath = "accounts.csv";
        AccountRepository accountRepository = new AccountRepository(accountFilePath); // Repository for storing account data
        AccountService accountService = new AccountService(accountRepository); // Service for handling account operations (login, register)
        ProductRepository repository = new CSVProductRepository("productsdb.csv"); // Repository for product data (CSV-based)
        ProductManager manager = new ProductManager(repository); // Manager for managing products (viewing, updating)

        // Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Infinite loop to show the main menu until the user chooses to exit
        while (true) {
            System.out.println("=== E-Commerce System ===");
            System.out.println("1. Login"); // Option for the user to login
            System.out.println("2. Register"); // Option for the user to register a new account
            System.out.println("3. Exit"); // Option to exit the program
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine(); // Read user choice

            switch (choice) {
                case "1": // Login case
                    // Prompt the user to enter their username and password for login
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    try {
                        // Attempt to login using the provided credentials
                        if (accountService.login(username, password)) {
                            // If login is successful, retrieve the user's role
                            String role = accountService.getRole(username);
                            System.out.println("Login successful! Role: " + role);

                            // Start the appropriate interface based on the user's role
                            if ("User".equalsIgnoreCase(role)) {
                                User user = new User(manager); // Create User object to handle user-specific operations
                                user.start(); // Start user session
                            } else if ("Admin".equalsIgnoreCase(role)) {
                                Admin admin = new Admin(manager); // Create Admin object to handle admin-specific operations
                                admin.start(); // Start admin session
                            } else {
                                // If the role is unknown, print an error message
                                System.out.println("Unknown role. Contact system administrator.");
                            }
                        } else {
                            // If login fails, show an error message
                            System.out.println("Invalid username or password.");
                        }
                    } catch (IOException | CsvException e) {
                        // Handle any potential errors (e.g., file issues or CSV-related errors)
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;

                case "2": // Register case
                    // Prompt the user to input a new username, password, and role during registration
                    System.out.print("Enter username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Enter role (User/Admin): ");
                    String role = scanner.nextLine();

                    try {
                        // Register the new account using the provided information
                        accountService.register(newUsername, newPassword, role);
                        System.out.println("Account registered successfully!"); // Confirm registration
                    } catch (IOException e) {
                        // Handle any errors that occur during registration (e.g., file-related errors)
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;

                case "3": // Exit case
                    System.out.println("Goodbye!"); // Farewell message before exiting
                    scanner.close(); // Close the scanner resource
                    return; // Exit the program

                default:
                    // If the user enters an invalid option, prompt them to try again
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
