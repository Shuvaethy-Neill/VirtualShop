package store;

import java.util.ArrayList;

// Written by: Andre Hazim 101141843
// Edited by: Shuvaethy Neill 101143478

/**
 * This store.ShoppingCart class models a user's shopping cart
 *
 * @author Andre Hazim
 *
 * @version 1.0
 *
 * @since 2021-03-07
 *
 */

public class ShoppingCart extends Inventory{
    private ArrayList<Product>cart;
    private ArrayList<Integer>itemsInCart;
    private int cartID;

    /**
     * The default constructor for Shopping cart
     */
    public ShoppingCart(){
        this(0);
    }

    /**
     * A constructor for shopping cart
     * @param cartID int the id of the cart
     */
    public ShoppingCart(int cartID){
        this.cart = new ArrayList<Product>();
        this.itemsInCart = new ArrayList<Integer>();
        this.cartID = cartID;
    }

    /**
     * adds items to the cart
     * @param productID the id of the product
     * @param amountOfProduct the ammount of product to be added
     */
    public void addToCart(int productID, int amountOfProduct){
        if (cart.isEmpty()){
            this.cart.add(super.getProduct(productID));
            this.itemsInCart.add(amountOfProduct);
        }
        else{
            int i = 0;
            while (cart.get(i).getId() != productID && i<cart.size()-1) {
                i++;
            }
            if (cart.get(i).getId() == productID){
                int tempStock = itemsInCart.get(i);
                tempStock += amountOfProduct;
                itemsInCart.set(i, tempStock);
            }
            else{
                this.cart.add(super.getProduct(productID));
                this.itemsInCart.add(amountOfProduct);
            }

        }
    }

    /**
     * removes items from cart
     * @param productID the id of the product
     * @param amountOfProduct the amount of product to be removed
     * @return returns the amount of removed product
     */
    public int removeFromCart(int productID, int amountOfProduct){
        int i = 0;
        while(cart.get(i).getId() != productID && i < cart.size()-1){
            i++;
        }
        int removedProduct = amountOfProduct;

        // If quantity needed to be removed results in a quantity of 0 or less
        if(itemsInCart.get(i)-amountOfProduct <= 0){
            removedProduct = itemsInCart.get(i);
            // Remove product and its quantity from cart
            cart.remove(i);
            itemsInCart.remove(i);
        }
        else{
            // Remove specified quantity regularly and update cart quantity arraylist
            int tempStock = itemsInCart.get(i);
            tempStock -= amountOfProduct;
            itemsInCart.set(i, tempStock);
        }
        return removedProduct;
    }

    /**
     * The accessor of the cart attribute
     * @return ArrayList<store.Product> the cart of the shopping cart will be returned
     */
    public ArrayList<Product> getCart(){
        return this.cart;
    }
    /**
     * The accessor of the itemsInCart attribute
     * @return ArrayList<Integer> the arraylist of the num items in shopping cart will be returned
     */
    public ArrayList<Integer> getItemsInCart(){
        return this.itemsInCart;
    }

}
