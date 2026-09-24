import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // =========================================
    // CUSTOMER CLASS
    // =========================================

    static class Customer {

        int customerId;
        String name;
        String phone;

        Customer(int customerId, String name, String phone) {
            this.customerId = customerId;
            this.name = name;
            this.phone = phone;
        }

        void displayCustomer() {
            System.out.println(
                    customerId + " | " + name + " | " + phone);
        }
    }

    // =========================================
    // SALE CLASS
    // =========================================

    static class Sale {

        String customerName;
        double amount;
        String paymentMethod;

        Sale(String customerName, double amount,
                String paymentMethod) {

            this.customerName = customerName;
            this.amount = amount;
            this.paymentMethod = paymentMethod;
        }

        void displaySale() {

            System.out.println(
                    customerName + " | Rs."
                            + amount + " | "
                            + paymentMethod);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // =========================================
        // ADMIN LOGIN
        // =========================================

        System.out.println("================================");
        System.out.println("       SHOPPING MART");
        System.out.println("================================");

        System.out.print("Enter Admin Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Admin Password: ");
        String password = sc.nextLine();

        if (!username.equals("admin") ||
                !password.equals("1234")) {

            System.out.println("\nInvalid Username or Password!");
            System.out.println("Access Denied.");

            sc.close();
            return;
        }

        System.out.println("\nLogin Successful!");
        System.out.println("Welcome Admin!");

        // =========================================
        // PRODUCT LIST
        // =========================================

        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(101, "Rice", 550.00, 20));
        products.add(new Product(102, "Soap", 40.00, 50));
        products.add(new Product(103, "Biscuit", 30.00, 100));

        // =========================================
        // CUSTOMER LIST
        // =========================================

        ArrayList<Customer> customers = new ArrayList<>();

        // =========================================
        // SALES LIST
        // =========================================

        ArrayList<Sale> sales = new ArrayList<>();

        // =========================================
        // MAIN MENU
        // =========================================

        while (true) {

            System.out.println("\n================================");
            System.out.println("       SHOPPING MART MENU");
            System.out.println("================================");

            System.out.println("1. View Products");
            System.out.println("2. Add Product");
            System.out.println("3. Update Stock");
            System.out.println("4. Remove Product");
            System.out.println("5. Customer Management");
            System.out.println("6. Shopping / Billing");
            System.out.println("7. Sales Report");
            System.out.println("8. Exit");

            System.out.println("--------------------------------");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            // =========================================
            // 1. VIEW PRODUCTS
            // =========================================

            if (choice == 1) {

                System.out.println("\n================================");
                System.out.println("        AVAILABLE PRODUCTS");
                System.out.println("================================");

                if (products.isEmpty()) {

                    System.out.println(
                            "No products available.");

                } else {

                    for (Product p : products) {
                        p.displayProduct();
                    }
                }

            }

            // =========================================
            // 2. ADD PRODUCT
            // =========================================

            else if (choice == 2) {

                System.out.println("\n================================");
                System.out.println("          ADD PRODUCT");
                System.out.println("================================");

                System.out.print("Enter Product ID: ");
                int id = sc.nextInt();

                boolean duplicate = false;

                for (Product p : products) {

                    if (p.productId == id) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {

                    System.out.println(
                            "Product ID already exists!");

                } else {

                    sc.nextLine();

                    System.out.print(
                            "Enter Product Name: ");

                    String name = sc.nextLine();

                    System.out.print(
                            "Enter Price: ");

                    double price = sc.nextDouble();

                    System.out.print(
                            "Enter Stock: ");

                    int stock = sc.nextInt();

                    if (price <= 0 || stock < 0) {

                        System.out.println(
                                "Invalid price or stock!");

                    } else {

                        Product newProduct = new Product(
                                id,
                                name,
                                price,
                                stock);

                        products.add(newProduct);

                        System.out.println(
                                "Product added successfully!");
                    }
                }

            }

            // =========================================
            // 3. UPDATE STOCK
            // =========================================

            else if (choice == 3) {

                System.out.println("\n================================");
                System.out.println("         UPDATE STOCK");
                System.out.println("================================");

                System.out.print(
                        "Enter Product ID: ");

                int id = sc.nextInt();

                boolean found = false;

                for (Product p : products) {

                    if (p.productId == id) {

                        found = true;

                        System.out.println(
                                "Current Stock: " + p.stock);

                        System.out.print(
                                "Enter New Stock: ");

                        int newStock = sc.nextInt();

                        if (newStock < 0) {

                            System.out.println(
                                    "Invalid stock!");

                        } else {

                            p.stock = newStock;

                            System.out.println(
                                    "Stock updated successfully!");
                        }

                        break;
                    }
                }

                if (!found) {

                    System.out.println(
                            "Product not found!");
                }

            }

            // =========================================
            // 4. REMOVE PRODUCT
            // =========================================

            else if (choice == 4) {

                System.out.println("\n================================");
                System.out.println("         REMOVE PRODUCT");
                System.out.println("================================");

                System.out.print(
                        "Enter Product ID: ");

                int id = sc.nextInt();

                Product removeProduct = null;

                for (Product p : products) {

                    if (p.productId == id) {

                        removeProduct = p;
                        break;
                    }
                }

                if (removeProduct != null) {

                    products.remove(removeProduct);

                    System.out.println(
                            "Product removed successfully!");

                } else {

                    System.out.println(
                            "Product not found!");
                }

            }

            // =========================================
            // 5. CUSTOMER MANAGEMENT
            // =========================================

            else if (choice == 5) {

                while (true) {

                    System.out.println("\n================================");
                    System.out.println("      CUSTOMER MANAGEMENT");
                    System.out.println("================================");

                    System.out.println("1. Add Customer");
                    System.out.println("2. View Customers");
                    System.out.println("3. Search Customer");
                    System.out.println("4. Back to Admin Menu");

                    System.out.println("--------------------------------");
                    System.out.print(
                            "Enter your choice: ");

                    int customerChoice = sc.nextInt();

                    // ADD CUSTOMER

                    if (customerChoice == 1) {

                        System.out.println(
                                "\n================================");

                        System.out.println(
                                "          ADD CUSTOMER");

                        System.out.println(
                                "================================");

                        System.out.print(
                                "Enter Customer ID: ");

                        int customerId = sc.nextInt();

                        boolean duplicateCustomer = false;

                        for (Customer c : customers) {

                            if (c.customerId == customerId) {

                                duplicateCustomer = true;
                                break;
                            }
                        }

                        if (duplicateCustomer) {

                            System.out.println(
                                    "Customer ID already exists!");

                        } else {

                            sc.nextLine();

                            System.out.print(
                                    "Enter Customer Name: ");

                            String customerName = sc.nextLine();

                            System.out.print(
                                    "Enter Phone Number: ");

                            String phone = sc.nextLine();

                            if (customerName.trim()
                                    .isEmpty() ||
                                    phone.trim().isEmpty()) {

                                System.out.println(
                                        "Name or phone cannot be empty!");

                            } else {

                                Customer customer = new Customer(
                                        customerId,
                                        customerName,
                                        phone);

                                customers.add(customer);

                                System.out.println(
                                        "Customer added successfully!");
                            }
                        }

                    }

                    // VIEW CUSTOMERS

                    else if (customerChoice == 2) {

                        System.out.println(
                                "\n================================");

                        System.out.println(
                                "       CUSTOMER LIST");

                        System.out.println(
                                "================================");

                        if (customers.isEmpty()) {

                            System.out.println(
                                    "No customers available.");

                        } else {

                            System.out.println(
                                    "ID | Name | Phone");

                            System.out.println(
                                    "--------------------------------");

                            for (Customer c : customers) {

                                c.displayCustomer();
                            }
                        }

                    }

                    // SEARCH CUSTOMER

                    else if (customerChoice == 3) {

                        System.out.println(
                                "\n================================");

                        System.out.println(
                                "       SEARCH CUSTOMER");

                        System.out.println(
                                "================================");

                        System.out.print(
                                "Enter Customer ID: ");

                        int searchId = sc.nextInt();

                        boolean foundCustomer = false;

                        for (Customer c : customers) {

                            if (c.customerId == searchId) {

                                System.out.println(
                                        "\nCustomer Found!");

                                System.out.println(
                                        "Customer ID : "
                                                + c.customerId);

                                System.out.println(
                                        "Name        : "
                                                + c.name);

                                System.out.println(
                                        "Phone       : "
                                                + c.phone);

                                foundCustomer = true;
                                break;
                            }
                        }

                        if (!foundCustomer) {

                            System.out.println(
                                    "Customer not found!");
                        }

                    }

                    // BACK

                    else if (customerChoice == 4) {

                        break;

                    } else {

                        System.out.println(
                                "Invalid choice!");
                    }
                }

            }

            // =========================================
            // 6. SHOPPING / BILLING
            // =========================================

            else if (choice == 6) {

                ArrayList<Product> cartProducts = new ArrayList<>();

                ArrayList<Integer> cartQuantity = new ArrayList<>();

                double grandTotal = 0;

                while (true) {

                    System.out.println(
                            "\n================================");

                    System.out.println(
                            "       WELCOME TO SHOPPING MART");

                    System.out.println(
                            "================================");

                    System.out.println(
                            "\nAvailable Products:");

                    System.out.println(
                            "--------------------------------");

                    if (products.isEmpty()) {

                        System.out.println(
                                "No products available!");

                        break;
                    }

                    for (Product p : products) {

                        p.displayProduct();
                    }

                    System.out.println(
                            "\n--------------------------------");

                    System.out.println(
                            "1. Select Product");

                    System.out.println(
                            "2. View Cart");

                    System.out.println(
                            "3. Checkout");

                    System.out.println(
                            "4. Back to Admin Menu");

                    System.out.println(
                            "--------------------------------");

                    System.out.print(
                            "Enter your choice: ");

                    int shoppingChoice = sc.nextInt();

                    // SELECT PRODUCT

                    if (shoppingChoice == 1) {

                        System.out.print(
                                "Enter Product ID: ");

                        int id = sc.nextInt();

                        Product selectedProduct = null;

                        for (Product p : products) {

                            if (p.productId == id) {

                                selectedProduct = p;
                                break;
                            }
                        }

                        if (selectedProduct == null) {

                            System.out.println(
                                    "Invalid Product ID!");

                        } else {

                            System.out.println(
                                    "You selected: "
                                            + selectedProduct.productName);

                            if (selectedProduct.stock == 0) {

                                System.out.println(
                                        "Product is OUT OF STOCK!");

                            } else {

                                System.out.print(
                                        "Enter quantity: ");

                                int quantity = sc.nextInt();

                                if (quantity > 0 &&
                                        quantity <= selectedProduct.stock) {

                                    cartProducts.add(
                                            selectedProduct);

                                    cartQuantity.add(
                                            quantity);

                                    selectedProduct.stock -= quantity;

                                    grandTotal += selectedProduct.price
                                            * quantity;

                                    System.out.println(
                                            "Added to cart!");

                                    System.out.println(
                                            "Remaining Stock: "
                                                    + selectedProduct.stock);

                                } else {

                                    System.out.println(
                                            "Invalid quantity!");

                                    System.out.println(
                                            "Available stock: "
                                                    + selectedProduct.stock);
                                }
                            }
                        }

                    }

                    // VIEW CART

                    else if (shoppingChoice == 2) {

                        System.out.println(
                                "\n================================");

                        System.out.println(
                                "             CART");

                        System.out.println(
                                "================================");

                        if (cartProducts.isEmpty()) {

                            System.out.println(
                                    "Cart is empty!");

                        } else {

                            for (int i = 0; i < cartProducts.size(); i++) {

                                Product p = cartProducts.get(i);

                                int qty = cartQuantity.get(i);

                                double total = p.price * qty;

                                System.out.println(
                                        p.productName
                                                + " x" + qty
                                                + "    Rs." + total);
                            }

                            System.out.println(
                                    "--------------------------------");

                            System.out.println(
                                    "Grand Total : Rs."
                                            + grandTotal);
                        }

                        System.out.println(
                                "================================");

                    }

                    // CHECKOUT

                    else if (shoppingChoice == 3) {

                        if (cartProducts.isEmpty()) {

                            System.out.println(
                                    "\nCart is empty!");

                            System.out.println(
                                    "Please add products first.");

                        } else {

                            sc.nextLine();

                            System.out.print(
                                    "\nEnter Customer Name: ");

                            String customerName = sc.nextLine();

                            System.out.print(
                                    "Enter Phone Number: ");

                            String phoneNumber = sc.nextLine();

                            System.out.println(
                                    "\nSelect Payment Method:");

                            System.out.println(
                                    "1. Cash");

                            System.out.println(
                                    "2. UPI");

                            System.out.println(
                                    "3. Card");

                            System.out.println(
                                    "--------------------------------");

                            System.out.print(
                                    "Enter your choice: ");

                            int paymentChoice = sc.nextInt();

                            String paymentMethod;

                            if (paymentChoice == 1) {

                                paymentMethod = "Cash";

                            } else if (paymentChoice == 2) {

                                paymentMethod = "UPI";

                            } else if (paymentChoice == 3) {

                                paymentMethod = "Card";

                            } else {

                                paymentMethod = "Invalid";
                            }

                            if (paymentMethod.equals(
                                    "Invalid")) {

                                System.out.println(
                                        "Invalid payment choice!");

                                System.out.println(
                                        "Checkout cancelled.");

                                continue;
                            }

                            System.out.println(
                                    "\nPayment Successful!");

                            // SAVE SALE

                            Sale newSale = new Sale(
                                    customerName,
                                    grandTotal,
                                    paymentMethod);

                            sales.add(newSale);

                            // FINAL BILL

                            System.out.println(
                                    "\n================================");

                            System.out.println(
                                    "          FINAL BILL");

                            System.out.println(
                                    "================================");

                            System.out.println(
                                    "Customer Name : "
                                            + customerName);

                            System.out.println(
                                    "Phone Number  : "
                                            + phoneNumber);

                            System.out.println(
                                    "--------------------------------");

                            for (int i = 0; i < cartProducts.size(); i++) {

                                Product p = cartProducts.get(i);

                                int qty = cartQuantity.get(i);

                                double total = p.price * qty;

                                System.out.println(
                                        p.productName
                                                + " x" + qty
                                                + "    Rs." + total);
                            }

                            System.out.println(
                                    "--------------------------------");

                            System.out.println(
                                    "TOTAL AMOUNT   : Rs."
                                            + grandTotal);

                            System.out.println(
                                    "Payment Method : "
                                            + paymentMethod);

                            System.out.println(
                                    "Payment Status : SUCCESS");

                            System.out.println(
                                    "================================");

                            System.out.println(
                                    "          THANK YOU!");

                            System.out.println(
                                    "================================");

                            break;
                        }

                    }

                    // BACK

                    else if (shoppingChoice == 4) {

                        break;

                    } else {

                        System.out.println(
                                "Invalid choice!");
                    }
                }

            }

            // =========================================
            // 7. SALES REPORT
            // =========================================

            else if (choice == 7) {

                System.out.println(
                        "\n================================");

                System.out.println(
                        "          SALES REPORT");

                System.out.println(
                        "================================");

                if (sales.isEmpty()) {

                    System.out.println(
                            "No sales available.");

                } else {

                    System.out.println(
                            "Customer | Amount | Payment");

                    System.out.println(
                            "--------------------------------");

                    double totalSales = 0;

                    for (Sale s : sales) {

                        s.displaySale();

                        totalSales += s.amount;
                    }

                    System.out.println(
                            "--------------------------------");

                    System.out.println(
                            "Total Sales : Rs."
                                    + totalSales);
                }

                System.out.println(
                        "================================");

            }

            // =========================================
            // 8. EXIT
            // =========================================

            else if (choice == 8) {

                System.out.println(
                        "\nThank you for using Shopping Mart!");

                break;

            } else {

                System.out.println(
                        "Invalid choice!");
            }
        }

        sc.close();
    }
}