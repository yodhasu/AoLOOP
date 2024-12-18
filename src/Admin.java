import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Admin {
    // Instance variable for managing products
    private final ProductManager manager;

    /**
     * Constructor that initializes the Admin class with a ProductManager.
     * 
     * @param manager The ProductManager instance used for handling product operations.
     */
    public Admin(ProductManager manager) {
        this.manager = manager; // Initialize the ProductManager
    }

    /**
     * Starts the Admin panel, where the admin can perform various actions related to products.
     * This method runs a loop displaying the admin options and responding to user input.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        while (true) {
            // Display the admin menu
            System.out.println("\nAdmin Panel");
            System.out.println("1. Load Products");
            System.out.println("2. Add Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt(); // Get the admin's choice
            scanner.nextLine(); // Consume the newline character

            try {
                // Switch statement to handle different admin actions
                switch (choice) {
                    case 1:
                        // Load and display the list of products
                        List<Product> products = manager.loadProducts();
                        System.out.printf("+----------------+-------------+-------+%n");
                        System.out.printf("| Product Name   | Category    | Price |%n");
                        System.out.printf("+----------------+-------------+-------+%n");
                        // Display each product's details
                        for (Product product : products) {
                            System.out.printf("| %-14s | %-11s | %5.2f |%n",
                                    product.getName(), product.getCategory(), product.getPrice());
                        }
                        System.out.printf("+----------------+-------------+-------+%n");
                        break;

                    case 2:
                        // Add a new product
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter category (Elektronik/Buku): ");
                        String category = scanner.nextLine();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();
                        // Create and add the new product
                        Product product = ProductFactory.createProduct(name, category, price);
                        manager.addProduct(product);
                        System.out.println("Product added successfully.");
                        break;

                    case 3:
                        // Delete a product
                        System.out.print("Enter product name to delete: ");
                        String productName = scanner.nextLine();
                        // Delete the product by name
                        manager.deleteProduct(productName);
                        System.out.println("Product deleted successfully.");
                        break;

                    case 4:
                        // Exit the Admin panel
                        System.out.println("Exiting Admin Panel...");
                        return;

                    default:
                        // Handle invalid input
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            } catch (IOException e) {
                // Handle errors related to I/O operations
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
