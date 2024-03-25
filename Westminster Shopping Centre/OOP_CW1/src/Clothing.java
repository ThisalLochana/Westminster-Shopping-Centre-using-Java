public class Clothing extends Product { // Clothing class representing a type of product with additional size and color attributes
        String size; // Declare variable for Size of the clothing
        String color; // Declare variable for Color of the clothing

    // Constructor to initialize the Clothing object with specific attributes
    public Clothing(String productID, String productName, int availableItems, double price, String size, String color) {
        super(productID, productName, availableItems, price);
        this.size = size;
        this.color = color;
    }

    public String getSize() { // Getter method for retrieving the size of the clothing
        return size;
    }

    public String getColor() { // Getter method for retrieving the color of the clothing
        return color;
    }

    public void setSize(String size) {  // Setter method for updating the size of the clothing
        this.size = size;
    }

    public void setColor(String color) { // Setter method for updating the color of the clothing
        this.color = color;
    }
}