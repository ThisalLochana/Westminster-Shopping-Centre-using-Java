import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    public List<Product> shoppingCart;
    double totalCost;
    double firstPersonDiscount;
    double threeItemSameCategoryDiscount;
    private Map<String, Integer> categoryCountMap; //to keep track of the count of items for each category
    public ShoppingCart() {
        this.shoppingCart = new ArrayList<>();
        this.categoryCountMap = new HashMap<>();
    }

    public void addProduct(Product product){ // Method to add a product to the shopping cart
        if (!shoppingCart.contains(product)){
            shoppingCart.add(product);
        }
        product.incrementQuantity();
        calculateTotalCost(); //Recalculate total cost immediately
        updateCategoryCount(product); //Update the category count
        calculateDiscounts(); //Recalculate discount immediately
    }

    public void removeProduct(Product product){ // Method to remove a product from the shopping cart
        shoppingCart.remove(product);
        calculateTotalCost(); // Recalculate total cost immediately
        updateCategoryCount(product); // Update category count
        calculateDiscounts(); // Recalculate discounts immediately
    }

    // Method to update the category count
    private void updateCategoryCount(Product product) { //method is called when adding or removing a product to update the category count
        if (product instanceof Clothing || product instanceof Electronic) {
            String category = (product instanceof Clothing) ? "Clothing" : "Electronic";
            categoryCountMap.put(category, categoryCountMap.getOrDefault(category, 0) + 1);
        }
    }

    private void calculateTotalCost() { // Method to calculate the total cost of items in the shopping cart
        totalCost = 0;
        for (Product product : shoppingCart) {
            totalCost += product.getPrice() * product.getQuantity();
        }
    }

    private void calculateDiscounts() { // Method to calculate various discounts
        calculateFirstPersonDiscount();
        calculateThreeItemSameCategoryDiscount();
    }

    private void calculateFirstPersonDiscount() { // Method to calculate the first person discount
        firstPersonDiscount = -(0.1 * totalCost);
    }

    private void calculateThreeItemSameCategoryDiscount() { //Method checks the category count map and applies the discount if a category has 3 or more items
        threeItemSameCategoryDiscount = 0;
        for (int count : categoryCountMap.values()) {
            if (count >= 3) {
                threeItemSameCategoryDiscount = -(0.2 * totalCost);
                break; // Only apply discount once, even if multiple categories have 3 or more items
            }
        }
    }

    // Method to calculate the final total including discounts
    public double calculateFinalTotal() { //method is modified to include discounts
        double finalTotal = totalCost + firstPersonDiscount + threeItemSameCategoryDiscount;
        return Math.max(0, finalTotal); // Ensure the final total is non-negative
    }

    public double getTotalCost() { // Getter method to retrieve the total cost
        return totalCost;
    }

    public double getFirstPersonDiscount() { // Getter method to retrieve the first person discount
        return firstPersonDiscount;
    }

    public double getThreeItemSameCategoryDiscount() { // Getter method to retrieve the three-item same category discount
        return threeItemSameCategoryDiscount;
    }
}
