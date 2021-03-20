// Written by: Shuvaethy Neill 101143478
// Partner: Andre Hazim 101141843

package test;

import org.junit.jupiter.api.*;
import store.Inventory;
import store.Product;
import store.ShoppingCart;
import store.StoreManager;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This StoreManagerTest class tests the functionality of the store.StoreManager class
 * @author Shuvaethy Neill
 *
 * @version 1.0
 *
 * @since 2021-03-17
 *
 */

public class StoreManagerTest {
    private static StoreManager storeManager;

    @BeforeAll
    public static void init(){
        storeManager = new StoreManager();
        //initialize all the store managers here or in the methods

    }

    @Test
    public void testGetStoreInventory(){
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("Testing the getStoreInventory method in the StoreManager Class");
        System.out.println();

        //How do you test for the case where an inv does not already exist?

        Inventory inv = new Inventory();
        StoreManager storeManager2 = new StoreManager(inv);
        assertEquals(inv, storeManager2.getStoreInventory(), "There is a bug in the getStoreInventory method in the StoreManager class");

    }

    @Test
    public void testCheckInventoryStock(){
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("Testing the checkInventoryStock method in the StoreManager Class");
        System.out.println();

        Inventory inv = new Inventory();
        StoreManager testStoreManager = new StoreManager(inv);
        assertEquals(50, testStoreManager.checkInventoryStock(inv.getProduct(1)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

        inv.removeStock(3, 20);
        assertEquals(30, testStoreManager.checkInventoryStock(inv.getProduct(3)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

        inv.removeStock(3, 35);
        assertEquals(0, testStoreManager.checkInventoryStock(inv.getProduct(3)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

        inv.removeStock(1, 55);
        assertEquals(0, testStoreManager.checkInventoryStock(inv.getProduct(1)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

        assertEquals(-1, testStoreManager.checkInventoryStock(inv.getProduct(8)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

        assertEquals(-1, testStoreManager.checkInventoryStock(inv.getProduct(-2)), "There is a bug in the checkInventoryStock method in the StoreManager Class");

    }

    @Test
    public void testGenerateCartID(){
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("Testing the generateCartID method in the StoreManager Class");
        System.out.println();

        //SHOULD I INITIALIZE STORE MANAGER HERE?
        // print out cartIDs?

        assertEquals(0, storeManager.generateCartID(),"There is a big in the generateCartID method in the StoreManager class");
        assertEquals(1, storeManager.generateCartID(),"There is a big in the generateCartID method in the StoreManager class");
        assertEquals(2, storeManager.generateCartID(),"There is a big in the generateCartID method in the StoreManager class");

    }

    @Test
    public void testAddShoppingCart(){
        /*
        StoreManager storeManager = new StoreManager();
        storeManager.generateCartID();
        ShoppingCart shoppingCart1 = new ShoppingCart(0);
        storeManager.addShoppingCart(shoppingCart1);

        HashMap testCarts = new HashMap<Integer, ShoppingCart>();
        testCarts.put(0,shoppingCart1);

        assertEquals(testCarts, );

         */

    }

    @Test
    public void testGetSMCart(){
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("Testing the getSMCart method in the StoreManager Class");
        System.out.println();

        StoreManager storeManager = new StoreManager();
        int cartID = storeManager.generateCartID();
        ShoppingCart shoppingCart = new ShoppingCart(0);
        storeManager.addShoppingCart(shoppingCart);
        storeManager.getSMCart(cartID);

        ArrayList<Product> testSMCart = new ArrayList<Product>();
        assertEquals(testSMCart, storeManager.getSMCart(cartID), "There is a bug in the getSMCart method in the StoreManager class");

        storeManager.addToCart(1, 1, 0);
        testSMCart.add(storeManager.getStoreInventory().getProduct(1));
        assertEquals(testSMCart, storeManager.getSMCart(cartID), "There is a bug in the getSMCart method in the StoreManager class");

        storeManager.addToCart(2, 3, 0);
        testSMCart.add(storeManager.getStoreInventory().getProduct(2));
        assertEquals(testSMCart, storeManager.getSMCart(cartID), "There is a bug in the getSMCart method in the StoreManager class");

        storeManager.removeFromCart(1, 5, 0);
        testSMCart.remove(storeManager.getStoreInventory().getProduct(1));
        assertEquals(testSMCart, storeManager.getSMCart(cartID), "There is a bug in the getSMCart method in the StoreManager class");

    }

    @Test
    public void testGetSMItemsInCart(){
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("Testing the getSMItemsInCart method in the StoreManager Class");
        System.out.println();

        StoreManager storeManager = new StoreManager();
        int cartID = storeManager.generateCartID();
        ShoppingCart shoppingCart = new ShoppingCart(0);
        storeManager.addShoppingCart(shoppingCart);
        storeManager.getSMCart(cartID);

        ArrayList<Integer> testSMItemsInCart = new ArrayList<Integer>();
        assertEquals(testSMItemsInCart, storeManager.getSMItemsInCart(cartID), "There is a bug in the getSMCart method in the StoreManager class");

        storeManager.addToCart(1, 1, 0);
        testSMItemsInCart.add(1);
        assertEquals(testSMItemsInCart, storeManager.getSMItemsInCart(cartID), "There is a bug in the getSMCart method in the StoreManager class");

        storeManager.addToCart(2, 3, 0);
        testSMItemsInCart.add(3);
        assertEquals(testSMItemsInCart, storeManager.getSMItemsInCart(cartID), "There is a bug in the getSMCart method in the StoreManager class");

        storeManager.removeFromCart(1, 5, 0);
        testSMItemsInCart.remove(0);
        assertEquals(testSMItemsInCart, storeManager.getSMItemsInCart(cartID), "There is a bug in the getSMCart method in the StoreManager class");

        storeManager.removeFromCart(2, 1, 0);
        testSMItemsInCart.set(0, 2);
        assertEquals(testSMItemsInCart, storeManager.getSMItemsInCart(cartID), "There is a bug in the getSMCart method in the StoreManager class");

    }

    @Test
    public void testAddToCart(){

    }

    @Test
    public void testRemoveFromCart(){

    }

    @Test
    public void testOrderTransaction(){
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("Testing the orderTransaction method in the StoreManager Class:");
        System.out.println();

        StoreManager storeManager = new StoreManager();
        storeManager.generateCartID();
        ShoppingCart shoppingCart1 = new ShoppingCart(0);
        storeManager.addShoppingCart(shoppingCart1);
        storeManager.addToCart(1,2,0);
        assertEquals(200.0, storeManager.orderTransaction(storeManager.getSMCart(0), storeManager.getSMItemsInCart(0)));

        storeManager.generateCartID();
        ShoppingCart shoppingCart2 = new ShoppingCart(1);
        storeManager.addShoppingCart(shoppingCart2);
        storeManager.addToCart(2,4,1);
        assertEquals(603.96, storeManager.orderTransaction(storeManager.getSMCart(1), storeManager.getSMItemsInCart(1)));

        storeManager.generateCartID();
        ShoppingCart shoppingCart3 = new ShoppingCart(2);
        storeManager.addShoppingCart(shoppingCart3);
        storeManager.addToCart(5,2,2);
        storeManager.addToCart(1,3,2);
        assertEquals(559.98, storeManager.orderTransaction(storeManager.getSMCart(2), storeManager.getSMItemsInCart(2)));

        storeManager.generateCartID();
        ShoppingCart shoppingCart4 = new ShoppingCart(3);
        storeManager.addShoppingCart(shoppingCart4);
        storeManager.addToCart(4,2,3);
        storeManager.addToCart(1,1,3);
        storeManager.addToCart(2,1,3);
        storeManager.addToCart(1,3,3);
        assertEquals(686.97, storeManager.orderTransaction(storeManager.getSMCart(3), storeManager.getSMItemsInCart(3)));

    }
}
