import jdk.dynalink.NoSuchDynamicMethodException;

import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class SystemPanel {

    AdminManager am = new AdminManager(); // creating the object to use its functions and entities
    CustomerManager cm = new CustomerManager(); // creating the object to use its functions and entities
    Manager manager = new Manager();  // manager object to realise the abstraction throug it and call its methods and hide detials
    Scanner scanner = new Scanner(System.in);
    HashMap<String, String> adminCredentials = new HashMap<>(); // map for admin credentials

    SystemPanel() {
        adminCredentials.put("admin", "admin123");
    }

    public void start() { // the function that runs the whole console panel messages

        while (true) { // infinite looping until the correct choice

            System.out.println("1. ---Admin panel---");
            System.out.println("2. ---Customer panel---");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = getValidatedInteger(); // reusing a function to ensure the correct input from user

            switch (choice) {

                case 1:
                    boolean correctAuthentication = authenticate(); // firstly the function, then we store the value, and go forwars if true; could have done it by writing the parameters in it
                    if (correctAuthentication) {
                        while (true) {// once again an infinite loop for the Admin panel
                            System.out.println("1. ---Add new products---");
                            System.out.println("2. ---View product list---");
                            System.out.println("3. ---Remove products---");
                            System.out.println("4. ---Back to main menu---");
                            System.out.print("Choose an option: ");
                            int adminChoice = getValidatedInteger(); // using a function that returns correct integers and stores the value for the adminChoice

                            if (adminChoice == 4) {
                                System.out.println("Backing to the main menu...");
                                break;
                            }

                            switch (adminChoice) {
                                case 1:
                                    am.addProduct(); // using functions of the AdminManager class
                                    break;
                                case 2:
                                    am.displayProducts();
                                    break;
                                case 3:
                                    am.removeProducts();
                                    break;
                                default:
                                    System.out.println("Enter correct integer"); // makes sure the input integer is correct
                            }
                        }
                    }
                    break;
                case 2:

                    while (true) {// case 2 is already for the customer, and its class and functions
                        System.out.println("1. ---View available products---");
                        System.out.println("2. ---Add products to the cart---");
                        System.out.println("3. ---Remove item from cart---");
                        System.out.println("4. ---View cart---");
                        System.out.println("5. ---Back to main menu---");
                        System.out.print("Enter your choice: ");
                        int customerChoice = getValidatedInteger();

                        if (customerChoice == 5) {
                            System.out.println("Backing to the main menu...");
                            break;
                        }
                        switch (customerChoice) {
                            case 1:
                                 manager.displayProducts();// using function of the customerManager class
                                break;
                            case 2:
                                cm.addToCart();
                                break;
                            case 3:
                                cm.removeFromCart();
                                break;
                            case 4:
                                cm.viewCart();
                                break;
                            default:
                                System.out.println("PLease enter correct integer"); // correct integer input if not 1,2,3
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting..."); // end of the program
                    return;
                default:
                    System.out.println("PLease enter correct integer"); // ensures the correct input
            }
        }
    }

    private int getValidatedInteger() { // function that ensures the correct input
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("PLease enter a number!");
            }
        }
    }

    private boolean authenticate() { // function that check wether the credentials are legit
    System.out.println("Enter username: ");
    String username = scanner.nextLine();

    System.out.println("Enter password: ");
    String password = scanner.nextLine();

    if (adminCredentials.containsKey(username) && adminCredentials.containsValue(password)) { // we have acces to the admin credentials
        System.out.println("Access granted!");
        return true;
    }
    System.out.println("Please try again!");
    return false;
    }
}
