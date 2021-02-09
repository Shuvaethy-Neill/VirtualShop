// Written by: Shuvaethy Neill 101143478
// Partner: Andre Hazim 101141843

/**
 * This StoreManager class is the "brain" of the system and for this
 * milestone it manages a single Inventory.
 * @author Shuvaethy Neill
 *
 * @version 1.0
 *
 * @since 2021-02-06
 *
 */

public class StoreManager {
    private Inventory managerInventory;

    public StoreManager(){
        managerInventory = new Inventory();
    }

    /**
     * If an inventory already exists
     * @param inv
     */
    public StoreManager(Inventory inv){
        managerInventory = inv;
    }

    /**
     * Check available stock of a product given its object
     * @param product
     * @return value of stock available for that specific product
     */
    public int checkInventoryStock(Product product){
        return managerInventory.getStock(product.getId());
    }

    /**
     * Takes an order in the form of a 2D array ([ProductID1, quantity], [..,..]...) and completes the transaction
     * @param order
     * @return total price for order purchase
     */
    public double transaction(int [][] order){
        double total = 0;

        // Rows represents each product and quantity [ProductID1, quantity]
        int rows = order.length;

        // Check to see if there is enough stock for each product being purchased
        for(int i = 0; i < rows; i++ ) {
            int productID = order[i][0];
            int quantity = order[i][1];

            // Insufficient stock for quantity being purchased (transaction fails)
            if (quantity > managerInventory.getStock(productID)) {
                System.out.println("Sorry there is not enough stock for a product you were looking to purchase!");
                return -1;
            }
        }

        // Totals the transaction cost and removes the corresponding quantity from each product's stock
        for(int i = 0; i < rows; i++ ){
            int productID = order[i][0];
            int quantity = order[i][1];
            total += (quantity * managerInventory.getPrice(productID));
            managerInventory.removeStock(productID, quantity);

        }
        return Math.round(total);
    }
}
