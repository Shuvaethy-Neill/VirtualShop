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
    public void setShoppingCart(ShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
    }
    public ShoppingCart getShoppingCart(){return shoppingCart;}

    public int generateCartID(){
        numCarts += 1;
        return numCarts;
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
        int i = 0;

        System.out.print("You have the following in your cart: ");
        for(i = 0; i < items.size() && i < quantities.size(); i ++) {
            if(i != items.size() - 1){
                System.out.print(items.get(i).getName() + " (quantity: " + quantities.get(i) + "), ");
            }

            else{
                System.out.println(items.get(i).getName() + " (quantity: " + quantities.get(i) + ") ");
            }
        }
        int j = 0;
        for(j = 0; j < items.size(); j ++){
            total += (quantities.get(j) * managerInventory.getPrice(items.get(j).getId()));
        }

        System.out.println("Your total is: " + total);

        return total;
    }

}
