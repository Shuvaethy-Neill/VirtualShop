// Written by: Shuvaethy Neill 101143478
// Partner: Andre Hazim 101141843

package storetest;

import org.junit.jupiter.api.*;
import store.Inventory;
import store.Product;
import store.ShoppingCart;
import store.StoreManager;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This StoreManagerTest class tests the functionality of the StoreManager class
 *
 * @author Shuvaethy Neill
 *
 * @version 1.0
 *
 * @since 2021-03-17
 *
 */

public class StoreManagerTest {
    private static StoreManager storeManager;
    private static Inventory inv;
    private static ShoppingCart shoppingCart;

    /**
     * Initialize the variables used throughout the test suite
     */
    @BeforeEach
    public void init(){
        // Executed before each test
        inv = new Inventory();
        storeManager = new StoreManager(inv);
        shoppingCart = new ShoppingCart(storeManager.generateCartID());
    }

    /**
     * Tests the checkInventoryStock method to make sure the stock is adjusted accordingly
     */
    @Test
    public void testCheckInventoryStock(){
        System.out.println("Testing the checkInventoryStock method in the StoreManager Class");
        System.out.println();

        assertEquals(50, storeManager.checkInventoryStock(inv.getProduct(1)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

        inv.removeStock(3, 20);
        assertEquals(30, storeManager.checkInventoryStock(inv.getProduct(3)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

        inv.addStock(inv.getProduct(3), 1);
        assertEquals(31, storeManager.checkInventoryStock(inv.getProduct(3)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

        // More than what exists is removed (stock should be 0)
        inv.removeStock(3, 35);
        assertEquals(0, storeManager.checkInventoryStock(inv.getProduct(3)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

        // More than what exists is removed (stock should be 0)
        inv.removeStock(1, 55);
        assertEquals(0, storeManager.checkInventoryStock(inv.getProduct(1)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

        // Invalid productID (does not exist - should return -1)
        assertEquals(-1, storeManager.checkInventoryStock(inv.getProduct(8)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

        // Invalid productID (does not exist - should return -1)
        assertEquals(-1, storeManager.checkInventoryStock(inv.getProduct(-2)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

    }

    /**
     * Tests the generateCartID method to make sure the correct cartIDs are generated accordingly
     */
    @Test
    public void testGenerateCartID(){
        System.out.println("Testing the generateCartID method in the store.StoreManager Class");
        System.out.println();

        assertEquals(1, storeManager.generateCartID(),"Wrong cartID. There is a bug in the generateCartID method in the store.StoreManager class");
        assertEquals(2, storeManager.generateCartID(),"Wrong cartID. There is a bug in the generateCartID method in the store.StoreManager class");
        assertEquals(3, storeManager.generateCartID(),"Wrong cartID. There is a bug in the generateCartID method in the store.StoreManager class");
    }

    /**
     * Tests the addShoppingCart method to make sure a cart is correctly added with it's cartID
     */
    @Test
    public void testAddShoppingCart(){
        System.out.println("Testing the addShoppingCart method in the StoreManager Class");
        System.out.println();

        storeManager.addShoppingCart(shoppingCart);

        HashMap<Integer,ShoppingCart> testCarts = new HashMap<Integer,ShoppingCart>();
        testCarts.put(0,shoppingCart);

        assertEquals(testCarts, storeManager.getCarts(), "There is something wrong with the addShoppingCart method");
    }

    /**
     * Tests the addToCart method to make sure items are correctly added to a cart
     */
    @Test
    public void testAddToCart(){
        System.out.println("Testing the addToCart method in the StoreManager Class");
        System.out.println();


        storeManager.addShoppingCart(shoppingCart);

        // Create a test cart (products and their quantities)
        ArrayList<Product> tempCart = new ArrayList<Product>();
        ArrayList<Integer> tempItemsInCart = new ArrayList<Integer>();

        storeManager.addToCart(1,2,0);

        tempCart.add(inv.getProduct(1));
        tempItemsInCart.add(2);
        assertEquals(tempCart, shoppingCart.getCart(), "There is a bug in the addToCart method in the StoreManager class");
        assertEquals(tempItemsInCart, shoppingCart.getItemsInCart(), "There is a bug in the addToCart method in the StoreManager class");
        assertEquals(48, storeManager.getStoreInventory().getStock(1), "There is a bug in the addToCart method in the StoreManager class");

        storeManager.addToCart(4,15,0);

        tempCart.add(storeManager.getStoreInventory().getProduct(4));
        tempItemsInCart.add(15);
        assertEquals(tempCart, shoppingCart.getCart(), "There is a bug in the addToCart method in the store.StoreManager class");
        assertEquals(tempItemsInCart, shoppingCart.getItemsInCart(), "There is a bug in the addToCart method in the store.StoreManager class");
        assertEquals(35, storeManager.getStoreInventory().getStock(4), "There is a bug in the addToCart method in the StoreManager class");

        storeManager.addToCart(1,25,0);

        tempItemsInCart.set(0,27);
        assertEquals(tempCart, shoppingCart.getCart(), "There is a bug in the addToCart method in the store.StoreManager class");
        assertEquals(tempItemsInCart, shoppingCart.getItemsInCart(), "There is a bug in the addToCart method in the store.StoreManager class");
        assertEquals(23, storeManager.getStoreInventory().getStock(1), "There is a bug in the addToCart method in the StoreManager class");

    }

    /**
     * Tests the removeFromCart method to make sure items are correctly removed from a cart
     */
    @Test
    public void testRemoveFromCart(){
        System.out.println("Testing the removeFromCart method in the store.StoreManager Class:");
        System.out.println();

        storeManager.addShoppingCart(shoppingCart);

        // Create a test cart (products and their quantities)
        ArrayList<Product> tempCart = new ArrayList<Product>();
        ArrayList<Integer> tempItemsInCart = new ArrayList<Integer>();

        // Start with items in cart to be able to remove
        storeManager.addToCart(1,2,0);
        storeManager.addToCart(3,7,0);
        storeManager.addToCart(5,10,0);

        tempCart.add(storeManager.getStoreInventory().getProduct(1));
        tempCart.add(storeManager.getStoreInventory().getProduct(3));
        tempCart.add(storeManager.getStoreInventory().getProduct(5));

        tempItemsInCart.add(2);
        tempItemsInCart.add(7);
        tempItemsInCart.add(10);

        storeManager.removeFromCart(3,4,0);

        tempItemsInCart.set(1,3);
        assertEquals(tempCart, shoppingCart.getCart(), "There is a bug in the removeFromCart method in the store.StoreManager class");
        assertEquals(tempItemsInCart, shoppingCart.getItemsInCart(), "There is a bug in the removeFromCart method in the store.StoreManager class");
        assertEquals(47, storeManager.getStoreInventory().getStock(3), "There is a bug in the addToCart method in the store.StoreManager class");

        // Remove all of a product from cart
        storeManager.removeFromCart(5,10,0);

        tempCart.remove(2);
        tempItemsInCart.remove(2);
        assertEquals(tempCart, shoppingCart.getCart(), "There is a bug in the removeFromCart method in the store.StoreManager class");
        assertEquals(tempItemsInCart, shoppingCart.getItemsInCart(), "There is a bug in the removeFromCart method in the store.StoreManager class");
        assertEquals(50, storeManager.getStoreInventory().getStock(5), "There is a bug in the addToCart method in the store.StoreManager class");

        storeManager.removeFromCart(1,2,0);

        tempCart.remove(0);
        tempItemsInCart.remove(0);
        assertEquals(tempCart, shoppingCart.getCart(), "There is a bug in the removeFromCart method in the store.StoreManager class");
        assertEquals(tempItemsInCart, shoppingCart.getItemsInCart(), "There is a bug in the removeFromCart method in the store.StoreManager class");
        assertEquals(50, storeManager.getStoreInventory().getStock(1), "There is a bug in the addToCart method in the store.StoreManager class");

        storeManager.removeFromCart(3,1,0);

        tempItemsInCart.set(0,2);
        assertEquals(tempCart, shoppingCart.getCart(), "There is a bug in the removeFromCart method in the store.StoreManager class");
        assertEquals(tempItemsInCart, shoppingCart.getItemsInCart(), "There is a bug in the removeFromCart method in the store.StoreManager class");
        assertEquals(48, storeManager.getStoreInventory().getStock(3), "There is a bug in the addToCart method in the store.StoreManager class");

    }

    /**
     * Tests the orderTransaction method to make sure the correct total is returned
     */
    @Test
    public void testOrderTransaction(){
        System.out.println("Testing the orderTransaction method in the store.StoreManager Class:");
        System.out.println();

        int cartID1 = storeManager.generateCartID();
        ShoppingCart shoppingCart1 = new ShoppingCart(cartID1);
        storeManager.addShoppingCart(shoppingCart1);
        storeManager.addToCart(1,2,cartID1);
        assertEquals(200.0, storeManager.orderTransaction(storeManager.getSMCart(cartID1), storeManager.getSMItemsInCart(cartID1)));

        int cartID2 = storeManager.generateCartID();
        ShoppingCart shoppingCart2 = new ShoppingCart(cartID2);
        storeManager.addShoppingCart(shoppingCart2);
        storeManager.addToCart(2,4,cartID2);
        assertEquals(603.96, storeManager.orderTransaction(storeManager.getSMCart(cartID2), storeManager.getSMItemsInCart(cartID2)));

        int cartID3 = storeManager.generateCartID();
        ShoppingCart shoppingCart3 = new ShoppingCart(cartID3);
        storeManager.addShoppingCart(shoppingCart3);
        storeManager.addToCart(5,2,cartID3);
        storeManager.addToCart(1,3,cartID3);
        assertEquals(559.98, storeManager.orderTransaction(storeManager.getSMCart(cartID3), storeManager.getSMItemsInCart(cartID3)));

        int cartID4 = storeManager.generateCartID();
        ShoppingCart shoppingCart4 = new ShoppingCart(cartID4);
        storeManager.addShoppingCart(shoppingCart4);
        storeManager.addToCart(4,2,cartID4);
        storeManager.addToCart(1,1,cartID4);
        storeManager.addToCart(2,1,cartID4);
        storeManager.addToCart(1,3,cartID4);
        assertEquals(686.97, storeManager.orderTransaction(storeManager.getSMCart(cartID4), storeManager.getSMItemsInCart(cartID4)));
    }
}
