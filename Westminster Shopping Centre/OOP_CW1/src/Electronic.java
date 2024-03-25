public class Electronic extends Product{
    private String brand;
    private int warrantyPeriod;

    // Constructor to initialize Electronic object with relevant attributes
    public Electronic(String productID, String productName, int availableItems, double price, String brand, int warrantyPeriod) {
        super(productID, productName, availableItems, price); // Call the constructor of the superclass (Product)
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }


    public String getBrand() { // Getter method to retrieve the brand of the electronic product
        return brand;
    }

    public int getWarrantyPeriod() { // Getter method to retrieve the warranty period of the electronic product
        return warrantyPeriod;
    }

    public void setBrand(String brand) {  // Setter method to update or set the brand of the electronic product
        this.brand = brand;
    }

    public void setWarrantyPeriod(int warrantyPeriod) { // Setter method to update or set the warranty period of the electronic product
        this.warrantyPeriod = warrantyPeriod;
    }
}