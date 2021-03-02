// Written by: Shuvaethy Neill 101143478
// Partner: Andre Hazim 101141843

import java.util.ArrayList;

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
    private ShoppingCart shoppingCart;
    private int numCarts = 0;

    public StoreManager(){
        managerInventory = new Inventory();
        shoppingCart = new ShoppingCart();
    }

    /**
     * If an inventory already exists
     * @param inv
     */
    public StoreManager(Inventory inv){
        managerInventory = inv;
    }

    public Inventory getStoreInventory(){ return managerInventory;}

    public int generateCartID(){
        numCarts += 1;
        return numCarts;
    }

    //should this check if it has already been assigned an id?
    public void assignID(){
        shoppingCart.setCartID(generateCartID());
    }


    /**
     * Check available stock of a product given its object
     * @param product
     * @return value of stock available for that specific product
     */
    public int checkInventoryStock(Product product){
        if (product != null){
            return managerInventory.getStock(product.getId());
        }
        return -1;
    }

    public double orderTransaction(ArrayList<Product> items, ArrayList<Integer> quantities){
        double total = 0;

        // Check to see if there is enough stock for each product being purchased
        for(Product item: items) {
            for (Integer quantity : quantities) {
                if (quantity > managerInventory.getStock(item.getId())) {
                    System.out.println("Sorry there is not enough stock for a product you were looking to purchase!");
                    return -1;
                }
            }
        }

        System.out.print("You have the following in your cart: " + items.toString());
        for(Product item: items) {
            System.out.println(item + (" ("));
            for (Integer quantity : quantities) {
                System.out.println("quantity: " + quantity + ") ");
                total += (quantity * managerInventory.getPrice(item.getId()));
                managerInventory.removeStock(item.getId(), quantity);
            }
        }
        System.out.print("Your total is: ");
        return total;
    }

}
