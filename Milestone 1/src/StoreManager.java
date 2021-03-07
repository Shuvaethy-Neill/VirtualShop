// Written by: Shuvaethy Neill 101143478
// Partner: Andre Hazim 101141843

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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

    private int numCarts;
    private HashMap <Integer, ShoppingCart> carts;


    public StoreManager(){
        managerInventory = new Inventory();
        carts = new HashMap <Integer, ShoppingCart>();
        numCarts = -1;
    }

    /**
     * If an inventory already exists
     * @param inv
     */
    public StoreManager(Inventory inv){
        managerInventory = inv;
        carts = new HashMap<Integer, ShoppingCart>();
        numCarts = -1;
    }

    public Inventory getStoreInventory(){ return managerInventory;}
    public void setShoppingCart(ShoppingCart shoppingCart){

        this.carts.put(numCarts, shoppingCart);
    }
    public ShoppingCart getShoppingCart(int cartID){
        return carts.get(cartID);
    }

    public int generateCartID(){
        numCarts += 1;
        return numCarts;
    }

    public ArrayList<Product> getSMCart(int cartId){
        ShoppingCart shoppingCart = carts.get(cartId);
        return shoppingCart.getCart();
    }

    public ArrayList<Integer> getSMItemsInCart(int cartId){
        ShoppingCart shoppingCart = carts.get(cartId);
        return shoppingCart.getItemsInCart();
    }




    public void addToCart(int productID, int amountOfProduct, int cartId){
        ShoppingCart shoppingCart = carts.get(cartId);
        if(shoppingCart.getCart().isEmpty()){
            shoppingCart.getCart().add(managerInventory.getProduct(productID));
            shoppingCart.getItemsInCart().add(amountOfProduct);
            managerInventory.removeStock(productID, amountOfProduct);
        }
        else {
            int i = 0;
            while (shoppingCart.getCart().get(i).getId() != productID && i < shoppingCart.getCart().size() - 1) {
                i++;
            }
            if (shoppingCart.getCart().get(i).getId() == productID) {
                int temp = shoppingCart.getItemsInCart().get(i);
                temp += amountOfProduct;
                shoppingCart.getItemsInCart().set(i, temp);

            } else {
                shoppingCart.getCart().add(managerInventory.getProduct(productID));
                shoppingCart.getItemsInCart().add(amountOfProduct);
            }
            carts.put(cartId, shoppingCart);
            managerInventory.removeStock(productID, amountOfProduct);
        }
    }
    public void removeFromCart(int productID, int amountOfProduct,int cartId){
        ShoppingCart shoppingCart = carts.get(cartId);
        int i = 0;
        while(shoppingCart.getCart().get(i).getId() != productID && i < shoppingCart.getCart().size()-1){
            i++;
        }
        int removedProduct = amountOfProduct;
        if(shoppingCart.getItemsInCart().get(i)-amountOfProduct <= 0){
            removedProduct = shoppingCart.getItemsInCart().get(i);
            shoppingCart.getCart().remove(i);
            shoppingCart.getItemsInCart().remove(i);
        }

        else{
            int tempStock = shoppingCart.getItemsInCart().get(i);
            tempStock -= amountOfProduct;
            shoppingCart.getItemsInCart().set(i, tempStock);
        }
        carts.put(cartId, shoppingCart);
        managerInventory.addStock(managerInventory.getProduct(productID) ,removedProduct);
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

        System.out.print("You have the following in your cart: ");
        for(int i = 0; i < items.size() && i < quantities.size(); i ++) {
            if(i != items.size() - 1){
                System.out.print(items.get(i).getName() + " (quantity: " + quantities.get(i) + "), ");
            }
            else{
                System.out.println(items.get(i).getName() + " (quantity: " + quantities.get(i) + ") ");
            }
        }

        for(int i = 0; i < items.size(); i ++){
            total += (quantities.get(i) * managerInventory.getPrice(items.get(i).getId()));
        }

        System.out.println("Your total is: " + total);
        return total;
    }

}