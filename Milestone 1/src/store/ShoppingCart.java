package store;

import java.util.ArrayList;

// Written by: Andre Hazim 101141843
// Edited by: Shuvaethy Neill 101143478

/**
 * This ShoppingCart class models a user's shopping cart
 *
 * @author Andre Hazim
 *
 * @version 1.0
 *
 * @since 2021-03-07
 *
 */

public class ShoppingCart implements ProductStockContainer {
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
     * Adds a stock to a product given a name and a stock amount
     * @param product Product, the specific product
     * @param stock int, the specific amount of stock being added
     */
    @Override
    public void addProductQuantity(Product product, int stock){

        //If the arraylist is empty add the product as a new product and add stock
        if(cart.isEmpty()){

            cart.add(product);
            itemsInCart.add(stock);
        }
        else{
            int i = 0;
            while (cart.get(i).getId() != product.getId() && i<cart.size()-1) {
                i++;
            }

            if(cart.get(i).getId() == product.getId()){
                int tempStock = itemsInCart.get(i);
                tempStock += stock;
                itemsInCart.set(i, tempStock);
            }
            else{
                // If product does not exist then add it
                cart.add(product);
                itemsInCart.add(stock);
            }
        }
    }

    /**
     *  Removes specific amount of a product's stock based on product ID
     * @param product Product, the specific product
     * @param amountOfProduct int, the amount of stock being removed
     * @return boolean value true for successful remove or false if product does not exist
     */
    public int removeProductQuantity(Product product, int amountOfProduct){
        int i = 0;
        while(cart.get(i).getId() != product.getId() && i < cart.size()-1){
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

    /**
     * Not implemented  version of getProduct quantity
     * @param product Product, product to get the quantity of
     * @return -1 because it is not implemented
     */
    @Override
    public int getProductQuantity(Product product) {
        return -1;
    }

    /**
     * not implemented verions of getNumProducts
     * @return -1 because it is not implemented
     */
    @Override
    public int getNumOfProducts() {
        return -1;
    }
}
