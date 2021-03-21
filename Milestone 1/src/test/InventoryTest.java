// Written by: Shuvaethy Neill 101143478 and Andre Hazim 101141843

package test;
import org.junit.jupiter.api.*;
import store.Inventory;
import store.Product;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This InventoryTest class tests the functionality of the Inventory class
 *
 * @author Shuvaethy Neill and Andre Hazim
 *
 * @version 1.0
 *
 * @since 2021-03-17
 *
 */

class InventoryTest {
    private static Inventory inventory;
    private static Product newProduct;

    /**
     * Initialize the variables used throughout the test suite
     */
    @BeforeEach
    public void init(){
        // Executed before the tests
        inventory = new Inventory();
        newProduct = new Product("Solid State Drive", 6, 129.99);
    }

    /**
     * Tests the removeStock method to make sure the stock of a product is correctly removed
     */
    @Test
    void testRemoveStock() {
        System.out.println("Testing the removeStock method in the Inventory Class");
        System.out.println();

        boolean temp1 = inventory.removeStock(2, 25);
        assertEquals(25,inventory.getStock(2),"There is an issue with your removeStock method");
        assertTrue(temp1);

        // Removing more than what is available
        boolean temp2 = inventory.removeStock(3, 60);
        assertEquals(0,inventory.getStock(3),"There is an issue with your removeStock method");
        assertTrue(temp2);

        // Removing stock from an item that does not exist in inventory
        boolean temp3 = inventory.removeStock(7,30);
        assertEquals(-1,inventory.getStock(7),"That product does not exist in inventory");
        assertFalse(temp3);

        boolean temp4 = inventory.removeStock(2, 10);
        assertEquals(15,inventory.getStock(2),"There is an issue with your removeStock method");
        assertTrue(temp4);

        // Removing stock from an item that does not exist in inventory
        boolean temp5 = inventory.removeStock(6, 10);
        assertEquals(-1,inventory.getStock(6),"There is an issue with your removeStock method");
        assertFalse(temp5);
    }

    /**
     * Tests the addStock method to make sure the stock of a product is correctly added
     */
    @Test
    void testAddStock() {
        System.out.println("Testing the addStock method in the Inventory Class");
        System.out.println();

        inventory.addStock(inventory.getProduct(2),50);
        assertEquals(100, inventory.getStock(2),"There is an issue with addStock");

        inventory.addStock(inventory.getProduct(3),1);
        assertEquals(51, inventory.getStock(3),"There is an issue with addStock");

        // Adding stock to a product with no stock
        inventory.removeStock(1, 60);
        inventory.addStock(inventory.getProduct(1),5);
        assertEquals(5, inventory.getStock(1),"There is an issue with addStock");

        // Add a product to inventory and corresponding stock
        inventory.addStock(newProduct,123);
        // Check if new product exists in inventory
        assertTrue(inventory.getProductList().contains(inventory.getProduct(6)));
        assertEquals(123, inventory.getStock(6),"The new item was not correctly added, there is an issue with addStock");
    }

}
