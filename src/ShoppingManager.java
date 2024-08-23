import java.util.List;

// Interface defining methods for managing products in a shopping system
public interface ShoppingManager {

    // Add a product to the system
    void addProduct(Product product);

    // Delete a product from the system based on its ID
    void deleteProduct(String productId);

    // Print the list of products in the system
    void printProductList();

    // Save the current list of products to a file
    void saveProductsToFile();

    // Load products from a file into the system
    void loadProductsFromFile();

    // Get the current list of products in the system
    List<Product> getProductList();
}
