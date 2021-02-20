import java.util.ArrayList;

public class ShoppingCart {
    private Inventory inventory;
    public ArrayList<Integer> quantity; // The quantity of stock in the cart
    public ArrayList<Product> products; // The products in the cart

    public ShoppingCart(Inventory inventory){
        this.inventory = inventory;
        quantity = new ArrayList<Integer>();
        products = new ArrayList<Product>();
    }
    public boolean addProducts(int id, int stock){
        boolean isAdded = false;
        Product product = inventory.getProduct(id);
        if(inventory.getStock(id) != 0){
            isAdded = true;
            if(products.isEmpty()){

                products.add(product);
                quantity.add(stock);
            }
            else{
                int i = 0;
                while (products.get(i).getId() != product.getId() && i<products.size()-1) {
                    i++;
                }

                if(products.get(i).getId() == product.getId()){
                    int tempStock = quantity.get(i);
                    tempStock += stock;
                    quantity.set(i, tempStock);
                }
                else{
                    // If product does not exist then add it
                    products.add(product);
                    quantity.add(stock);
                }
            }
            inventory.removeStock(id,stock);
        }

        return isAdded;
    }

    public boolean removeProducts(int id, int stock){
        boolean isInStock = false;
        int i = 0;
        while(products.get(i).getId() != id && i<products.size()-1){
            i++;
        }
        if(products.get(i).getId() == id){
            // Will return true if the product exists and its stock can be successfully removed
            if (products.get(i).getId() == id) {
                isInStock = true;
            }
            int tempStock = quantity.get(i);
            tempStock -= stock;
            if (tempStock < 0) {
                tempStock = 0;
            }
            quantity.set(i, tempStock);
            inventory.addStock(inventory.getProduct(id),stock);
        }

        return isInStock;
    }
}
