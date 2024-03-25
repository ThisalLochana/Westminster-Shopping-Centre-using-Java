Westminster Shopping Centre
Westminster Shopping Centre is a Java application for managing shopping-related operations, including adding and deleting products, displaying product details, managing shopping carts, and applying discounts.

Features
Product Management: Add and delete products from the system.
Display Products: Display details of products, including ID, name, category, price, size (for clothing), color (for clothing), brand (for electronics), and warranty period (for electronics).
Shopping Cart: Add products to the shopping cart, view items in the cart, and calculate the total cost including discounts.
Discounts: Apply discounts such as first person discount (10%) and three-item same category discount (20%).
Project Structure
The project consists of the following classes:

Main: Contains the main method to run the application.
User: Represents a user with a username and password.
ShoppingManager: Interface for managing shopping-related operations.
ShoppingCart: Manages the shopping cart, including adding and removing products, calculating total cost, and applying discounts.
Product: Abstract class representing a general product with common attributes.
Electronic: Extends the Product class and represents an electronic product with additional attributes such as brand and warranty period.
Clothing: Extends the Product class and represents a clothing product with additional attributes such as size and color.
GUI: Provides a graphical user interface for interacting with the application, including displaying products, managing shopping carts, and adding products to the cart.
Getting Started
To run the application:

Clone the repository to your local machine.
Compile the Java files using a Java compiler.
Run the Main class to start the application.
Usage
Launch the application to access the main interface.
Use the dropdown menu to filter products by category (All, Electronics, Clothing).
Select a product to view its details in the panel.
Click "Add to Shopping Cart" to add a product to the shopping cart.
Click "Shopping Cart" to view the items in the shopping cart and apply discounts.
Dependencies
The project has no external dependencies.

This project was developed as part of a coursework assignment for the "Java Programming" module under the guidance of Saman Heƫarachchi at Institute of IIT Affiliated to University of Westminster.
