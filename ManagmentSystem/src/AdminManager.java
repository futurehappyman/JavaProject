import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AdminManager extends Manager{

    private final Scanner scanner = new Scanner(System.in);

    private  String productName;
    private  String removeProductName; // encapsulating

    public void addProduct() throws IllegalArgumentException {
        boolean flag = true;                                                       // basically saying until the correct value is not passed down
        while (flag) {                                                            // waiting for the correct value and only then going forward

            try {   // for safety, so the program does not stop halfway
                System.out.println("Enter the name of the product to add: ");
                System.out.println("type: exit, to go back");
                productName = scanner.nextLine();
                if (!productName.matches("[a-zA-Z ]+")) {
                    throw new IllegalArgumentException("Must be letters only!");
                }
                System.out.println("type: exit, to go back");
                if (productName.equalsIgnoreCase("exit")) { // exit condition so the user does not get stuck in the loop
                    System.out.println("Cancelled adding products.");
                    return;
                }
                productCounter.put(productName, productCounter.getOrDefault(productName, 0) + 1); // putting in the product and making a count for it
                listOfProducts.add(productName);
                saveProductToFile(productName); // putting in the file the occurunces
                System.out.println("Product added!");
                flag = false; // if correct value, then we go forward

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void displayProducts() {
        if (listOfProducts.isEmpty()) {
            System.out.println("The list is empty");
        }
        System.out.println("products in stock:");
        for (String product : productCounter.keySet()) {
            System.out.println("Product: " + product + " Count: " + productCounter.get(product));
        }
    }

    public void removeProducts() {
        boolean flag = true;
        while (flag) { // same here, but removing, and the inner is little different.

            try {
                System.out.println("Enter the name of the product to remove: ");
                System.out.println("type: exit, to go back");
                removeProductName = scanner.nextLine();
                if (removeProductName.equalsIgnoreCase("exit")) {
                    System.out.println("Cancelled removing products.");
                    return;
                }
                if (!listOfProducts.contains(removeProductName)) {
                    throw new IllegalArgumentException("Non existant product");
                }

                if (productCounter.get(removeProductName) > 1) { // if more than 1 product in productCounter, just decrements by 1
                    productCounter.put(removeProductName, productCounter.get(removeProductName) - 1);
                } else {
                    productCounter.remove(removeProductName); // removes if just 1
                    listOfProducts.remove(removeProductName);
                }
                System.out.println("Product removed from list!");
                flag = false;

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    // basic implementation of file writer
    private void saveProductToFile(String productName) throws IOException{
        try (FileWriter writer = new FileWriter("/Users/stepasha/IdeaProjects/ManagmentSystem/src/text.txt", true)) {
            writer.write("Product: " + productName + " Count: "+ productCounter.get(productName) + "\n");
        }
    }
}
