import java.util.Locale;
import java.util.Scanner;

public class StoreView {
    private StoreManager storeManager;
    private ShoppingCart shoppingCart;

    public StoreView(StoreManager storeManager, int cartID) {
        this.storeManager = storeManager;
        storeManager.setShoppingCart(new ShoppingCart(cartID, storeManager.getStoreInventory()));
        shoppingCart = storeManager.getShoppingCart();
    }

    public void addToCart(int productID, int amountToAdd) {
        System.out.println("-ADD-");
        shoppingCart.addToCart(productID, amountToAdd);
    }

    public void removeFromCart(int productID, int amountToRemove) {
        shoppingCart.removeFromCart(productID, amountToRemove);
    }

    public void help() {
        System.out.println("browse - shows products in stock");
        System.out.println("addtocart - adds products to cart");
        System.out.println("removefromcart - removes products from cart");
        System.out.println("viewcart - shows products in cart");
        System.out.println("checkout - to checkout your items");
    }

    public void browse() {
        Inventory inv = storeManager.getStoreInventory();
        System.out.println("The Computer Store");
        System.out.println("-BROWSE-");
        System.out.println("Stock | Product | Price | ID");
        for (int i = 1; i < inv.getProductList().size() + 1; i++) {
            System.out.println(inv.getStock(i) + " | " + inv.getProduct(i).getName() + " | $" + inv.getPrice(i) + " | " +
                    i);
        }
    }

    public void viewCart() {
        for (int i = 0; i < shoppingCart.getCart().size(); i++) {
            System.out.println(shoppingCart.getCart().get(i).getName() + ", " + shoppingCart.getItemsInCart().get(i));
        }
    }

    public double getTotal() {

        return storeManager.orderTransaction(shoppingCart.getCart(), shoppingCart.getItemsInCart());
    }
    public void transaction(double total, double amountToPay){
        if(amountToPay>=total){
            System.out.println("Thank you for shopping at the computer store");
        }


    }

    public static void main(String[] args) {
        StoreManager storeManager1 = new StoreManager();
        StoreView storeView1 = new StoreView(storeManager1, storeManager1.generateCartID());
        StoreView storeView2 = new StoreView(storeManager1, storeManager1.generateCartID());
        StoreView storeView3 = new StoreView(storeManager1, storeManager1.generateCartID());
        StoreView[] users = {storeView1, storeView2, storeView3};
        int activeSV = users.length;


        Scanner sc = new Scanner(System.in);
        while (activeSV > 0) {
            System.out.print("CHOOSE YOUR STOREVIEW >>> ");
            int choice = sc.nextInt();
            if (choice < users.length && choice >= 0){
                String chooseAnother = "";
                while (!chooseAnother.equals("y") && !chooseAnother.equals("Y")){
                    System.out.println("Enter a command or type \'help\' for a list of commands or \'exit\' to exit");
                    String command = sc.next();
                    if(command.toLowerCase(Locale.ROOT).equals("browse")){
                        users[choice].browse();
                    }
                    if(command.toLowerCase(Locale.ROOT).equals("removefromcart")) {
                        System.out.println("Enter the product number");
                        int productNumber = sc.nextInt();
                        System.out.println("Enter the product amount to remove");
                        int amountOfProduct = sc.nextInt();
                        users[choice].removeFromCart(productNumber,amountOfProduct);
                    }
                    if(command.toLowerCase(Locale.ROOT).equals("addtocart")) {

                        System.out.println("Enter the product number");
                        int productNumber = sc.nextInt();
                        System.out.println("Enter the product amount to add");
                        int amountOfProduct = sc.nextInt();
                        users[choice].addToCart(productNumber,amountOfProduct);
                    }
                    if(command.toLowerCase(Locale.ROOT).equals("viewcart")){
                        users[choice].viewCart();
                    }
                    if(command.toLowerCase(Locale.ROOT).equals("help")){
                        users[choice].help();
                    }
                    if(command.toLowerCase(Locale.ROOT).equals("checkout")){
                        System.out.println("Do you want to checkout y/n");
                        String answer = sc.next();
                        if (answer.equals("y")){
                            double total = users[choice].getTotal();
                            System.out.println("Please pay now");
                            double payment = sc.nextDouble();
                            users[choice].transaction(total,payment);
                            users[choice] = null;
                            activeSV--;
                            break;
                        }
                    }

                    if(command.toLowerCase(Locale.ROOT).equals("exit")){
                        users[choice] = null;

                        break;
                    }
                    System.out.print("GO TO ANOTHER STOREVIEW? (y) >>> ");
                    chooseAnother = sc.next();
                }

            }else{
                System.out.println(String.format("MAIN > ERROR > BAD CHOICE\nPLEASE CHOOSE IN RANGE [%d, %d]",
                                0, users.length - 1));

            }

        }
        System.out.println("ALL STOREVIEWS DEACTIVATED");

    }




}