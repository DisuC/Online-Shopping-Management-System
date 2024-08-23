public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;

    //create a constructor
    public Electronics(String productId, String productName, int quantity, double productPrice, String brand, int warrantyPeriod) {
        super(productId, productName, quantity, productPrice);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // getter and setter method for product brand
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // getter and setter method for warranty period
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    // Override toString method for electronics details
    @Override
    public String toString() {
        return "Electronics{" +
                " Product Brand='" + brand + '\'' +
                ", Warranty Period=" + warrantyPeriod +
                "} " + super.toString();
    }
}
