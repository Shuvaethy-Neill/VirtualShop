// Written by: Andre Hazim 101141843
// Partner: Shuvaethy Neill 101143478
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class StoreView {
    private StoreManager storeManager;
    private ShoppingCart shoppingCart;
    private int cartId;

    public StoreView(StoreManager storeManager, int cartID) {
        this.storeManager = storeManager;
        this.cartId = cartID;
        storeManager.setShoppingCart(new ShoppingCart(cartID));

    }

    private boolean checkInCart(int productID){
        boolean inCart = false;
        for (int i = 0; i < storeManager.getSMCart(this.cartId).size(); i++) {
            if (productID== storeManager.getSMCart(this.cartId).get(i).getId()){
                inCart = true;
            }

        }
        return inCart;
    }
    private void addToCart(int productID, int amountToAdd) {
        System.out.println("-ADD-");
        System.out.println("Adding "+amountToAdd + " "+ storeManager.getStoreInventory().getProductName(productID)+"(s)");
        storeManager.addToCart(productID, amountToAdd, this.cartId);

        this.viewCart();
    }

    private void removeFromCart(int productID, int amountToRemove) {

        System.out.println("-REMOVE-");
        System.out.println("Removing "+amountToRemove + " "+ storeManager.getStoreInventory().getProductName(productID));
        storeManager.removeFromCart(productID, amountToRemove,this.cartId);

        this.viewCart();



    }

    private void removeEverythingFromCart(){
        for (int i =  storeManager.getSMCart(this.cartId).size()-1; i >= 0 ; i--) {

            storeManager.removeFromCart( storeManager.getSMCart(this.cartId).get(i).getId(),
                    storeManager.getSMItemsInCart(this.cartId).get(i),this.cartId);

        }
    }

    private void help() {
        System.out.println("browse - shows products in stock");
        System.out.println("addtocart - adds products to cart based off of id");
        System.out.println("removefromcart - removes products from cart");
        System.out.println("viewcart - shows products in cart");
        System.out.println("checkout - to checkout your items");
    }

    private void browse() {
        Inventory inv = storeManager.getStoreInventory();
        System.out.println("The Computer Store");
        System.out.println("-BROWSE-");
        System.out.println("ID | Product | Price | Stock");
        for (int i = 1; i < inv.getProductList().size() + 1; i++) {
            System.out.println( i + " | " + inv.getProduct(i).getName() + " | $" + inv.getPrice(i) + " | " +
                    inv.getStock(i));
        }
    }

    private void viewCart() {
        System.out.print("Your Cart : ");
        for (int i = 0; i < storeManager.getSMCart(this.cartId).size(); i++) {
            System.out.print("("+storeManager.getSMCart(this.cartId).get(i).getName() + ", " + storeManager.getSMItemsInCart(this.cartId).get(i)+")"); //
            if (i == storeManager.getSMCart(this.cartId).size()-1){
                System.out.print("");
            }else {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }

    private double getTotal() {

        return storeManager.orderTransaction(storeManager.getSMCart(this.cartId), storeManager.getSMItemsInCart(this.cartId));
    }
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
            System.out.print("CHOOSE YOUR STOREVIEW >>> ");
            int choice = sc.nextInt();
            if (choice < users.size() && choice >= 0){
                String chooseAnother = "";
                while (!chooseAnother.equals("y") && !chooseAnother.equals("Y")){
                    System.out.println("Enter a command or type \'help\' for a list of commands or \'exit\' to disconnect "+
                            "or changeview to change storeview");
                    String command = sc.next();
                    if(command.toLowerCase(Locale.ROOT).equals("browse")){
                        users.get(choice).browse();
                    }
                    if(command.toLowerCase(Locale.ROOT).equals("removefromcart")) {
                        System.out.println("Enter the product number");
                        int productNumber = sc.nextInt();

                        while (!users.get(choice).checkInCart(productNumber)){
                            System.out.println("Please pick a product id for a product you have");
                            productNumber = sc.nextInt();
                        }
                        System.out.println("Enter the product amount to remove");
                        int amountOfProduct = sc.nextInt();


                        users.get(choice).removeFromCart(productNumber,amountOfProduct);
                    }
                    if(command.toLowerCase(Locale.ROOT).equals("addtocart")) {

                        System.out.println("Enter the product number");
                        int productNumber = sc.nextInt();

                        while (productNumber > rangeOfProducts || productNumber <=0){
                            System.out.println("Please pick a product id in range 1-"+ rangeOfProducts);
                            productNumber = sc.nextInt();
                        }
                        System.out.println("Enter the product amount to add");
                        int amountOfProduct = sc.nextInt();
                        while (amountOfProduct > inv.getStock(productNumber)){
                            System.out.println("Please pick an amount of stock in range 1-"+ inv.getStock(productNumber));
                            amountOfProduct = sc.nextInt();
                        }
                        users.get(choice).addToCart(productNumber,amountOfProduct);
                    }
                    if(command.toLowerCase(Locale.ROOT).equals("viewcart")){
                        users.get(choice).viewCart();
                    }
                    if(command.toLowerCase(Locale.ROOT).equals("help")){
                        users.get(choice).help();
                    }
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

                    if(command.toLowerCase(Locale.ROOT).equals("exit")){
                        users.get(choice).removeEverythingFromCart();
                        users.remove(choice);
                        activeSV--;


                        break;
                    }
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
