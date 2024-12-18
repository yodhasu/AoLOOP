import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class User {
    private final ProductManager manager;
    private Cart usrcart = new Cart(); // Cart object to store selected products

    // Payment methods
    paymentMethod cash = new Cash();
    paymentMethod credit = new Credit();
    paymentMethod ewallet = new Ewallet();

    /**
     * Constructor to initialize the User with a given ProductManager.
     * 
     * @param manager The ProductManager to manage product operations.
     */
    public User(ProductManager manager) {
        this.manager = manager;
    }

    /**
     * Starts the user interaction for shopping.
     * It displays a menu and processes user choices.
     */
    public void start() {
        Scanner inp = new Scanner(System.in);

        while (true) {
            System.out.print("\033[H\033[2J");  // Clears the console screen
            System.out.flush();

            showMenu();  // Display the main menu
            System.out.print("Masukan pilihan [1, 2, 3, ...]: ");
            String inpmenu = inp.nextLine();

            try {
                switch (inpmenu) {
                    case "1": // See and Add Products to Cart
                        List<Product> products = manager.loadProducts();
                        if (products.isEmpty()) {
                            System.out.println("Tidak ada produk tersedia.");
                        } else {
                            showProducts(products);  // Display products to the user
                            System.out.print("\nMasukkan nomor produk untuk ditambahkan ke keranjang (atau 0 untuk kembali): ");
                            int productChoice = Integer.parseInt(inp.nextLine());
                            if (productChoice > 0 && productChoice <= products.size()) {
                                Product selectedProduct = products.get(productChoice - 1);
                                usrcart.addProduct(selectedProduct);  // Add product to the cart
                                System.out.println("Produk telah ditambahkan ke keranjang.");
                            } else if (productChoice != 0) {
                                System.out.println("Pilihan tidak valid.");
                            }
                        }
                        System.out.println("\nTekan Enter untuk melanjutkan...");
                        inp.nextLine();
                        break;

                    case "2": // View Cart
                        if (usrcart.emptyCart()) {
                            System.out.println("Keranjang Anda kosong.");
                        } else {
                            showCart();  // Show cart contents and total
                        }
                        System.out.println("\nTekan Enter untuk melanjutkan...");
                        inp.nextLine();
                        break;

                    case "3": // Pay
                        if (usrcart.emptyCart()) {
                            System.out.println("Keranjang Anda kosong. Tambahkan produk sebelum membayar.");
                        } else {
                            processPayment(inp);  // Proceed to payment
                        }
                        System.out.println("\nTekan Enter untuk melanjutkan...");
                        inp.nextLine();
                        break;

                    case "4": // Exit
                        System.out.println("Keluar dari sistem pengguna...");
                        return;

                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                        break;
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
    }

    /**
     * Displays the main menu for the user.
     */
    private void showMenu() {
        System.out.println("== Sistem Pengguna E-Commerce ==");
        System.out.println("1. Lihat dan Tambahkan Produk ke Keranjang");
        System.out.println("2. Lihat Keranjang");
        System.out.println("3. Bayar");
        System.out.println("4. Keluar");
    }

    /**
     * Displays a list of products to the user.
     * 
     * @param products The list of products to display.
     */
    private void showProducts(List<Product> products) {
        System.out.printf("+----------------+-------------+-------+%n");
        System.out.printf("| No | Product Name   | Category    | Price |%n");
        System.out.printf("+----+----------------+-------------+-------+%n");
        int i = 1;
        for (Product product : products) {
            System.out.printf("| %2d | %-14s | %-11s | %5.2f |%n",
                    i++, product.getName(), product.getCategory(), product.getPrice());
        }
        System.out.printf("+----+----------------+-------------+-------+%n");
    }

    /**
     * Displays the user's cart and its total.
     */
    private void showCart() {
        System.out.println("== Keranjang Anda ==");
        usrcart.viewCart();  // Show the products in the cart
        System.out.printf("Total: %.2f%n", usrcart.sumTotal());  // Display the total price of the cart
    }

    /**
     * Processes the payment for the items in the user's cart.
     * 
     * @param inp The Scanner object to read user input.
     */
    private void processPayment(Scanner inp) {
        showCart();  // Show cart contents before proceeding with payment
        System.out.println("\nPilih metode pembayaran:");
        System.out.println("1. Cash");
        System.out.println("2. Credit");
        System.out.println("3. E-wallet");
        System.out.print("Masukkan pilihan: ");
        String paymentChoice = inp.nextLine();

        switch (paymentChoice) {
            case "1":
                System.out.println("Pembayaran dengan Cash diproses...");
                Checkout checkcash = new Checkout(usrcart, cash);  // Process cash payment
                checkcash.checkOutCart();
                break;
            case "2":
                System.out.println("Pembayaran dengan Credit diproses...");
                Checkout checkcredit = new Checkout(usrcart, credit);  // Process credit payment
                checkcredit.checkOutCart();
                break;
            case "3":
                System.out.println("Pembayaran dengan E-wallet diproses...");
                Checkout checkewallet = new Checkout(usrcart, ewallet);  // Process e-wallet payment
                checkewallet.checkOutCart();
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }

        System.out.println("Pembayaran berhasil! Terima kasih telah berbelanja.");
        usrcart = new Cart();  // Clear the cart after successful payment
    }
}
