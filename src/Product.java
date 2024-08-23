import java.io.Serializable;

public abstract class Product implements Serializable {
    private String productId;
    private String productName;
    private int availableQuantity;
    private double productPrice;

    //create a constructor
    public Product(String productId, String productName, int availableQuantity, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.availableQuantity = availableQuantity;
        this.productPrice = productPrice;
    }

    // getter and setter method for product Id
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }

    // getter and setter method for product name
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // getter and setter method for no of available items
    public int getAvailableQuantity() {
        return availableQuantity;
    }
    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    // getter and setter method for product price
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    // Override toString method for product details
    @Override
    public String toString() {
        return "Product{" +
                " Product Id='" + productId + '\'' +
                ", Product Name='" + productName + '\'' +
                ", Available Quantity=" + availableQuantity +
                ", Product Price=" + productPrice +
                '}';
    }
}
