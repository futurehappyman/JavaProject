import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class CustomerManager extends Manager{

    Scanner scanner = new Scanner(System.in);
    private String productForCart;
    private String productToRemove;

    public void addToCart() {
        boolean flag = true; // waining untill the correct product added, without numbers
        while (flag) {
            try {
                System.out.println("Enter the product to add: ");
                System.out.println("type: exit, to go back"); // exit so user don't get stuck in loops
                productForCart = scanner.nextLine();
                if (productForCart.equalsIgnoreCase("exit")) {
                    System.out.println("Cancelled adding products to cart.");
                    return;
                }

                if (!AdminManager.listOfProducts.contains(productForCart)) { // if no product was found
                    throw new IllegalArgumentException("Non existant product!");
                }

                cart.put(productForCart, cart.getOrDefault(productForCart, 0) + 1); // otherwise we put the product

                if (AdminManager.productCounter.get(productForCart) > 1) { // if product nunmber is more than 1, just reduces the number
                    AdminManager.productCounter.put(productForCart, AdminManager.productCounter.get(productForCart) - 1);
                } else {
                    AdminManager.productCounter.remove(productForCart); // if 1, then deletes the product from list and counter
                    AdminManager.listOfProducts.remove(productForCart);
                    System.out.println("the shop is empty");
                }

                System.out.println("Product added to cart!");
                flag = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Error :" + e.getMessage());
            }
        }
    }

    public void removeFromCart() {
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Enter the product to remove from cart: ");
                System.out.println("type: exit, to go back");
                productToRemove = scanner.nextLine();

                if (productToRemove.equalsIgnoreCase("exit")) {
                    System.out.println("Cancelled removing products to cart.");
                    return;
                }

                if (!cart.containsKey(productToRemove)) {
                    throw new IllegalArgumentException("Product does not exist!");
                }

                int countInCart = cart.get(productToRemove);

                if (countInCart > 1) {
                    cart.put(productToRemove, countInCart - 1); // just decrements if more then 1
                } else {
                    cart.remove(productToRemove); // deletes if 1
                }

                AdminManager.productCounter.put(productToRemove, AdminManager.productCounter.getOrDefault(productToRemove, 0) + 1); // return the product back to productCounter
                if (!AdminManager.listOfProducts.contains(productToRemove)) { // return back it to list
                    AdminManager.listOfProducts.add(productToRemove);
                }

                System.out.println("Product removed!");
                flag = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    @Override
    public void displayProducts() {
        if (listOfProducts.isEmpty()) {
            System.out.println("The list is empty");
        }
        System.out.println("Available products in the shop:");
        for (String product : productCounter.keySet()) {
            System.out.println("Product: " + product + " Count: " + productCounter.get(product));
        }
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("The cart is empty");
        }
        for (String productInCart : cart.keySet()) {
            System.out.println("Product: " + productInCart + " Count: " + cart.get(productInCart));
        }
    }
}
