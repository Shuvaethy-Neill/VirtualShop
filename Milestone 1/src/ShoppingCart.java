// Written by: Andre Hazim 101141843
// Partner: Shuvaethy Neill 101143478
import java.util.ArrayList;


public class ShoppingCart extends Inventory{
    private ArrayList<Product>cart;
    private ArrayList<Integer>itemsInCart;
    private int cartID;
    private Inventory inventory;

    /**
     * The default constructor
     */
    public ShoppingCart(){
        this(0,null);
    }

    /**
     * A constructor for the Shopping cart
     * @param cartID: int A unique id for each cart
     * @param inventory: Inventory the inventory of a store
     */
    public ShoppingCart(int cartID, Inventory inventory){
        this.cart = new ArrayList<Product>();
        this.itemsInCart = new ArrayList<Integer>();
        this.cartID =cartID;
        this.inventory = inventory;
    }

    /**
     * adding items to the shopping cart
     * @param productID int the id of the product
     * @param amountOfProduct int the amount of products to add
     */
    public void addToCart(int productID, int amountOfProduct){
        if(cart.isEmpty()){
            this.cart.add(super.getProduct(productID));
            this.itemsInCart.add(amountOfProduct);
        }
        else {
            int i = 0;
            while (cart.get(i).getId() != productID && i < cart.size() - 1) {
                i++;
            }
            if (cart.get(i).getId() == productID) {
                int temp = this.itemsInCart.get(i);
                temp += amountOfProduct;
                this.itemsInCart.set(i, temp);

            } else {
                this.cart.add(super.getProduct(productID));
                this.itemsInCart.add(amountOfProduct);
            }
        }
        inventory.removeStock(productID, amountOfProduct);
    }

    /**
     * removes items from carts
     * @param productID int the id of the product
     * @param amountOfProduct int amount of product to remove
     */
    public void removeFromCart(int productID, int amountOfProduct){
        int i = 0;
        while(cart.get(i).getId() != productID && i<cart.size()-1){
            i++;
        }
        int removedProduct = amountOfProduct;
        if(itemsInCart.get(i)-amountOfProduct <= 0){
            removedProduct = itemsInCart.get(i);
            cart.remove(i);
            itemsInCart.remove(i);
        }

        else{
            int tempStock = itemsInCart.get(i);
            tempStock -= amountOfProduct;
            itemsInCart.set(i, tempStock);
        }
        inventory.addStock(getProduct(productID) ,removedProduct);
    }

    /**
     * accessor for cartID
     * @return int cartID
     */
    public int getCartID(){
        return this.cartID;
    }

    /**
     * accessor for product name
     * @param productID int id for the product
     * @return String product name
     */
    public String getProductName(int productID){
        int i = 0;
        while(cart.get(i).getId() != productID && i<cart.size()-1){
            i++;
        }
        String productName = cart.get(i).getName();

        // If the product does not exist return null
        if(cart.get(i).getId() != productID){
            productName = null;
        }

        return productName;
    }

    /**
     * gets the stock for a certain product in stock
     * @param productID
     * @return
     */
    public int getStock(int productID) {
        int stock = 0;
        if (cart.isEmpty() == false) {
            int i = 0;
            while (cart.get(i).getId() != productID && i < cart.size()-1) {
                i++;
            }
            stock = itemsInCart.get(i);

            // If product does not exist return -1
            if (cart.get(i).getId() != productID) {
                stock = -1;
            }
        }
        return stock;
    }
    public ArrayList<Product> getCart(){
        return this.cart;
    }
    public ArrayList<Integer> getItemsInCart(){
        return this.itemsInCart;
    }

}
