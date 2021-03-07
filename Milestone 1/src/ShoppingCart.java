// Written by: Andre Hazim 101141843
// Edited by: Shuvaethy Neill 101143478

import java.util.ArrayList;
import java.util.Locale;

public class ShoppingCart {
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
     * The accessor of the cart attribute
     * @return ArrayList<Product> the cart of the shopping cart will be returned
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
