/**
 * Represents a credit payment method.
 */
public class Credit implements paymentMethod {

    /**
     * Processes a payment using the credit method.
     * 
     * @param total The total value of the payment.
     * @return A string describing the payment, including its total value.
     */
    @Override
    public String pay(double total) {
        // Check if the total payment value is valid (greater than 0).
        if (total <= 0) {
            // Throw an exception if the payment value is invalid.
            throw new IllegalArgumentException("Payment must be greater than 0");
        }

        // Return a string describing the payment, including its total value.
        return "Credit payment with total value of " + String.valueOf(total);
    }
}