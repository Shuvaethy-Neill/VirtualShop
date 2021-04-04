package store;

import store.StoreManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


// Written by: Andre Hazim 101141843 and Shuvaethy Neill 101143478

/**
 * This StoreView class is the entry point of the store system
 * For this milestone, it is displayed to the user with a graphical user interface
 *
 * @author Andre Hazim and Shuvaethy Neill
 *
 * @version 2.0
 *
 * @since 2021-04-04
 *
 */

public class StoreView {
    private final JFrame frame;             // Display the store
    private List<JPanel> productPanels;     // List of panels to display the products
    private int[] stockToAdd;               // Represent the quantities of each product to be added to cart
    private List<JLabel> stockToAddLabel;   // The labels to display the quantities of each product to be added to cart
    private StoreManager storeManager;
    private JButton[][] buttonArray;        // Array to hold the add, remove and add to cart buttons
    private JLabel[] productLabels;         // Array to hold the labels for each product
    private String[] productImages;         // Array to hold the url of each product image
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

        //Create the stock to add labels
        for(int i = 0; i < storeManager.getStoreInventory().getProductList().size(); i++){

            JLabel stock = new JLabel("Quantity: " + stockToAdd[i]);
            stock.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.stockToAddLabel.add(stock);
        }
        this.buttonArray = new JButton[5][];
        this.productLabels = new JLabel[5];
        this.productImages = new String[]{"https://static.bhphoto.com/images/images150x150/1589222193_1560964.jpg","https://staging.lifeloop.com.au/wp-content/uploads/2020/04/intel-core-lga-1151-cpu-processor-chipset-top-150x150.jpg",
                "http://www.yeargoo.com/wp-content/uploads/2020/07/Note-Book-Computer-SODIMM-RAM-producer-1-150x150.jpg", "https://www.ilounge.com/wp-content/uploads/2020/02/Get-More-Storage-Space-with-the-110-Seagate-BarraCuda-6TB-Hard-Drive-150x150.png",
                "https://www.pc-canada.com/dd2/img/item/B-150x150/1/14621.jpg"};
        this.storeManager = storeManager;
        this.cartId = cartID;

        // Adds ShoppingCart to hashmap in StoreManager to keep track
        storeManager.addShoppingCart(new ShoppingCart(cartID));
    }

    /**
     * This method adds a product to cart by StoreManager
     *
     * @param productID   int, the id of the product to be added
     * @param amountToAdd int, the amount of the product to add
     */
    private void addToCart(int productID, int amountToAdd) {
        storeManager.addToCart(productID, amountToAdd, this.cartId);
    }

    /**
     * This method removes product from the cart by StoreManager
     *
     * @param productID      int, the id of the product to be removed
     * @param amountToRemove int, the amount of the product to remove
     */
    private void removeFromCart(int productID, int amountToRemove) {
        storeManager.removeFromCart(productID, amountToRemove, this.cartId);
    }

    /**
     * This method gets the total for the transaction
     * @return double, the total price
     */
    private double getTotal() {
        return storeManager.orderTransaction(storeManager.getSMCart(this.cartId), storeManager.getSMItemsInCart(this.cartId));
    }

    /**
     * This method enables and disables the remove button depending on the quantity of a product being added to cart
     * @param productId int: the id of the product that is linked to the button
     */
    private void enableRemove(int productId){
        if(stockToAdd[productId] > 0){
            buttonArray[productId][1].setEnabled(true);
        }
        else{
            buttonArray[productId][1].setEnabled(false);
        }
    }

    /**
     * This method enables and disables the addToCart button depending on the quantity of a product being added to cart
     * @param productId int: the id of the product that is linked to the button
     */
    private void enableAdd(int productId){
        if(stockToAdd[productId] > 0){
            buttonArray[productId][2].setEnabled(true);
        }
        else{
            buttonArray[productId][2].setEnabled(false);
        }
    }

    /**
     * Creates a remove button to decrease the quantity of a product that will be added to the cart
     * @param productId int: the id of the product that is linked to the button
     * @return JButton: returns the created remove button
     */
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

    /**
     * Creates an add button to increase the quantity of a product to be added to the cart
     * @param productId int: the id of the product that is linked to the button
     * @return JButton: returns the created add button
     */
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

    /**
     * Creates an add to cart button for the user to add a product to their cart
     * @param productId int: the id of the product that is linked to the button
     * @return JButton: returns the created add to cart button
     */
    private JButton getAddToCart(int productId){
        JButton addToCart = new JButton("Add to Cart");
        addToCart.setEnabled(false);

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

    /**
     * Creates a remove from cart button for the user to remove an item completely from the cart
     * @param productId int: the id of the product that is linked to the button
     * @return JButton: returns the created remove from cart button
     */
    private JButton getRemoveFromCart(int productId ){
        JButton removeFromCart = new JButton("Remove From Cart");
        removeFromCart.setPreferredSize(new Dimension(50, 20));

        removeFromCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int positionInCart = -1;
                for (int i = 0; i < storeManager.getSMItemsInCart(cartId).size(); i++) {
                    if (productId == storeManager.getSMCart(cartId).get(i).getId()) {
                        positionInCart = i;
                    }

                }
                removeFromCart(productId,(storeManager.getSMItemsInCart(cartId).get(positionInCart)));

                productLabels[productId-1].setText(("Price: " +storeManager.getStoreInventory().getProduct(productId).getPrice() + " | Stock: " +storeManager.getStoreInventory().getStock(productId)));
                removeFromCart.setEnabled(false);
            }
        });
        return removeFromCart;
    }

    /**
     * Creates a view cart button for the user to view their cart
     * @return JButton: returns the created view cart button
     */
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
                    removeButtons[i] = getRemoveFromCart(storeManager.getSMCart(cartId).get(i).getId());
                    p1.add(productInfo[i]);
                    p1.add(removeButtons[i]);

                    int j = i;
                    removeButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            productInfo[j].setText(null);
                            removeButtons[j].setVisible(false);
                        }
                    });
                }

                f.add(p1);
                f.pack();

                f.setVisible(true);
            }

        });
        return viewCartB;
    }

    /**
     * Creates a checkout button for the user to checkoout from the store
     * @return JButton: returns the created checkout button
     */
    private JButton getCheckoutB() {
        JButton checkoutB = new JButton("Checkout");
        checkoutB.setPreferredSize(new Dimension(30, 20));
        checkoutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame f = new JFrame("Checkout");
                f.setMinimumSize(new Dimension(100,100));

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
                JLabel total = new JLabel("Total: $" + getTotal());
                p1.add(total);

                JButton okB = new JButton("OK");
                okB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (JOptionPane.showConfirmDialog(frame, "Pressing OK will close the whole program. Are you sure you want to quit?")
                                == JOptionPane.OK_OPTION) {
                            f.setVisible(false);
                            f.dispose();
                            frame.setVisible(false);
                            frame.dispose();
                        }
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

    /**
     * Creates a quit button for the user to exit the store
     * @return JButton: returns the quit created button
     */
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
        this.frame.setMinimumSize(new Dimension(900, 900));
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Header panel specifications
        JLabel headerLabel = new JLabel("Welcome to our store! Cart ID: 0");
        JPanel headerPanel = new JPanel();
        Color myBlue = new Color(223, 243, 255);
        headerPanel.setBackground(myBlue);
        headerPanel.add(headerLabel);
        headerPanel.setPreferredSize(new Dimension(250, 100));
        mainPanel.add(headerPanel, BorderLayout.PAGE_START);

        // Body panel specifications
        JPanel bodyPanel = new JPanel(new GridLayout(2,3));
        bodyPanel.setBackground(myBlue);
        mainPanel.add(bodyPanel, BorderLayout.CENTER);

        // Footer panel specification
        JPanel footerPanel = new JPanel(new GridLayout(1,1));
        footerPanel.setPreferredSize(new Dimension(250,100));
        footerPanel.setBackground(myBlue);
        mainPanel.add(footerPanel, BorderLayout.PAGE_END);

        // Product panel specifications
        for(int i = 0; i < storeManager.getStoreInventory().getProductList().size(); i++){
            // Create the product image
            Image image = null;
            try {
                URL url = new URL(this.productImages[i]);
                image = ImageIO.read(url);


            } catch (Exception exp) {
                exp.printStackTrace();
            }

            // Create a label of the product image and add it to the product panel
            JLabel label = new JLabel(new ImageIcon(image));
            this.productPanels.add(i, new JPanel(new GridLayout(3,1)));
            // Create layout and border with product name
            this.productPanels.get(i).setBorder(BorderFactory.createTitledBorder(storeManager.getStoreInventory().getProductName(i+1)));
            this.productPanels.get(i).setPreferredSize(new Dimension(200, 200));
            BoxLayout layout = new BoxLayout(this.productPanels.get(i), BoxLayout.Y_AXIS);
            this.productPanels.get(i).setLayout(layout);

            JLabel productLabel = new JLabel("Price: " +storeManager.getStoreInventory().getProduct(i+1).getPrice() + " | Stock: " +storeManager.getStoreInventory().getStock(i+1));
            productLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.productLabels[i] = productLabel;
            this.productPanels.get(i).add(this.productLabels[i]);
            this.productPanels.get(i).setBackground(Color.WHITE);
            this.productPanels.get(i).add(label);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Add the add, remove and add too cart buttons to the product panel
            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.setBackground(Color.WHITE);
            JButton[] threeButtons = {getAddB(i+1),getRemoveB(i+1), getAddToCart(i+1)};
            this.buttonArray[i] = threeButtons;
            this.productPanels.get(i).add(buttonPanel);
            buttonPanel.add(this.buttonArray[i][0]);
            buttonPanel.add(this.buttonArray[i][1]);
            buttonPanel.add(this.buttonArray[i][2]);
            this.productPanels.get(i).add(stockToAddLabel.get(i));

            // Add product panel to the body panel
            bodyPanel.add(this.productPanels.get(i));

        }

        // Add the control buttons
        footerPanel.add(getViewCartB());
        footerPanel.add(getCheckoutB());
        footerPanel.add(getQuitB());

        // pack
        frame.add(mainPanel);
        frame.pack();

        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        StoreManager storeManager = new StoreManager();
        StoreView storeView = new StoreView(storeManager, storeManager.generateCartID());
        storeView.displayGUI();
    }
}
