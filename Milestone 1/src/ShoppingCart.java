import java.util.ArrayList;

public class ShoppingCart extends Inventory{
    private ArrayList<Product>cart;
    private ArrayList<Integer>itemsInCart;
    private int cartID;
    private Inventory inventory;

    public ShoppingCart(){
        this(0,null);
    }
    public ShoppingCart(int cartID, Inventory inventory){
        this.cart = new ArrayList<Product>();
        this.itemsInCart = new ArrayList<Integer>();
        this.cartID =cartID;
        this.inventory = inventory;
    }


    public void addToCart(int productID, int amountOfProduct){
        this.cart.add(super.getProduct(productID));
        this.itemsInCart.add(amountOfProduct);
        inventory.removeStock(productID, amountOfProduct);
    }
    public void removeFromCart(int productID, int amountOfProduct){
        int i = 0;
        while(cart.get(i).getId() != productID && i<cart.size()-1){
            i++;
        }
        if(itemsInCart.get(i)-amountOfProduct <= 0){
            cart.remove(i);
            itemsInCart.remove(i);
        }
        else{
            int tempStock = itemsInCart.get(i);
            tempStock -= amountOfProduct;
            itemsInCart.set(i, tempStock);
        }
        inventory.addStock(getProduct(productID) ,amountOfProduct);
    }
}
