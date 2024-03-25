import java.io.Serializable;

public abstract class Product implements Serializable { // Abstract class representing a general product with common attributes
    // Declare variables
    private String productID; // Unique identifier for the product
    private String productName; // Name of the product
    private int availableItems; // Number of available items in stock
    private double price; // Price of the product
    private int quantity; // Quantity of the product in the shopping cart

    // Constructor to initialize the Product object with specific attributes
    public Product(String productID, String productName, int availableItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
        this.quantity =0; // Initialize quantity to zero
    }

    public String getProductID(){  // Getter method for retrieving the product ID
        return productID;
    }

    public String getProductName() { // Getter method for retrieving the product name
        return productName;
    }

    public int getAvailableItems() { // Getter method for retrieving the number of available items
        return availableItems;
    }

    public double getPrice() {  // Getter method for retrieving the price of the product
        return price;
    }

    public int getQuantity(){ // Getter method for retrieving the quantity of the product in the shopping cart
        return quantity;
    }

    public void setProductID(String productID){ // Setter method for updating the product ID
        this.productID = productID;
    }

    public void incrementQuantity(){  // Method to increment the quantity of the product in the shopping cart
        this.quantity++;
    }

    public void setProductName(String productName) {  // Setter method for updating the product name
        this.productName = productName;
    }

    public void setAvailableItems(int availableItems) { // Setter method for updating the number of available items
        this.availableItems = availableItems;
    }

    public void setPrice(double price) {  // Setter method for updating the price of the product
        this.price = price;
    }
}
