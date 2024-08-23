import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;



public class WestminsterShoppingManager implements ShoppingManager {
    private List<Product> productList;
    private Scanner scanner;

    // Constructor initializing WestminsterShoppingManager with an empty product list and a scanner for user input
    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    @Override
    // Add a product to the product list
    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    // Delete a product from the product list based on its ID
    public void deleteProduct(String productId) {
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                productList.remove(product);
                System.out.println("Product deleted: " + product);
                System.out.println("Total number of products left: " + productList.size());
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
    }

    @Override
    // Print the list of products in the system sorted alphabetically by product ID
    public void printProductList() {
        Collections.sort(productList, (p1, p2) -> p1.getProductId().compareToIgnoreCase(p2.getProductId()));

        for (Product product : productList) {
            System.out.println(product);
            if (product instanceof Electronics) {
                System.out.println("Product Type: Electronics");
            } else if (product instanceof Clothing) {
                System.out.println("Product Type: Clothing");
            }
            System.out.println("------------");
        }
    }

    // Save the current list of products to a file using object serialization
    public void saveProductsToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("products.txt"))) {
            outputStream.writeObject(productList);
            System.out.println("Products saved to the file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving products to the file!");
        }
    }

    // Load products from a file into the system using object deserialization
    public void loadProductsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.txt"))) {
            productList = (ArrayList<Product>) ois.readObject();
            System.out.println("Products loaded from the file: " + productList.size());
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting with an empty product list.");
            productList = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading products from the file!");
            e.printStackTrace();
        }
    }

    @Override
    // Get the current list of products in the system
    public List<Product> getProductList() {
        return productList;
    }

    // Create a new product based on user input
    private Product createProduct(String productType) {
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter available items: ");
        int availableItems = scanner.nextInt();
        System.out.print("Enter product price: ");
        double productPrice = scanner.nextDouble();
        scanner.nextLine();

        if ("Electronics".equalsIgnoreCase(productType) || "e".equalsIgnoreCase(productType)) {
            System.out.print("Enter brand: ");
            String brand = scanner.nextLine();
            System.out.print("Enter warranty period: ");
            int warrantyPeriod = scanner.nextInt();
            return new Electronics(productId, productName, availableItems, productPrice, brand, warrantyPeriod);
        } else if ("Clothing".equalsIgnoreCase(productType) || "c".equalsIgnoreCase(productType)) {
            System.out.print("Enter size: ");
            String size = scanner.nextLine();
            System.out.print("Enter colour: ");
            String colour = scanner.nextLine();
            return new Clothing(productId, productName, availableItems, productPrice, size, colour);
        } else {
            System.out.println("Invalid product type.");
            return null;
        }
    }

    // Display the menu options for the Westminster Shopping Manager
    public void displayMenu() {
        System.out.println("===== Westminster Shopping Manager Menu =====");
        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print the list of products");
        System.out.println("4. Save products to the file");
        System.out.println("5. Load products from the file");
        System.out.println("6. Open GUI");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    // Open the GUI in a new thread
    public void openGUI() {
        loadProductsFromFile(); // Ensure products are loaded before opening the GUI

        SwingUtilities.invokeLater(() -> {
            ShoppingGUI shoppingGUI = new ShoppingGUI(productList);
            shoppingGUI.setVisible(true);
        });
    }

    // Main method to interact with the Westminster Shopping Manager
    public static void main(String[] args) {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        boolean exit = false;

        while (!exit) {
            shoppingManager.displayMenu();
            int choice = shoppingManager.scanner.nextInt();
            shoppingManager.scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter product type (Electronics / Clothing): ");
                    String productType = shoppingManager.scanner.nextLine();
                    Product product = shoppingManager.createProduct(productType);
                    if (product != null) {
                        shoppingManager.addProduct(product);
                        System.out.println("Product added successfully.");
                    }
                    break;
                case 2:
                    System.out.print("Enter product ID to delete: ");
                    String productIdToDelete = shoppingManager.scanner.nextLine();
                    shoppingManager.deleteProduct(productIdToDelete);
                    break;
                case 3:
                    shoppingManager.printProductList();
                    break;
                case 4:
                    shoppingManager.saveProductsToFile();
                    break;
                case 5:
                    shoppingManager.loadProductsFromFile();
                    break;
                case 6:
                    shoppingManager.openGUI();
                    System.out.println("Open GUI");
                    break;
                case 7:
                    System.out.println("Exiting from Westminster Shopping Manager.");
                    System.exit(0);
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid selection ! Please input a valid number within the range of 1 to 6.");
            }
        }
    }

}








