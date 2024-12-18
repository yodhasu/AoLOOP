/**
 * Represents a payment method using an e-wallet.
 */
public class Ewallet implements paymentMethod {

    /**
     * Processes a payment using the e-wallet.
     *
     * @param total the total amount to pay
     * @return a string indicating the payment has been made using the e-wallet
     * @throws IllegalArgumentException if the total amount is less than or equal to 0
     */
    @Override
    public String pay(double total) {
        if (total <= 0) {
            throw new IllegalArgumentException("Payment must be greater than 0");
        }

        return "E-wallet payment with total value of " + String.valueOf(total);
    }
}