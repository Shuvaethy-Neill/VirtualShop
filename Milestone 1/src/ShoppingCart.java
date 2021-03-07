import java.util.ArrayList;
import java.util.Locale;

public class ShoppingCart extends Inventory{
    private ArrayList<Product>cart;
    private ArrayList<Integer>itemsInCart;
    private int cartID;

    public ShoppingCart(){
        this(0);
    }
    public ShoppingCart(int cartID){
        this.cart = new ArrayList<Product>();
        this.itemsInCart = new ArrayList<Integer>();
        this.cartID = cartID;
    }


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
