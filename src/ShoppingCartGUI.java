import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShoppingCartGUI extends JFrame {

    private List<Product> shoppingCart;
    private JTable cartTable;
    private JLabel totalLabel;
    double totalPrice = 0;

    // Constructor
    public ShoppingCartGUI(List<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
        initializeGUI();
    }

    // GUI initialization method
    private void initializeGUI() {
        setTitle("Shopping Cart");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Table setup
        String[] columnNames = {"Product", "Quantity", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        cartTable = new JTable(tableModel);

        // Total label setup
        totalLabel = new JLabel("Total: ");

        // Layout setup
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(cartTable), BorderLayout.CENTER);
        mainPanel.add(totalLabel, BorderLayout.SOUTH);

        add(mainPanel);
        populateCartTable();
    }

    // Populates the shopping cart table
    private void populateCartTable() {
        DefaultTableModel tableModel = (DefaultTableModel) cartTable.getModel();
        totalPrice = 0;

        if (shoppingCart == null || shoppingCart.isEmpty()) {
            tableModel.addRow(new Object[]{"No products in the cart", 0, 0.0});
        } else {
            for (Product product : shoppingCart) {
                tableModel.addRow(new Object[]{
                        product.getProductId() + ", " + product.getProductName() + ", " + getType(product),
                        1,
                        product.getProductPrice()
                });

                totalPrice += product.getProductPrice();
            }
        }

        // Update the total label
        updateTotalLabel(totalPrice);
    }

    // Determines the type of product (Electronics, Clothing, or Unknown Type)
    private String getType(Product product) {
        if (product instanceof Electronics) {
            return "Electronics";
        } else if (product instanceof Clothing) {
            return "Clothing";
        }
        return "Unknown Type";
    }

    // Updates the total label with the current total price
    private void updateTotalLabel(double totalPrice) {
        if (totalLabel != null) {
            totalLabel.setText("Total Price: $" + totalPrice);
        }
    }

    // Main method to create an instance of ShoppingCartGUI
    public static void main(String[] args) {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        manager.loadProductsFromFile();
        manager.openGUI();
        ShoppingGUI shoppingGUI = new ShoppingGUI(manager.getProductList());

        SwingUtilities.invokeLater(() -> {
            new ShoppingCartGUI(shoppingGUI.getShoppingCartList()).setVisible(true);
        });
    }
}
