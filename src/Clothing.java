public class Clothing extends Product {
    private String size;
    private String colour;

    //create a constructor
    public Clothing(String productId, String productName, int availableQuantity, double productPrice, String size, String colour) {
        super(productId, productName, availableQuantity, productPrice);
        this.size = size;
        this.colour = colour;
    }

    // getter and setter method for product size
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    // getter and setter method for product colour
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    // Override toString method for clothing details
    @Override
    public String toString() {
        return "Clothing{" +
                " Size='" + size + '\'' +
                ", Colour='" + colour + '\'' +
                "} " + super.toString();
    }
}
