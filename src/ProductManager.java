import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Manages products by loading, adding, and deleting them from a repository.
 */
public class ProductManager {
    // The repository used to store and retrieve products
    private final ProductRepository repository;

    /**
     * Initializes a new ProductManager instance with the specified repository.
     * 
     * @param repository the repository used to store and retrieve products
     */
    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Loads products from the repository into a list.
     * 
     * @return a list of products loaded from the repository
     * @throws IOException if an I/O error occurs while loading products
     */
    public List<Product> loadProducts() throws IOException {
        List<Product> products = new ArrayList<>();
        // Load rows from the repository, excluding the header row
        List<String> rows = repository.load();

        for (int i = 1; i < rows.size(); i++) { // Skip the header row
            // Extract fields from each row
            String row = rows.get(i);
            String[] fields = row.split(",");
            if (fields.length != 3) continue; // Ensure there are exactly 3 fields

            // Create a new product from the extracted fields
            String name = fields[0];
            String category = fields[1];
            double price = Double.parseDouble(fields[2]);

            products.add(ProductFactory.createProduct(name, category, price));
        }

        return products;
    }

    /**
     * Adds a new product to the repository.
     * 
     * @param newProduct the product to add
     * @throws IOException if an I/O error occurs while adding the product
     */
    public void addProduct(Product newProduct) throws IOException {
        // Create a row representing the new product
        String row = newProduct.getName() + "," + newProduct.getCategory() + "," + newProduct.getPrice();
        // Append the new product to the repository
        repository.append(List.of(row));
    }

    /**
     * Deletes a product from the repository by product name.
     * 
     * @param productName the name of the product to delete
     * @throws IOException if an I/O error occurs while deleting the product
     */
    public void deleteProduct(String productName) throws IOException {
        // Load all rows from the repository
        List<String> rows = repository.load();
        // Create a new list of updated rows
        List<String> updatedRows = new ArrayList<>();
        updatedRows.add(rows.get(0)); // Keep the header row

        for (int i = 1; i < rows.size(); i++) {
            // Check if the row matches the product name
            String row = rows.get(i);
            if (row.toLowerCase().startsWith(productName.toLowerCase() + ",")) {
                continue; // Skip the row matching the product name
            }
            updatedRows.add(row);
        }

        // Save the updated list of rows to the repository
        repository.save(updatedRows);
    }
}