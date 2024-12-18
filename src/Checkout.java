public class Checkout {
    // Instance variables to hold the current cart and the chosen payment method
    private final Cart currcart;
    private final paymentMethod paid;

    /**
     * Constructor to initialize the Checkout with a given cart and payment method.
     * 
     * @param cart The shopping cart for checkout.
     * @param pay The payment method to process the payment.
     * @throws IllegalArgumentException If either the cart or payment method is null.
     */
    public Checkout(Cart cart, paymentMethod pay) {
        // Validate that the cart and payment method are not null
        if (cart == null || pay == null) {
            throw new IllegalArgumentException("Cart or Payment method can't be empty!");
        }
        // Assign the cart and payment method
        this.currcart = cart;
        this.paid = pay;
    }

    /**
     * Processes the checkout for the current cart, calculates the total, and displays the payment details.
     */
    public void checkOutCart() {
        // Calculate the total price of the products in the cart
        double total = this.currcart.sumTotal();

        // If the cart is empty, display a message and return
        if (total == 0) {
            System.out.println("Cart is empty");
            return;
        }

        // Print the payment details
        System.out.println("== Payment Details ==");
        this.currcart.viewCart();  // Show the contents of the cart
        System.out.printf("Total: Rp %2f%n", total);  // Display the total price
        this.paid.pay(total);  // Process the payment using the chosen payment method
    }
}
