public interface ShoppingManager { // Interface for managing shopping-related operations
    void addProduct(Product product); // Add a product to the system
    void deleteProduct(Product product); // Delete a product from the system
    void printProduct(); // Print details of products
    void productToFile(); // Save product information to a file
    void loadProductFile(); // Load product information from a file
}