/**
 * The Buku class represents a product in the "Buku" (book) category in the e-commerce system.
 * It implements the Product interface and provides details about the product such as name, price, and category.
 * 
 * @author prk
 */
public class Buku implements Product {

    // Instance variables for the product's name, price, and fixed category
    public String name;
    public double price;
    public final String category = "buku"; // Fixed category for Buku products

    /**
     * Constructor for creating a Buku product with a specified name and price.
     * The constructor ensures the name is not empty and the price is non-negative.
     * 
     * @param nm The name of the book.
     * @param prc The price of the book.
     * @throws IllegalArgumentException If the name is null or empty, or if the price is negative.
     */
    public Buku(String nm, double prc) {
        // Validate the name and price parameters
        if (nm == null || nm.trim().isEmpty()) {
            throw new IllegalArgumentException("Name can't be empty");
        }
        if (prc < 0) {
            throw new IllegalArgumentException("Price can't be 0");
        }

        // Assign the name and price
        this.name = nm;
        this.price = prc;
    }

    /**
     * Retrieves the name of the product.
     * 
     * @return The name of the product.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Retrieves the price of the product.
     * 
     * @return The price of the product.
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Retrieves the category of the product.
     * 
     * @return The category of the product ("buku").
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Provides a string representation of the product, including its name and price.
     * 
     * @return A formatted string containing the name and price of the book.
     */
    @Override
    public String toString() {
        return "Tittle: " + name + "\n" + "Price: " + String.valueOf(price);
    }
}
