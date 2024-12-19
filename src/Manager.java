import java.util.HashMap;
import java.util.LinkedList;

public class Manager { // class for the

    public static LinkedList<String> listOfProducts = new LinkedList<>();
    public static HashMap<String, Integer> productCounter = new HashMap<>();
    HashMap<String, Integer> cart = new HashMap<>();

    // polymorphic function
    public void displayProducts() {
        System.out.println("Available products:");
        for (String product : productCounter.keySet()) {
            System.out.println("Product: " + product + " Count: " + productCounter.get(product));
        }
    }


}
