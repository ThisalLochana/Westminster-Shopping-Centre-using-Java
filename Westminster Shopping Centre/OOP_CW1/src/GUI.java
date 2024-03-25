import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI extends WestminterShoppingManager implements ActionListener {
    // Declare variables
    private List<Product> products;
    private ShoppingCart shoppingCart; // Declare shoppingCart
    private DefaultTableModel cartTableModel; //Declare cartTableModel

    //Constructor
    public GUI(List<Product> products) {
        this.products = products;
        this.shoppingCart = new ShoppingCart(); // Initialize shoppingCart
    }

    // Declare UI components
    JButton shoppingCartButton;
    JComboBox<String> categoryBox;
    JButton addToShoppingCart;
    DefaultTableModel tableModel;
    Product cartProduct;
    double calculatedDiscountValue;

    // Update the cart panel with the latest data
    private void updateCartPanel() {
        if (cartTableModel != null){
            cartTableModel.setRowCount(0); // Clear existing data

            // Populate cart table with shopping cart items
            for (Product cartProduct : shoppingCart.shoppingCart) {
                double priceForRow = cartProduct.getQuantity() * cartProduct.getPrice();
                if (cartProduct instanceof Clothing) {
                    // Add row for Clothing
                    Object[] rowData = {cartProduct.getProductID() + ", " + cartProduct.getProductName() + ", " + ((Clothing) cartProduct).getSize() + ", " + ((Clothing) cartProduct).getColor(), cartProduct.getQuantity(), priceForRow};
                    cartTableModel.addRow(rowData);
                } else if (cartProduct instanceof Electronic) {
                    // Add row for Electronic
                    Object[] rowData = {cartProduct.getProductID() + ", " + cartProduct.getProductName() + ", " + ((Electronic) cartProduct).getBrand() + ", " + ((Electronic) cartProduct).getWarrantyPeriod(), cartProduct.getQuantity(), priceForRow};
                    cartTableModel.addRow(rowData);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    // Method to display the main interface
    public void showInterface(){
        JFrame frame = new JFrame();
        frame.setTitle("Westminster Shopping Centre");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(new GridLayout(5,1));

        // Panel 1: Shopping Cart Button
        JPanel panel1 = new JPanel( new FlowLayout(FlowLayout.RIGHT)); //Creating the panel1

        shoppingCartButton = new JButton("Shopping Cart"); //Creating the shopping cart button
        frame.add(panel1);
        panel1.add(shoppingCartButton);

        // Add ActionListener to Shopping Cart Button
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == shoppingCartButton){
                    //Create a new JFrame for the shopping Cart
                    JFrame cartFrame = new JFrame("Shopping Cart");
                    cartFrame.setLayout(new BoxLayout(cartFrame.getContentPane(), BoxLayout.Y_AXIS));
                    cartFrame.setVisible(true);
                    cartFrame.setSize(700,600);

                    // Panel 1: Cart Items Table
                    JPanel cartPanel1 = new JPanel();
                    cartPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));

                    // Define column names for the table
                    String[] cartColumnNames = {"<html><b>Product<b><html>", "<html><b>Quantity<html><b>", "<html><b>Price<html><b>"};

                    // Create a DefaultTableModel with column names and zero rows
                    cartTableModel = new DefaultTableModel(cartColumnNames, 0);

                    // Add all products from the shopping cart to the table
                    for (int i=0; i<shoppingCart.shoppingCart.size(); i++) {
                        cartProduct = shoppingCart.shoppingCart.get(i);

                        //Calculating price for row
                        double priceForRow = cartProduct.getQuantity()*cartProduct.getPrice();
                        if (cartProduct instanceof Clothing){
                            // Add row for Clothing
                            Object[] rowData = {cartProduct.getProductID() + ", "+ cartProduct.getProductName() + ", "+ ((Clothing) cartProduct).getSize() + ", " + ((Clothing) cartProduct).getColor(), cartProduct.getQuantity(), "Rs." + priceForRow + "/="};
                            cartTableModel.addRow(rowData);
                        } else if (cartProduct instanceof Electronic) {
                            // Add row for Electronic
                            Object[] rowData = {cartProduct.getProductID() + ", "+ cartProduct.getProductName() + ", "+ ((Electronic) cartProduct).getBrand() + ", " + ((Electronic) cartProduct).getWarrantyPeriod(), cartProduct.getQuantity(), "Rs." + priceForRow + "/="};
                            cartTableModel.addRow(rowData);
                        }
                    }

                    // Create a JTable with the populated DefaultTableModel
                    JTable cartTable = new JTable(cartTableModel);
                    // Add a JScrollPane containing the JTable to panel3
                    cartPanel1.add(new JScrollPane(cartTable), BorderLayout.CENTER);

                    // Create a custom TableCellRenderer to center text
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                    centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                    // Set the custom TableCellRenderer to all columns
                    for (int i=0; i<cartTable.getColumnCount(); i++){
                        cartTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }

                    // Panel 2: Cart Summary
                    JPanel cartPanel2 = new JPanel();
                    cartPanel2.setLayout(new BoxLayout(cartPanel2,BoxLayout.Y_AXIS));
                    cartPanel2.setBorder(new EmptyBorder(0,60,0,0));

                    JLabel totalLabel = new JLabel("Total ");
                    JLabel firstPersonDiscountLabel = new JLabel("First Person Discount(10%) ");
                    JLabel threeItemSameCategoryDiscountLabel = new JLabel("Three Items in a same Category Discount(20%) ");
                    JLabel finalTotalLabel = new JLabel("Final Total ");

                    cartPanel2.add(totalLabel);
                    cartPanel2.add(firstPersonDiscountLabel);
                    cartPanel2.add(threeItemSameCategoryDiscountLabel);
                    cartPanel2.add(finalTotalLabel);

                    // Panel 3: Cart Summary Values
                    JPanel cartPanel3 = new JPanel();
                    cartPanel3.setLayout(new BoxLayout(cartPanel3,BoxLayout.Y_AXIS));

                    JLabel totalLabel1 = new JLabel("Rs." + shoppingCart.getTotalCost() + "/=");
                    JLabel firstPersonDiscountLabel1 = new JLabel("Rs.(" + shoppingCart.getFirstPersonDiscount() + ")/=");
                    JLabel threeItemSameCategoryDiscountLabel1 = new JLabel("Rs.(" + shoppingCart.getThreeItemSameCategoryDiscount() + ")/=");
                    JLabel finalTotalLabel1 = new JLabel("Rs." + shoppingCart.calculateFinalTotal() + "/=");

                    cartPanel3.add(totalLabel1);
                    cartPanel3.add(firstPersonDiscountLabel1);
                    cartPanel3.add(threeItemSameCategoryDiscountLabel1);
                    cartPanel3.add(finalTotalLabel1);

                    // Panel 4: Cart Summary Combined
                    JPanel cartPanel4 = new JPanel();
                    cartPanel4.setLayout(new GridLayout(1,2));

                    // Add panel 2 and panel 3 to the panel4
                    cartPanel4.add(cartPanel2);
                    cartPanel4.add(cartPanel3);

                    // Add panels to the cart frame
                    cartFrame.add(cartPanel1);
                    cartFrame.add(cartPanel4);
                }

            }
        });

        // Panel 2: Product Category Dropdown
        JPanel panel2 = new JPanel(new FlowLayout());

        JLabel selectProductCategory = new JLabel("Select Product Category  "); //Create select product category label
        frame.add(panel2); //Add panel2 into frame
        panel2.add(selectProductCategory);

        // Dropdown categories
        String[] categories = {"All", "Electronics", "Clothing"}; //Create category named array
        categoryBox = new JComboBox<>(categories); //Add array into Dropdown list
        // Add an ActionListener to the JComboBox
        categoryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected category
                String selectedCategory = (String) categoryBox.getSelectedItem();

                // Clear the table
                tableModel.setRowCount(0);

                // Add data to the table model based on selected category
                for (int i=0; i<products.size(); i++){
                    Product product = products.get(i);
                    if (selectedCategory.equals("All") ||
                            (selectedCategory.equals("Clothing") && products.get(i) instanceof Clothing) ||
                            (selectedCategory.equals("Electronics") && products.get(i) instanceof Electronic)) {
                        // Add product to table
                        Object[] rowData = new Object[0];
                        if (product instanceof Clothing){
                            // Add row for Clothing
                            rowData = new Object[]{product.getProductID(), product.getProductName(), "Clothing", product.getPrice(), ((Clothing) product).getSize() + " ," + ((Clothing) product).getColor()};
                        } else if (product instanceof Electronic) {
                            // Add row for Electronic
                            rowData = new Object[]{product.getProductID(), product.getProductName(), "Electronics", product.getPrice(), ((Electronic) product).getBrand() + " ," + ((Electronic) product).getWarrantyPeriod()};
                        }
                        tableModel.addRow(rowData);
                    }
                }
            }
        });
        panel2.add(categoryBox); //Add Dropdown List into panel2

        // Initialize panel3 with BorderLayout
        JPanel panel3 = new JPanel(new BorderLayout());

        // Define column names for the table
        String[] columnNames = {"<html><b>Product ID<b><html>", "<html><b>Name<html><b>", "<html><b>Category<html><b>", "<html><b>Price(Rs)<html><b>","<html><b>Info<html><b>"};

        // Create a DefaultTableModel with column names and zero rows
        tableModel = new DefaultTableModel(columnNames, 0);

        // Add data to the table model
        for (int i=0; i<products.size(); i++){
            if (products.get(i) instanceof Clothing){
                // Add row for Clothing
                Object[] rowData = {products.get(i).getProductID(), products.get(i).getProductName(), "Clothing", products.get(i).getPrice(), ((Clothing) products.get(i)).getSize() + ", " + ((Clothing) products.get(i)).getColor()};
                tableModel.addRow(rowData);
            }else if(products.get(i) instanceof Electronic){
                // Add row for Electronic
                Object[] rowData = {products.get(i).getProductID(), products.get(i).getProductName(), "Electronic", products.get(i).getPrice(), ((Electronic) products.get(i)).getBrand() + ", " + ((Electronic) products.get(i)).getWarrantyPeriod()};
                tableModel.addRow(rowData);
            }
        }

        // Create a JTable with the populated DefaultTableModel
        JTable productTable = new JTable(tableModel);

        // Create a custom TableCellRenderer to center text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Set the custom TableCellRenderer to all columns
        for (int i=0; i<productTable.getColumnCount(); i++){
            productTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add a JScrollPane containing the JTable to panel3
        panel3.add(new JScrollPane(productTable), BorderLayout.CENTER);
        panel3.setBorder(new EmptyBorder(0,100,0,100));

        // Add panel3 to frame
        frame.add(panel3);

        // Panel 4: Product Details
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4,BoxLayout.Y_AXIS));
        panel4.setBorder(new EmptyBorder(10,100,0,100));

        // Add a ListSelectionListener to the table
        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                //Get the selected row index
                int selectRow = productTable.getSelectedRow();

                //Check if a valid row is selected
                if(selectRow>=0){
                    //Get the corresponding product from the product ArrayList
                    Product selectedProduct = products.get(selectRow);
                    panel4.removeAll();

                    // Display the product details in the panel
                    panel4.add(new JLabel("Selected Product- Details"));
                    //Display the product details in the panel
                    panel4.add( new JLabel("Product ID: " + selectedProduct.getProductID()));
                    if (selectedProduct instanceof Clothing){
                        panel4.add(new JLabel("Category: Clothing"));
                    }else {
                        panel4.add(new JLabel("Category: Electronic"));
                    }
                    panel4.add( new JLabel("Name: " + selectedProduct.getProductName()));
                    if (selectedProduct instanceof Clothing){
                        panel4.add(new JLabel("Size: " + ((Clothing) selectedProduct).getSize()));
                        panel4.add(new JLabel("Color: " + ((Clothing) selectedProduct).getColor()));
                    }else if(selectedProduct instanceof Electronic){
                        panel4.add(new JLabel("Brand: " + ((Electronic) selectedProduct).getBrand()));
                        panel4.add(new JLabel("Warranty Period: " + ((Electronic) selectedProduct).getWarrantyPeriod() + " weeks warranty"));
                    }
                    panel4.add(new JLabel("Items Available : " + selectedProduct.getAvailableItems()));
                }
                panel4.revalidate();//Called when components are added, removed, or modified dynamically after the initial layout has been done
                panel4.repaint();//Used to update the visual appearance of a component, such as when the data it displays has changed
            }
        });

        frame.add(panel4);

        //Panel 5: add to cart button and options
        JPanel panel5 = new JPanel();
        addToShoppingCart = new JButton("Add to Shopping Cart");
        addToShoppingCart.setFocusable(false);
        panel5.add(addToShoppingCart);
        frame.add(panel5);

        //Add an ActionListener to the addToShoppingCart button
        addToShoppingCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Get the selected row index
                int selectedRow = productTable.getSelectedRow();
                //Check if a valid row is selected
                if (selectedRow>=0){
                    //Get the corresponding product from the shoppingCart ArrayLists
                    Product selectedProduct = products.get(selectedRow);

                    //Add the products to the shopping cart
                    shoppingCart.addProduct(selectedProduct);
                }
                updateCartPanel(); // Update the cart panel with the latest data
            }
        });
        frame.setVisible(true);
        frame.pack();
    }
}