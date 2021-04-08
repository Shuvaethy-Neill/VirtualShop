package store;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

// Written by: Shuvaethy Neill 101143478
// Partner: Andre Hazim 101141843

/**
 * This store.StoreManager class is the "brain" of the system and for this
 * milestone it manages the Inventory and ShoppingCarts.
 * @author Shuvaethy Neill
 *
 * @version 2.0
 *
 * @since 2021-03-07
 *
 */

public class StoreManager {
    private Inventory managerInventory;                 // store.Inventory being managed
    private int numCarts;                               // Number of ShoppingCarts
    private HashMap <Integer, ShoppingCart> carts;      // The cartIDs and ShoppingCarts created

    /**
     * Default constructor for StoreManager
     *
     */
    public StoreManager(){
        managerInventory = new Inventory();
        carts = new HashMap <Integer, ShoppingCart>();
        numCarts = -1;
    }

    /**
     * Constructor for StoreManager
     *
     * @param inv Inventory, existing store.Inventory
     */
    public StoreManager(Inventory inv){
        managerInventory = inv;
        carts = new HashMap<Integer, ShoppingCart>();
        numCarts = -1;
    }

    /**
     * This method provides access to the available inventory products that the StoreManager is managing
     *
     * @return store.Inventory, the store.Inventory being managed
     */

    public ArrayList<Product> getAvailableProducts(){
        return managerInventory.getProductList();
    }

    /**
     * This method checks the available stock of a product given its object
     * @param product Product, the product to be checked
     * @return int, value of stock available for that specific product
     */
    public int checkInventoryStock(Product product){
        if (product != null){
            return managerInventory.getProductQuantity(product);
        }
        return -1;
    }

    /**
     * This method keeps track of how many cartIDs there are and generates a new cartID
     * everytime a new ShoppingCart is made
     *
     * @return int, new cartID
     */
    public int generateCartID(){
        numCarts += 1;
        return numCarts;
    }

    /**
     * This method adds a ShoppingCart along with its cartID to
     * carts (the HashMap that keeps track of the cartIDs and their shopping carts)
     *
     * @param shoppingCart ShoppingCart, the new shopping cart being added
     */
    public void addShoppingCart(ShoppingCart shoppingCart){
        this.carts.put(numCarts, shoppingCart);
    }

    /**
     * This method gets the cart of the shopping cart given its cartID
     *
     * @param cartId int, the cartID associated with the specific shopping cart
     * @return ArrayList<Product>, the arraylist of the cart of the shopping cart
     */
    public ArrayList<Product> getSMCart(int cartId){
        ShoppingCart shoppingCart = carts.get(cartId);
        return shoppingCart.getCart();
    }

    /**
     * This method gets the number of items in the shopping cart given its cartID
     *
     * @param cartId int, the cartID associated with the specific shopping cart
     * @return ArrayList<Integer>, the arraylist of the number of items in the cart
     */
    public ArrayList<Integer> getSMItemsInCart(int cartId){
        ShoppingCart shoppingCart = carts.get(cartId);
        return shoppingCart.getItemsInCart();
    }

    /**
     * This method adds items to a user's shopping cart
     *
     * @param productID int, the ID of the product being added to the cart
     * @param amountOfProduct int, the quantity of the product being added
     * @param cartId int, the specific cartID associated with the shopping cart
     */
    public void addToCart(int productID, int amountOfProduct, int cartId){
        ShoppingCart shoppingCart = carts.get(cartId);


        shoppingCart.addToCart(productID,amountOfProduct,this.managerInventory);
        // Update the carts HashMap with the new additions
        carts.put(cartId, shoppingCart);
        // Decrease stock in store store.Inventory when user adds something to cart
        managerInventory.removeProductQuantity(managerInventory.getProduct(productID), amountOfProduct);

    }

    /**
     * This method removes items from a user's cart
     *
     * @param productID int, the ID of the product being removed to the cart
     * @param amountOfProduct int, the quantity of the product being removed
     * @param cartId int, the specific cartID associated with the shopping cart
     */
    public void removeFromCart(int productID, int amountOfProduct,int cartId){
        ShoppingCart shoppingCart = carts.get(cartId);
        int removedProduct = shoppingCart.removeFromCart(productID, amountOfProduct);

        // Update the carts HashMap after removals
        carts.put(cartId, shoppingCart);
        // Add stock in store store.Inventory when user removes something to cart
        managerInventory.addProductQuantity(managerInventory.getProduct(productID) ,removedProduct);
    }

    /**
     * This method is an accessor for the carts hashmap
     * @return HashMap<Integer,ShoppingCart> carts
     */
    public HashMap<Integer,ShoppingCart> getCarts(){
        return this.carts;
    }

    /**
     * This method takes an order in the form of two parallel arrayLists and completes the store transaction
     *
     * @param items ArrayList<Product>, the products being purchased
     * @param quantities ArrayList<Integer>, the quantities of each product being purchased
     * @return double, the total price for order purchase
     */
    public double orderTransaction(ArrayList<Product> items, ArrayList<Integer> quantities){
        double total = 0;

        // Totals the transaction cost
        for(int i = 0; i < items.size(); i ++){
            total += (quantities.get(i) * managerInventory.getPrice(items.get(i).getId()));
        }

        DecimalFormat df = new DecimalFormat("#.##");
        total = Double.valueOf(df.format(total));

        return total;
    }
}
