package store;

import store.StoreManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

// Written by: Andre Hazim 101141843 and Shuvaethy Neill 101143478

/**
 * This StoreView class is the entry point of the store system
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
    private final JFrame frame;
    private List<JPanel> productPanels;
    private int[] stockToAdd;
    private List<JLabel> stockToAddLabel;
    private StoreManager storeManager;
    private JButton[][] buttonArray;
    private JLabel[] productLabels;
    private JLabel[] productImageLabels;
    private int cartId;

    /**
     * The constructor for StoreView
     *
     * @param storeManager StoreManager
     * @param cartID       int, the id of the cart for a specific storeview
     */
    public StoreView(StoreManager storeManager, int cartID) {
        this.frame = new JFrame();
        this.productPanels = new ArrayList<JPanel>();
        this.stockToAdd = new int[] {0,0,0,0,0};
        this.stockToAddLabel = new ArrayList<JLabel>();
        for(int i = 0; i < storeManager.getStoreInventory().getProductList().size(); i++){

            JLabel stock = new JLabel("Quantity: " + stockToAdd[i]);
            stock.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.stockToAddLabel.add(stock);
        }
        this.buttonArray = new JButton[5][];
        this.productLabels = new JLabel[5];
        this.productImageLabels = new JLabel[5];
        this.storeManager = storeManager;
        this.cartId = cartID;
        // Adds ShoppingCart to hashmap in StoreManager to keep track
        storeManager.addShoppingCart(new ShoppingCart(cartID));
    }

    /**
     * This method checks if the product is in the shopping cart
     *
     * @param productID int, the id of the product
     * @return a boolean, true if the item is in the cart or false otherwise
     */
    private boolean checkInCart(int productID) {
        boolean inCart = false;
        for (int i = 0; i < storeManager.getSMCart(this.cartId).size(); i++) {
            if (productID == storeManager.getSMCart(this.cartId).get(i).getId()) {
                inCart = true;
            }

        }
        return inCart;
    }

    /**
     * This method adds a product to cart by StoreManager
     *
     * @param productID   int, the id of the product to be added
     * @param amountToAdd int, the amount of the product to add
     */
    private void addToCart(int productID, int amountToAdd) {
        System.out.println("-ADD-");
        System.out.println("Adding " + amountToAdd + " " + storeManager.getStoreInventory().getProductName(productID) + "(s)");
        storeManager.addToCart(productID, amountToAdd, this.cartId);

        this.viewCart();
    }

    /**
     * This method removes product from the cart by StoreManager
     *
     * @param productID      int, the id of the product to be removed
     * @param amountToRemove int, the amount of the product to remove
     */
    private void removeFromCart(int productID, int amountToRemove) {

        System.out.println("-REMOVE-");
        System.out.println("Removing " + amountToRemove + " " + storeManager.getStoreInventory().getProductName(productID));
        storeManager.removeFromCart(productID, amountToRemove, this.cartId);

        this.viewCart();
    }

    /**
     * This method removes everything from the cart when a user exits
     */
    private void removeEverythingFromCart() {
        for (int i = storeManager.getSMCart(this.cartId).size() - 1; i >= 0; i--) {

            storeManager.removeFromCart(storeManager.getSMCart(this.cartId).get(i).getId(),
                    storeManager.getSMItemsInCart(this.cartId).get(i), this.cartId);
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
        Inventory inv = storeManager.getStoreInventory();
        System.out.println("The Computer Store");
        System.out.println("-BROWSE-");
        System.out.println("ID | Product | Price | Stock");
        for (int i = 1; i < inv.getProductList().size() + 1; i++) {
            System.out.println(i + " | " + inv.getProduct(i).getName() + " | $" + inv.getPrice(i) + " | " +
                    inv.getStock(i));
        }
    }

    /**
     * This method shows the user's current cart
     */
    private void viewCart() {
        System.out.print("Your Cart : ");
        for (int i = 0; i < storeManager.getSMCart(this.cartId).size(); i++) {
            System.out.print("(" + storeManager.getSMCart(this.cartId).get(i).getName() + ", " + storeManager.getSMItemsInCart(this.cartId).get(i) + ")"); //
            if (i == storeManager.getSMCart(this.cartId).size() - 1) {
                System.out.print("");
            } else {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }


    /**
     * This method gets the total for the transaction
     *
     * @return double, the total price
     */
    private double getTotal() {
        return storeManager.orderTransaction(storeManager.getSMCart(this.cartId), storeManager.getSMItemsInCart(this.cartId));
    }

    /**
     * this method will do the transaction
     *
     * @param total       double the total amount the user must pay
     * @param amountToPay double the amount the user pays it is assumed that they payed in full
     */
    private void transaction(double total, double amountToPay) {
        if (amountToPay >= total) {
            System.out.println("Thank you for shopping at the computer store");
        }
    }

    private void enableRemove(int productId){
        if(stockToAdd[productId] > 0){
            buttonArray[productId][1].setEnabled(true);
        }
        else{
            buttonArray[productId][1].setEnabled(false);
        }
    }

    private void enableAdd(int productId){
        if(stockToAdd[productId] > 0){
            buttonArray[productId][2].setEnabled(true);
        }
        else{
            buttonArray[productId][2].setEnabled(false);
        }
    }

    private JButton getRemoveB(int productId) {
        JButton removeB = new JButton("-");
        removeB.setEnabled(false);
        removeB.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        removeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                stockToAdd[productId-1] -= 1;
                if(stockToAdd[productId-1] != storeManager.getStoreInventory().getStock(productId) && !buttonArray[productId-1][0].isEnabled()){
                    buttonArray[productId-1][0].setEnabled(true);
                }
                if (stockToAdd[productId-1] == 0 ){
                    buttonArray[productId-1][1].setEnabled(false);
                }
                stockToAddLabel.get(productId - 1).setText("Quantity: " + stockToAdd[productId-1]);
                enableAdd(productId-1);
            }
        });
        return removeB;
    }

    private JButton getAddB(int productId) {
        JButton addB = new JButton("+");

        addB.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        addB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockToAdd[productId-1] += 1;
                if(stockToAdd[productId-1] == storeManager.getStoreInventory().getStock(productId)){
                    addB.setEnabled(false);
                }
                if(stockToAdd[productId-1] > 0){
                    buttonArray[productId-1][1].setEnabled(true);
                }
                stockToAddLabel.get(productId - 1).setText("Quantity: " + stockToAdd[productId-1]);
                enableAdd(productId-1);
            }
        });
        return addB;
    }


    private JButton getAddToCart(int productId){
        JButton addToCart = new JButton("Add to Cart");
        addToCart.setEnabled(false);
        addToCart.setPreferredSize(new Dimension(40, 20));
        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart(productId, stockToAdd[productId-1]);
                productLabels[productId-1].setText(("Price: " +storeManager.getStoreInventory().getProduct(productId).getPrice() + " | Stock: " +storeManager.getStoreInventory().getStock(productId)));
                stockToAdd[productId - 1] = 0;
                stockToAddLabel.get(productId - 1).setText("Quantity: " + stockToAdd[productId-1]);
                enableRemove(productId - 1);
                enableAdd(productId-1);
            }
        });

        return addToCart;
    }
    private JButton getRemoveFromCart(int productId, int positionInCart ){
        JButton removeFromCart = new JButton("Remove From Cart");
        removeFromCart.setPreferredSize(new Dimension(40, 20));
        removeFromCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFromCart(productId,(storeManager.getSMItemsInCart(cartId).get(positionInCart)));
                productLabels[productId-1].setText(("Price: " +storeManager.getStoreInventory().getProduct(productId).getPrice() + " | Stock: " +storeManager.getStoreInventory().getStock(productId)));
                removeFromCart.setEnabled(false);
            }
        });
        return removeFromCart;
    }

    private JButton getViewCartB() {

        JButton viewCartB = new JButton("View Cart");
        viewCartB.setPreferredSize(new Dimension(30, 20));
        viewCartB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame f = new JFrame("Cart View");


                f.setSize(400, 400);
                JPanel p1 = new JPanel(new BorderLayout());
                BoxLayout layout = new BoxLayout(p1, BoxLayout.Y_AXIS);
                p1.setLayout(layout);
                p1.setPreferredSize(new Dimension(400,400));
                p1.add(new JLabel("Your cart:"));
                JLabel[] productInfo = new JLabel[5];
                JButton[] removeButtons = new JButton[5];
                for (int i = 0; i < storeManager.getSMCart(cartId).size(); i++) {
                    productInfo[i] = new JLabel(storeManager.getSMCart(cartId).get(i).getName() + ": " + storeManager.getSMItemsInCart(cartId).get(i));
                    removeButtons[i] = getRemoveFromCart(storeManager.getSMCart(cartId).get(i).getId(),i);
                    p1.add(productInfo[i]);
                    p1.add(removeButtons[i]);
                }

                f.add(p1);
                f.pack();

                f.setVisible(true);
            }
        });
        return viewCartB;
    }

    private JButton getCheckoutB() {
        JButton checkoutB = new JButton("Checkout");
        checkoutB.setPreferredSize(new Dimension(30, 20));
        checkoutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame f = new JFrame("Checkout");


                f.setSize(400, 400);
                JPanel p1 = new JPanel(new BorderLayout());
                BoxLayout layout = new BoxLayout(p1, BoxLayout.Y_AXIS);
                p1.setLayout(layout);
                p1.setPreferredSize(new Dimension(400,400));
                JLabel[] productInfo = new JLabel[5];
                JLabel[] productPrice = new JLabel[5];
                JLabel[] border = new JLabel[5];
                for (int i = 0; i < storeManager.getSMCart(cartId).size(); i++) {
                    productInfo[i] = new JLabel(storeManager.getSMCart(cartId).get(i).getName() + ": " + storeManager.getSMItemsInCart(cartId).get(i));
                    productPrice[i] = new JLabel("$" + storeManager.getSMCart(cartId).get(i).getPrice());
                    border[i] =  new JLabel("----------------");
                    p1.add(productInfo[i]);
                    p1.add(productPrice[i]);
                    p1.add(border[i]);
                }
                JLabel total = new JLabel("Total: " + getTotal());
                p1.add(total);

                JButton okB = new JButton("OK");
                okB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.setVisible(false);
                        f.dispose();
                        frame.setVisible(false);
                        frame.dispose();
                    }
                });
                f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        f.setVisible(false);
                        f.dispose();
                    }
                });

                p1.add(okB);

                f.add(p1);
                f.pack();

                f.setVisible(true);
            }
        });
        return checkoutB;
    }

    private JButton getQuitB() {
        JButton quitB = new JButton("Quit");
        quitB.setPreferredSize(new Dimension(30, 20));
        quitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    // close it down!
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    // close it down!
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        return quitB;
    }

    /**
     * Method that enables the features displayed within the GUI
     */
    private void displayGUI() {
        this.frame.setTitle("Client StoreView");

        JPanel mainPanel = new JPanel(new BorderLayout());

        // header
        JLabel headerLabel = new JLabel("Welcome to our store! Cart ID:");
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerLabel);
        headerPanel.setPreferredSize(new Dimension(250, 100));
        mainPanel.add(headerPanel, BorderLayout.PAGE_START);

        // body
        JPanel bodyPanel = new JPanel(new GridLayout());
        mainPanel.add(bodyPanel, BorderLayout.CENTER);


        // Add the product panels
        for(int i = 0; i < storeManager.getStoreInventory().getProductList().size(); i++){
            this.productPanels.add(i, new JPanel(new BorderLayout()));
            // Create layout and border with product name
            this.productPanels.get(i).setBorder(BorderFactory.createTitledBorder(storeManager.getStoreInventory().getProductName(i+1)));
            this.productPanels.get(i).setPreferredSize(new Dimension(200, 300));
            BoxLayout layout = new BoxLayout(this.productPanels.get(i), BoxLayout.Y_AXIS);
            this.productPanels.get(i).setLayout(layout);

            JLabel productLabel = new JLabel("Price: " +storeManager.getStoreInventory().getProduct(i+1).getPrice() + " | Stock: " +storeManager.getStoreInventory().getStock(i+1));
            productLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.productLabels[i] = productLabel;
            this.productPanels.get(i).add(this.productLabels[i]);
            //this.productPanels.get(i).add(getAddToCart(i+1));

            // Add the add and remove buttons to the product panel
            JPanel buttonPanel = new JPanel(new FlowLayout());
            JButton[] threeButtons = {getAddB(i+1),getRemoveB(i+1), getAddToCart(i+1)};
            this.buttonArray[i] = threeButtons;
            this.productPanels.get(i).add(buttonPanel);
            buttonPanel.add(this.buttonArray[i][0]);
            buttonPanel.add(this.buttonArray[i][1]);
            buttonPanel.add(this.buttonArray[i][2]);

            bodyPanel.add(this.productPanels.get(i));
            this.productPanels.get(i).add(stockToAddLabel.get(i));
        }

        // Add the control buttons
        bodyPanel.add(getViewCartB());
        bodyPanel.add(getCheckoutB());
        bodyPanel.add(getQuitB());

        // pack
        frame.add(mainPanel);
        frame.pack();

        this.frame.setVisible(true);

    }


    public static void main(String[] args) {
        StoreManager storeManager1 = new StoreManager();
        StoreView storeView1 = new StoreView(storeManager1, storeManager1.generateCartID());
        storeView1.displayGUI();
        /*
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
         */
    }
}
