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
 * This ShoppingCartTest class tests the functionality of the ShoppingCart class
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
     *
     */
    @BeforeAll
    public static void init() {
        shoppingCart = new ShoppingCart();
        storeManager = new StoreManager();
    }

    /**
     *
     */
    @Test
    public void testGetCart(){
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("Testing the getCart method in the ShoppingCart Class");
        System.out.println();

        int cartID = storeManager.generateCartID();
        ShoppingCart shoppingCart = new ShoppingCart(cartID);
        storeManager.addShoppingCart(shoppingCart);
        ArrayList<Product> cart = new ArrayList<>();
        assertEquals(cart, shoppingCart.getCart(), "There is a bug in the getCart method in the ShoppingCart class");

        storeManager.addToCart(1, 1, cartID);
        cart.add(storeManager.getStoreInventory().getProduct(1));
        assertEquals(cart, shoppingCart.getCart(), "There is a bug in the getCart method in the ShoppingCart class");

        storeManager.addToCart(3, 7, cartID);
        cart.add(storeManager.getStoreInventory().getProduct(3));
        assertEquals(cart, shoppingCart.getCart(), "There is a bug in the getCart method in the ShoppingCart class");

        storeManager.removeFromCart(1, 2, cartID);
        cart.remove(storeManager.getStoreInventory().getProduct(1));
        assertEquals(cart, shoppingCart.getCart(), "There is a bug in the getCart method in the ShoppingCart class");

        storeManager.addToCart(5, 3, cartID);
        cart.add(storeManager.getStoreInventory().getProduct(5));
        assertEquals(cart, shoppingCart.getCart(), "There is a bug in the getCart method in the ShoppingCart class");

    }

    /**
     *
     */
    @Test
    public void testGetItemsInCart(){
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("Testing the getItemsInCart method in the ShoppingCart Class");
        System.out.println();

        int cartID = storeManager.generateCartID();
        ShoppingCart shoppingCart = new ShoppingCart(cartID);
        storeManager.addShoppingCart(shoppingCart);
        ArrayList<Integer> cartItems = new ArrayList<>();
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the ShoppingCart class");

        storeManager.addToCart(1, 1,cartID);
        cartItems.add(1);
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the ShoppingCart class");

        storeManager.addToCart(1, 5,cartID);
        cartItems.set(0, 6);
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the ShoppingCart class");

        storeManager.addToCart(3, 20,cartID);
        cartItems.add(20);
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the ShoppingCart class");

        storeManager.removeFromCart(1, 9, cartID);
        cartItems.remove(0);
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the ShoppingCart class");

        storeManager.addToCart(4, 3, cartID);
        cartItems.add(3);
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the ShoppingCart class");

        storeManager.removeFromCart(3, 2, cartID);
        cartItems.set(0, 18);
        assertEquals(cartItems, shoppingCart.getItemsInCart(), "There is a bug in the getItemsInCart method in the ShoppingCart class");

    }

}
