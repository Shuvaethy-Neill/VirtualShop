package store;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

// Written by: Andre Hazim 101141843
// Partner: Shuvaethy Neill 101143478

/**
 * This store.StoreView class is the entry point of the store system
 * For this milestone, the user interface is textually displayed in the console
 *
 * @author Andre Hazim
 *
 * @version 1.0
 *
 * @since 2021-03-07
 *
 */

public class StoreView {
    private StoreManager storeManager;
    private int cartId;

    /**
     * The constructor for store.StoreView
     *
     * @param storeManager store.StoreManager
     * @param cartID int, the id of the cart for a specific storeview
     */
    public StoreView(StoreManager storeManager, int cartID) {
        this.storeManager = storeManager;
        this.cartId = cartID;
        // Adds store.ShoppingCart to hashmap in store.StoreManager to keep track
        storeManager.addShoppingCart(new ShoppingCart(cartID));
    }

    /**
     * This method checks if the product is in the shopping cart
     *
     * @param productID int, the id of the product
     * @return a boolean, true if the item is in the cart or false otherwise
     */
    private boolean checkInCart(int productID){
        boolean inCart = false;
        for (int i = 0; i < this.storeManager.getSMCart(this.cartId).size(); i++) {
            if (productID== this.storeManager.getSMCart(this.cartId).get(i).getId()){
                inCart = true;
            }

        }
        return inCart;
    }

    /**
     * This method adds a product to cart by store.StoreManager
     *
     * @param productID int, the id of the product to be added
     * @param amountToAdd int, the amount of the product to add
     */
    private void addToCart(int productID, int amountToAdd) {
        System.out.println("-ADD-");
        System.out.println("Adding "+amountToAdd + " "+ this.storeManager.getStoreInventory().getProductName(productID)+"(s)");
        this.storeManager.addToCart(productID, amountToAdd, this.cartId);

        this.viewCart();
    }

    /**
     * This method removes product from the cart by store.StoreManager
     *
     * @param productID  int, the id of the product to be removed
     * @param amountToRemove int, the amount of the product to remove
     */
    private void removeFromCart(int productID, int amountToRemove) {

        System.out.println("-REMOVE-");
        System.out.println("Removing "+amountToRemove + " "+ this.storeManager.getStoreInventory().getProductName(productID));
        this.storeManager.removeFromCart(productID, amountToRemove,this.cartId);

        this.viewCart();
    }

    /**
     * This method removes everything from the cart when a user exits
     */
    private void removeEverythingFromCart(){
        for (int i =  this.storeManager.getSMCart(this.cartId).size()-1; i >= 0 ; i--) {

            this.storeManager.removeFromCart( this.storeManager.getSMCart(this.cartId).get(i).getId(),
                    this.storeManager.getSMItemsInCart(this.cartId).get(i),this.cartId);
        }
    }

    /**
     * This method displays all the commands the user can enter
     */
    private void help() {
        System.out.println("browse - shows products in stock");
        System.out.println("addtocart - adds products to cart based off of id");
        System.out.println("removefromcart - removes products from cart");
        System.out.println("viewcart - shows products in cart");
        System.out.println("checkout - to checkout your items");
    }

    /**
     * This method displays the stores current inventory
     */
    private void browse() {
        Inventory inv = this.storeManager.getStoreInventory();
        System.out.println("The Computer Store");
        System.out.println("-BROWSE-");
        System.out.println("ID | store.Product | Price | Stock");
        for (int i = 1; i < inv.getProductList().size() + 1; i++) {
            System.out.println( i + " | " + inv.getProduct(i).getName() + " | $" + inv.getPrice(i) + " | " +
                    inv.getStock(i));
        }
    }

    /**
     * This method shows the user's current cart
     */
    private void viewCart() {
        System.out.print("Your Cart : ");
        for (int i = 0; i < this.storeManager.getSMCart(this.cartId).size(); i++) {
            System.out.print("("+this.storeManager.getSMCart(this.cartId).get(i).getName() + ", " + this.storeManager.getSMItemsInCart(this.cartId).get(i)+")"); //
            if (i == this.storeManager.getSMCart(this.cartId).size()-1){
                System.out.print("");
            }else {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }

    /**
     * This method gets the total for the transaction
     * @return double, the total price
     */
    private double getTotal() {
        return this.storeManager.orderTransaction(this.storeManager.getSMCart(this.cartId), this.storeManager.getSMItemsInCart(this.cartId));
    }

    /**
     * this method will do the transaction
     * @param total double the total amount the user must pay
     * @param amountToPay double the amount the user pays it is assumed that they payed in full
     */
    private void transaction(double total, double amountToPay){
        if(amountToPay>=total){
            System.out.println("Thank you for shopping at the computer store");
        }
    }


    public static void main(String[] args) {
        StoreManager storeManager1 = new StoreManager();
        StoreView storeView1 = new StoreView(storeManager1, storeManager1.generateCartID());
        StoreView storeView2 = new StoreView(storeManager1, storeManager1.generateCartID());
        StoreView storeView3 = new StoreView(storeManager1, storeManager1.generateCartID());
        ArrayList<StoreView> users = new ArrayList<StoreView>();
        users.add(storeView1);
        users.add(storeView2);
        users.add(storeView3);
        int activeSV = users.size();
        int rangeOfProducts = storeManager1.getStoreInventory().getProductList().size();
        Inventory inv = storeManager1.getStoreInventory();

        Scanner sc = new Scanner(System.in);
        while (activeSV > 0) {
            System.out.print("CHOOSE YOUR STOREVIEW (integer input) >>> ");
            int choice = -1;
            while (choice == -1){
                try {
                    choice = sc.nextInt();
                }catch (InputMismatchException ime){
                    System.out.println("Please input an integer for a store id");
                    sc.next();

                }
            }
            if (choice < users.size() && choice >= 0){
                String chooseAnother = "";

                // Checks if they want to change the storeview
                while (!chooseAnother.equals("y") && !chooseAnother.equals("Y")){
                    System.out.println("Enter a command or type \'help\' for a list of commands or \'exit\' to disconnect "+
                            "or changeview to change storeview");
                    String command = sc.next();
                    // Checks if the user entered the browse command
                    if(command.toLowerCase(Locale.ROOT).equals("browse")){
                        users.get(choice).browse();
                    }
                    // Checks if the user entered the removefromcart command
                    if(command.toLowerCase(Locale.ROOT).equals("removefromcart")) {
                        if (!storeManager1.getSMCart(users.get(choice).cartId).isEmpty()) {
                            System.out.println("Enter the product number");
                            int productNumber = -1;
                            while (productNumber == -1){
                                try {
                                    productNumber = sc.nextInt();
                                }catch (InputMismatchException ime){
                                    System.out.println("Please pick a product id");
                                    sc.next();

                                }
                            }

                            // Checks if the user has picked from the range of products they has
                            while (!users.get(choice).checkInCart(productNumber)) {
                                System.out.println("Please pick a product id for a product you have");
                                productNumber = -1;
                                while (productNumber == -1){
                                    try {
                                        productNumber = sc.nextInt();
                                    }catch (InputMismatchException ime){
                                        System.out.println("Please pick a product id");
                                        sc.next();

                                    }
                                }
                            }
                            System.out.println("Enter the product amount to remove");
                            int amountOfProduct = -1;
                            while (amountOfProduct == -1){
                                try {
                                    amountOfProduct = sc.nextInt();
                                }catch (InputMismatchException ime){
                                    System.out.println("Please enter a number");
                                    sc.next();

                                }
                            }
                            users.get(choice).removeFromCart(productNumber, amountOfProduct);
                        }
                        else{
                            System.out.println("Your cart is empty! There is nothing to remove");
                        }
                    }
                    // Checks if the user entered the addtocart command
                    if(command.toLowerCase(Locale.ROOT).equals("addtocart")) {
                        System.out.println("Enter the product number");
                        int productNumber = -1;
                        while (productNumber == -1){
                            try {
                                productNumber = sc.nextInt();
                            }catch (InputMismatchException ime){
                                System.out.println("Please pick a product id");
                                sc.next();

                            }
                        }

                        // Checks to see if the user has picked the correct product id
                        while (productNumber > rangeOfProducts || productNumber <=0 || inv.getStock(productNumber) == 0){
                            System.out.println("Please pick a product id in the range 1-"+ rangeOfProducts + " and that has available stock");
                            productNumber = -1;
                            while (productNumber == -1){
                                try {
                                    productNumber = sc.nextInt();
                                }catch (InputMismatchException ime){
                                    System.out.println("Please pick a product id");
                                    sc.next();

                                }
                            }
                        }

                        System.out.println("Enter the product amount to add");
                        int amountOfProduct = -1;
                        while (amountOfProduct == -1){
                            try {
                                amountOfProduct = sc.nextInt();
                            }catch (InputMismatchException ime){
                                System.out.println("Please enter a number");
                                sc.next();

                            }
                        }

                        //Checks if the user has picked from the range of products the store has
                        while (amountOfProduct > inv.getStock(productNumber) || amountOfProduct <= 0){
                            System.out.println("Please pick an amount of stock in range 1-"+ inv.getStock(productNumber));
                            amountOfProduct = -1;
                            while (amountOfProduct == -1){
                                try {
                                    amountOfProduct = sc.nextInt();
                                }catch (InputMismatchException ime){
                                    System.out.println("Please enter a number");
                                    sc.next();

                                }
                            }
                        }
                        users.get(choice).addToCart(productNumber,amountOfProduct);
                    }
                    // Checks if the user entered the viewcart command
                    if(command.toLowerCase(Locale.ROOT).equals("viewcart")){
                        users.get(choice).viewCart();
                    }
                    // Checks if the user entered the help command
                    if(command.toLowerCase(Locale.ROOT).equals("help")){
                        users.get(choice).help();
                    }
                    // Checks if the user entered the checkout command
                    if(command.toLowerCase(Locale.ROOT).equals("checkout")){
                        System.out.println("Do you want to checkout y/n");
                        String answer = sc.next();
                        if (answer.equals("y")){
                            double total = users.get(choice).getTotal();
                            System.out.println("Please pay now");
                            double payment = sc.nextDouble();
                            users.get(choice).transaction(total,payment);
                            users.remove(choice);
                            activeSV--;
                            break;
                        }
                    }
                    // Checks if the user entered the exit command
                    if(command.toLowerCase(Locale.ROOT).equals("exit")){
                        users.get(choice).removeEverythingFromCart();
                        users.remove(choice);
                        activeSV--;
                        break;
                    }
                    // Checks if the user entered the changeview command
                    if(command.toLowerCase(Locale.ROOT).equals("changeview")){
                        chooseAnother = "y";
                    }
                }

            }else{
                System.out.println(String.format("MAIN > ERROR > BAD CHOICE\nPLEASE CHOOSE IN RANGE [%d, %d]",
                        0, users.size() - 1));
            }
        }
        System.out.println("ALL STOREVIEWS DEACTIVATED");
    }
}
