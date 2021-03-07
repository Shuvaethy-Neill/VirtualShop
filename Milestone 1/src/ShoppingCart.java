import java.util.ArrayList;
import java.util.Locale;

public class ShoppingCart {
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

    public ArrayList<Product> getCart(){
        return this.cart;
    }
    public ArrayList<Integer> getItemsInCart(){
        return this.itemsInCart;
    }

}
