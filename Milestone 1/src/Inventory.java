//Andre Hazim 101141843
/**
 * This inventory class creates an inventory for the user to add products and/or
 * remove products. As well as get information about of products
 * @author Andre Hazim
 *
 * @version 1.0
 *
 * @since 2021-02-06
 *
 */

import java.util.ArrayList;
public class Inventory {
    private ArrayList<Integer> quantity;
    private ArrayList<Product> products;

    /**
     * Constructor for Inventory Class
     * initialize both arraylists for quantities and products
     */
    public Inventory(){
        quantity = new ArrayList<Integer>();
        products = new ArrayList<Product>();
    }

    /**
     * Gets stock on product given product id
     * @param productId
     * @return returns the stock
     */
    public int getStock(int productId){
        int stock = 0;
        if(products.isEmpty() == false) {
            int i = 0;
            while(products.get(i).getId() != productId && i<products.size()){
                i++;
            }
            stock = quantity.get(i);
        }
        return stock;

    }

    /**
     * Adds a stock to a product given a name and a stock amount
     * @param product
     * @param stock
     */
    public void addStock(Product product, int stock){

        if(products.isEmpty()){

            products.add(product);
            quantity.add(stock);
        }
        else{
            int i = 0;
            //System.out.println("product list size " + products.size());
            if(i>=products.size()) {

            }
            else{
                while (products.get(i).getId() != product.getId() && i<products.size()-1) {
                    i++;

                }
            }

            if(products.get(i).getId() == product.getId()){
                int tempStock = quantity.get(i);
                tempStock += stock;
                quantity.set(i, tempStock);
            }
            else{
                products.add(product);
                quantity.add(stock);
            }

        }
    }

    /**
     *  Removes stock based on product ID
     * @param productID
     * @param stockRemove
     */
    public void removeStock(int productID, int stockRemove){
        //This method removes a certain amount of stock from a product
        int i = 0;
        while(products.get(i).getId() != productID && i<products.size()-1){
            i++;
        }
        int tempStock = quantity.get(i);
        tempStock -= stockRemove;
        if(tempStock< 0){
            tempStock = 0;
        }
        quantity.set(i, tempStock);
    }

    /**
     *
     * @param productID
     * @return Returns product name
     */
    public String getProductName(int productID){
        int i = 0;
        while(products.get(i).getId() != productID && i<products.size()-1){
            i++;
        }
        String productName = products.get(i).getName();
        return productName;
    }

    /**
     *
     * @param productID
     * @return Returns product price
     */
    public double getPrice(int productID){
        int i = 0;
        while(products.get(i).getId() != productID && i<products.size()-1){
            i++;
        }
        double productPrice = products.get(i).getPrice();
        return productPrice;
    }
}
