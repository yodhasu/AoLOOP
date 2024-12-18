import java.util.ArrayList;
import java.util.List;

public class Cart {
    // A list to store products added to the cart
    private final List<Product> productList;

    /**
     * Constructor to initialize the cart with an empty product list.
     */
    public Cart() {
        this.productList = new ArrayList<>(); // Initialize the list for storing products
    }

    /**
     * Adds a product to the cart.
     * 
     * @param prod The product to be added to the cart.
     * @throws IllegalArgumentException If the product is null.
     */
    public void addProduct(Product prod) {
        // Validate that the product is not null
        if (prod == null) {
            throw new IllegalArgumentException("Product can't be empty");
        }
        // Add the product to the list
        this.productList.add(prod);
    }

    /**
     * Calculates the total price of all products in the cart.
     * 
     * @return The sum of the prices of all products in the cart.
     */
    public double sumTotal() {
        // Calculate the total price using streams to sum up product prices
        return this.productList.stream().mapToDouble(Product::getPrice).sum();
    }

    /**
     * Displays the list of products in the cart, including their names and prices.
     */
    public void viewCart() {
        // Loop through the products in the cart and print their details
        for (Product i : this.productList) {
            System.out.println(i.getName() + " " + String.valueOf(i.getPrice()));
        }
    }

    /**
     * Checks if the cart is empty.
     * 
     * @return true if the cart is empty, false otherwise.
     */
    public boolean emptyCart() {
        // Return true if the cart's product list is empty
        return this.productList.isEmpty();
    }
}
