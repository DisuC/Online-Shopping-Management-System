import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;



public class ShoppingGUI extends JFrame {
    private JComboBox<String> productTypeComboBox;
    private JTable productTable;
    private JTextArea productDetailsTextArea;
    private JButton addToCartButton;
    private JButton viewShoppingCartButton;
    private JScrollPane tableScrollPane;

    private List<Product> cartList;
    private List<Product> productList;

    // Constructor initializing the ShoppingGUI with a list of products
    public ShoppingGUI(List<Product> products) {
        this.productList = products;
        this.cartList = new ArrayList<>();

        initializeGUI();
        populateTable("all");

        // Add listener for selecting rows in the product table
        productTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                displayProductDetails(productList.get(selectedRow));
            }
        });
    }

    // Display product details in the JTextArea
    private void displayProductDetails(Product product) {
        String details = "Product ID: " + product.getProductId() + "\n" +
                "Product Name: " + product.getProductName() + "\n" +
                "Available Quantity: " + product.getAvailableQuantity() + "\n" +
                "Product Price: " + product.getProductPrice();
        productDetailsTextArea.setText(details);
    }

    // Set a new list of products and repopulate the table
    public void setProductList(List<Product> products) {
        this.productList = products;
        populateTable("all");
    }

    // Initialize the graphical user interface
    private void initializeGUI() {
        setTitle("Shopping GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setup JComboBox for selecting product types
        String[] productTypes = {"All", "Electronics", "Clothes"};
        productTypeComboBox = new JComboBox<>(productTypes);
        productTypeComboBox.addActionListener(e -> {
            String selectedType = (String) productTypeComboBox.getSelectedItem();
            populateTable(selectedType);
        });

        // Setup JTable for displaying products
        productTable = new JTable();
        tableScrollPane = new JScrollPane(productTable);

        // Setup JTextArea for displaying product details
        productDetailsTextArea = new JTextArea();
        productDetailsTextArea.setEditable(false);

        // Setup JButtons for adding to the cart and viewing the shopping cart
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                Product selectedProduct = productList.get(selectedRow);
                cartList.add(selectedProduct);
                JOptionPane.showMessageDialog(ShoppingGUI.this, "Product added to the cart");
            } else {
                JOptionPane.showMessageDialog(ShoppingGUI.this, "Please select a product to add to the cart",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        viewShoppingCartButton = new JButton("View Cart");
        viewShoppingCartButton.addActionListener(e -> openCartGUI());

        // Setup JPanel for layout
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Select Product Type:"));
        controlPanel.add(productTypeComboBox);
        controlPanel.add(viewShoppingCartButton);

        JPanel productDetailsPanel = new JPanel();
        productDetailsPanel.setLayout(new BoxLayout(productDetailsPanel, BoxLayout.Y_AXIS));
        productDetailsPanel.add(productDetailsTextArea);
        productDetailsPanel.add(addToCartButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(productDetailsPanel, BorderLayout.SOUTH);

        add(controlPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    // Populate the product table based on the selected product type
    private void populateTable(String productType) {
        if (productList == null) {
            return;
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Quantity");
        model.addColumn("Price");

        for (Product product : productList) {
            boolean isAll = "all".equals(productType);
            boolean isElectronics = "Electronics".equals(productType) && product instanceof Electronics;
            boolean isClothes = "Clothes".equals(productType) && product instanceof Clothing;

            if (isAll || isElectronics || isClothes) {
                Object[] rowData = {product.getProductId(), product.getProductName(),
                        product.getAvailableQuantity(), product.getProductPrice()};
                model.addRow(rowData);
            }
        }

        productTable.setModel(model);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        productTable.setRowSorter(sorter);
    }

    // Open the shopping cart GUI in a new thread
    private void openCartGUI() {
        SwingUtilities.invokeLater(() -> new ShoppingCartGUI(cartList).setVisible(true));
    }

    // Main method to create the WestminsterShoppingManager, load products, and create the GUI
    public static void main(String[] args) {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        manager.loadProductsFromFile();

        SwingUtilities.invokeLater(() -> {
            ShoppingGUI shoppingGUI = new ShoppingGUI(manager.getProductList());
            shoppingGUI.setVisible(true);
        });
    }

    // Get the list of products in the shopping cart
    public List<Product> getShoppingCartList() {
        return cartList;
    }
}

