public class Cash implements paymentMethod {

    /**
     * Implements the pay method from the PaymentMethod interface to handle cash payments.
     * 
     * @param total The total amount to be paid.
     * @return A string confirming the cash payment and the total value.
     * @throws IllegalArgumentException If the total is less than or equal to 0.
     */
    @Override
    public String pay(double total) {
        // Validate that the payment total is greater than 0
        if (total <= 0) {
            throw new IllegalArgumentException("Payment must be greater than 0");
        }

        // Return a string indicating the cash payment with the specified total amount
        return "Cash payment with total value of " + String.valueOf(total);
    }
}
