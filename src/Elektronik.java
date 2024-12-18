/**
 * Represents an Elektronik product with a name, price, and category.
 *
 * @author prk
 */
public class Elektronik implements Product {

    // Properties of the Elektronik product
    public String name;
    public double price;
    public final String category = "elektronik"; // Category is fixed to "elektronik"

    /**
     * Initializes an Elektronik product with the specified name and price.
     *
     * @param nm  the name of the product
     * @param prc the price of the product
     * @throws IllegalArgumentException if the name is empty or the price is negative
     */
    public Elektronik(String nm, double prc) {
        if (nm == null || nm.trim().isEmpty()) {
            throw new IllegalArgumentException("Name can't be empty");
        }
        if (prc < 0) {
            throw new IllegalArgumentException("Price can't be 0");
        }
        this.name = nm;
        this.price = prc;
    }

    /**
     * Returns the name of the product.
     *
     * @return the product name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the product.
     *
     * @return the product price
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Returns the category of the product (always "elektronik").
     *
     * @return the product category
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Returns a string representation of the product.
     *
     * @return a string containing the product's title and price
     */
    @Override
    public String toString() {
        return "Tittle: " + name + "\n" + "Price: " + String.valueOf(price);
    }
}