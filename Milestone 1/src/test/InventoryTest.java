import org.junit.jupiter.api.*;
import store.Inventory;
import store.Product;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private static Inventory inventory;
    @BeforeAll
    public static void init(){
        inventory = new Inventory();

    }
    @Test
    public void getStock() {
        assertEquals(50, inventory.getStock(1),"There is an issue with get stock or your id is not correct");
        assertEquals(50, inventory.getStock(2),"There is an issue with get stock or your id is not correct");
        assertEquals(50, inventory.getStock(3),"There is an issue with get stock or your id is not correct");
        assertEquals(50, inventory.getStock(4),"There is an issue with get stock or your id is not correct");
        assertEquals(50, inventory.getStock(5),"There is an issue with get stock or your id is not correct");
        assertEquals(-1, inventory.getStock(6),"There is an issue with get stock or your id is not correct");
    }

    @Test
    void addStock() {
        Product motherBoard = new Product("Motherboard", 1, 100.0);
        Product solidStateDrive = new Product("Solid State Drive", 6, 129.99);
        inventory.addStock(solidStateDrive,123);
        inventory.addStock(motherBoard,50);
        assertEquals(123, inventory.getStock(6),"There is an issue with addStock or your id is not correct");
        assertEquals(100, inventory.getStock(1),"There is an issue with addStock or your id is not correct");
    }

    @Test
    void removeStock() {
        inventory.removeStock(2, 25);
        inventory.removeStock(3, 60);
        inventory.removeStock(7,30);
        assertEquals(25,inventory.getStock(2),"There is an issue with your removeStock or your id is not correct");
        assertEquals(0,inventory.getStock(3),"There is an issue with your removeStock or your id is not correct");
        assertEquals(123,inventory.getStock(6),"There is an issue with your removeStock or your id is not correct");
    }

}