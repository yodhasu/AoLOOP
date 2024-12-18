/**
 * A factory class for creating products based on their category.
 */
public class ProductFactory {
    /**
     * Creates a product instance based on the specified category.
     * 
     * @param name     the name of the product
     * @param category the category of the product
     * @param price    the price of the product
     * @return a product instance based on the category
     * @throws IllegalArgumentException if the category is unknown
     */
    public static Product createProduct(String name, String category, double price) {
        switch (category.toLowerCase()) {
            case "elektronik":
                // Creates an Elektronik product instance
                return new Elektronik(name, price);
            case "buku":
                // Creates a Buku product instance
                return new Buku(name, price);
            default:
                // Throws an exception for unknown categories
                throw new IllegalArgumentException("Unknown category: " + category);
        }
    }
}