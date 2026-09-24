public class Product {

    int productId;
    String productName;
    double price;
    int stock;

    public Product(int productId, String productName, double price, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public void displayProduct() {
        System.out.println(
            productId + " | " +
            productName + " | ₹" +
            price + " | Stock: " +
            stock
        );
    }
}