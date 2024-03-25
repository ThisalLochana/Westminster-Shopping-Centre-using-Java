import java.io.*;
import java.util.*;

public class WestminterShoppingManager implements ShoppingManager{
    public List<Product> products; // List to store products

    private static final int max_products = 50; // Maximum number of products allowed

    // Constructor to initialize the product list
    public WestminterShoppingManager (){
        this.products = new ArrayList<>();
    }

    // Main method to display the menu and handle user actions
    public void displayMenu(){
        String productID;
        String productName;
        int availableItems;
        double price;

        Scanner input = new Scanner(System.in);
        loadProductFile();
        while (true){
            int option;
            while (true){
                try{
                    System.out.print("\n---------------------------Menu---------------------------\n" +
                            "1. Add a new product\n" +
                            "2. Delete a product\n" +
                            "3. Print the list of products\n" +
                            "4. Save product to a file\n" +
                            "5. Get interface\n" +
                            "6. Exit\n" +
                            "---------------------------\n" +
                            "Select an option: ");
                    option = input.nextInt();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Invalid input. Please enter a number.");
                    input.nextLine();
                }
            }

            switch (option){
                case 1:  //code to search for a product by ID
                    if (products.size()< max_products){
                        //Implement the logic to add a new product

                        int type;
                        while (true) {
                            try {
                                System.out.print("Enter product type(1 for Clothing, 2 for Electronic): ");
                                type = input.nextInt();
                                if (type == 1 || type == 2){
                                    break;
                                }else{
                                    System.out.println("Invalid input. Please enter 1 for clothing or 2 for electronic.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                input.nextLine();
                            }
                        }
                                switch (type){
                                    case 1:
                                        System.out.println("Enter clothing details.");
                                        System.out.print("Enter product ID: ");
                                        productID = input.next();
                                        while (checkExistingProductID(productID)){
                                            System.out.print("This product ID already exists.\nPlease enter another product ID: ");
                                            productID = input.next();
                                        }
                                        System.out.print("Enter product name: ");
                                        input.nextLine(); //consume the leftover newline
                                        productName = input.nextLine(); //wait for input
                                        while (true){
                                            try{
                                                System.out.print("Enter product price: ");
                                                price = input.nextDouble();
                                                if (price<0){
                                                    System.out.println("Invalid input. Price cannot be negative. Please try again.");
                                                    continue;
                                                }
                                                break;
                                            }catch (Exception e){
                                                System.out.println("Invalid input. Please try again");
                                                input.nextLine();
                                            }
                                        }

                                        while (true){
                                            try{
                                                System.out.print("Enter available items: ");
                                                availableItems = input.nextInt();
                                                if (availableItems<0){
                                                    System.out.println("Invalid input. Available items cannot be negative. Please try again.");
                                                    continue;
                                                }
                                                break;
                                            }catch (Exception e){
                                                System.out.println("Invalid input. Please try again");
                                                input.nextLine();
                                            }
                                        }
                                        String size;
                                        while (true){
                                            try{
                                                System.out.print("Enter the size(\"S\", \"M\", \"L\"): ");
                                                size = input.next();
                                                size = size.toUpperCase();
                                                if (size.equals("S") || size.equals("M") || size.equals("L")){
                                                    break;
                                                }else{
                                                    System.out.println("Invalid input. Please enter \"S\", \"M\", or \"L\".");
                                                }
                                            }catch (Exception e){
                                                System.out.println("Invalid input. Please try again.");
                                            }
                                        }
                                        System.out.print("Enter color: ");
                                        input.nextLine(); //consume the leftover newline
                                        String color = input.nextLine();
                                        Clothing clothing = new Clothing(productID,productName,availableItems,price,size,color);
                                        addProduct(clothing);
                                        System.out.println("Product successfully added.");
                                        break;
                                    case 2:
                                        System.out.println("Enter Electronic details.");
                                        System.out.print("Enter product ID: ");
                                        productID = input.next();
                                        while (checkExistingProductID(productID)){
                                            System.out.print("This product ID already exists.\nPlease enter another product ID: ");
                                            productID = input.next();
                                        }
                                        System.out.print("Enter product name: ");
                                        input.nextLine(); //consume the leftover newline
                                        productName = input.nextLine();
                                        while (true){
                                            try{
                                                System.out.print("Enter product price: ");
                                                price = input.nextDouble();
                                                if (price<0){
                                                    System.out.println("Invalid input. Price cannot be negative. Please try again.");
                                                    continue;
                                                }
                                                break;
                                            }catch (Exception e){
                                                System.out.println("Invalid input. Please try again");
                                                input.nextLine();
                                            }
                                        }
                                        while (true){
                                            try{
                                                System.out.print("Enter available items: ");
                                                availableItems = input.nextInt();
                                                if (availableItems<0){
                                                    System.out.println("Invalid input. Available items cannot be negative. Please try again.");
                                                    continue;
                                                }
                                                break;
                                            }catch (Exception e){
                                                System.out.println("Invalid input. Please try again");
                                                input.nextLine();
                                            }
                                        }
                                        System.out.print("Enter the brand: ");
                                        String brand = input.next();
                                        int warrantyPeriod;
                                        while (true){
                                            try{
                                                System.out.print("Enter warranty period in weeks: ");
                                                warrantyPeriod = input.nextInt();
                                                if (warrantyPeriod<0){
                                                    System.out.println("Invalid input. Warranty period cannot be negative. Please try again.");
                                                    continue;
                                                }
                                                break;
                                            }catch (Exception e){
                                                System.out.println("Invalid input. Please enter a number.");
                                                input.nextLine();
                                            }
                                        }

                                        Electronic electronic = new Electronic(productID,productName,availableItems,price,brand,warrantyPeriod);
                                        addProduct(electronic);
                                        System.out.println("Product successfully added.");
                                        break;
                                    default:
                                        System.out.println("Invalid product type. Please try again.");
                                }
                    }else{
                        System.out.println("The system can have the maximum of " + max_products + "products.");
                    }
                    break;
                case 2:
                    //Implement the logic to delete a product
                    System.out.print("Enter the product ID to delete: ");
                    productID = input.next();
                    Product product = findProductByID(productID);
                    if (product != null){
                        deleteProduct(product);
                        System.out.println("Only " + products.size() + " products in the list.");
                    }else{
                        System.out.println("Product not found. Please check the Product ID and try again.");
                    }
                    break;
                case 3:
                    if (products.isEmpty()){
                        System.out.println("Product list is empty.");
                    }else{
                        printProduct();
                    }break;
                case 4:
                    //Implement the logic to save products to a file
                    productToFile();
                    break;
                case 5:
                    GUI gui = new GUI(products);
                    gui.showInterface();
                    break;
                case 6:
                    System.out.println("*Thank You!*\nExiting from the main menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Find a product by its ID
    public Product findProductByID(String productID){
        for (int i=0; i< products.size(); i++){
            Product product = products.get(i);
            if (product.getProductID().equals(productID)){
                return product;
            }
        }return null;
    }

    // Add a product to the list
    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    // Delete a product from the list
    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
        System.out.println("Product deleted successfully.");
    }

    // Print the list of products
    @Override
    public void printProduct() {
        products.sort(Comparator.comparing(Product::getProductID));

        for (int i=0; i<products.size();i++){
            Product product = products.get(i);

            if( product instanceof Clothing){
                System.out.println("\n*Clothing product*");
                System.out.println("Product ID: " + product.getProductID() + "\nProduct name: " + product.getProductName() + "\nPrice: " + product.getPrice() + "\nAvailable items: " + product.getAvailableItems() + "\nClothing size: " + ((Clothing) product).getSize() + "\nProduct color: " + ((Clothing) product).getColor());
            }else{
                System.out.println("\n*Electronic product*");
                System.out.println("Product ID: " + product.getProductID() + "\nProduct name: " + product.getProductName() + "\nPrice: " + product.getPrice() + "\nAvailable items: " + product.getAvailableItems() + "\nElectronic brand: " + ((Electronic) product).getBrand() + "\nProduct warranty period: " + ((Electronic) product).getWarrantyPeriod());
            }
        }
    }

    // Save products to a file
    @Override
    public void productToFile() {
        // Implement the logic to save products to a file
        try{
            FileOutputStream fileOut = new FileOutputStream("Product.ser");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(products);
            objOut.close();
            fileOut.close();
            System.out.println("File saved successfully.");
        }catch (Exception e){
            System.out.println("An error occurred while writing to file. Please try again.");
            System.out.println(e);
        }
    }

    // Load products from a file
    @Override
    public void loadProductFile(){
        try{
            FileInputStream fileIn = new FileInputStream("Product.ser");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            products = (List<Product>) objIn.readObject();
            System.out.println("File loaded Successfully.");
        }catch (Exception e){
            System.out.println("An error occurred while reading from file. Please try again.");
        }
    }

    // Check if a product with the given ID already exists
    public boolean checkExistingProductID(String productID){
        for (int i = 0 ; i<products.size(); i++){
            if(products.get(i).getProductID().equals(productID)){
                return true;
            }
        }return false;
    }
}