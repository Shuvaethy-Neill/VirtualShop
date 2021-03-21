// Written by: Shuvaethy Neill 101143478
// Partner: Andre Hazim 101141843

package test;

import org.junit.jupiter.api.*;
import store.Product;
import store.ShoppingCart;
import store.StoreManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This ShoppingCartTest class tests the functionality of the store.ShoppingCart class
 *
 * @author Shuvaethy Neill
 *
 * @version 1.0
 *
 * @since 2021-03-17
 *
 */

public class ShoppingCartTest {
    private static ShoppingCart shoppingCart;
    private static StoreManager storeManager;

    /**
     * Initialize the variables used throughout the test suite
     */
    @BeforeEach
    public void init() {
        // Executed before each test
        storeManager = new StoreManager();
        shoppingCart = new ShoppingCart(storeManager.generateCartID());
    }

    /**
     * Tests the getCart method to ensure the correct cart products are returned
     */
    @Test
    public void testGetCart(){
        System.out.println("Testing the getCart method in the store.ShoppingCart Class");
        System.out.println();

        storeManager.addShoppingCart(shoppingCart);

        // Create test array list representing a cart
        ArrayList<Product> cart = new ArrayList<>();

        // Testing with an empty cart (no products)
        assertEquals(cart, shoppingCart.getCart(), "The cart should be empty. There is a bug in the getCart method in the store.ShoppingCart class.");

        storeManager.addToCart(1, 1, 0);
        cart.add(storeManager.getStoreInventory().getProduct(1));
        assertEquals(cart, shoppingCart.getCart(), "There is a bug in the getCart method in the store.ShoppingCart class");

        storeManager.addToCart(3, 7, 0);
        cart.add(storeManager.getStoreInventory().getProduct(3));
        assertEquals(cart, shoppingCart.getCart(), "There is a bug in the getCart method in the store.ShoppingCart class");

        storeManager.removeFromCart(1, 2, 0);
        cart.remove(storeManager.getStoreInventory().getProduct(1));
        assertEquals(cart, shoppingCart.getCart(), "There is a bug in the getCart method in the store.ShoppingCart class");

        storeManager.addToCart(5, 3, 0);
        cart.add(storeManager.getStoreInventory().getProduct(5));
        assertEquals(cart, shoppingCart.getCart(), "There is a bug in the getCart method in the store.ShoppingCart class");
    }

    /**
     * Tests the getItemsInCart to ensure the correct item quantities in the cart are returned
     */
    @Test
    public void testGetItemsInCart(){
        System.out.println("Testing the getItemsInCart method in the store.ShoppingCart Class");
        System.out.println();

        storeManager.addShoppingCart(shoppingCart);

        // Create test array list representing the items (quantities) in a cart
        ArrayList<Integer> cartItems = new ArrayList<>();

        // Testing with an empty cart (no quantity of items)
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "The cart should be empty. There is a bug in the getItemsInCart method in the store.ShoppingCart class");

        storeManager.addToCart(1, 1,0);
        cartItems.add(1);
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the store.ShoppingCart class");

        storeManager.addToCart(1, 5,0);
        cartItems.set(0, 6);
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the store.ShoppingCart class");

        storeManager.addToCart(3, 20,0);
        cartItems.add(20);
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the store.ShoppingCart class");

        storeManager.removeFromCart(1, 9, 0);
        cartItems.remove(0);
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the store.ShoppingCart class");

        storeManager.addToCart(4, 3, 0);
        cartItems.add(3);
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the store.ShoppingCart class");

        storeManager.removeFromCart(3, 2, 0);
        cartItems.set(0, 18);
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the store.ShoppingCart class");

    }
}
