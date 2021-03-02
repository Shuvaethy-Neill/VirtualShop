// Written by: Andre Hazim 101141843
// Partner: Shuvaethy Neill 101143478

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
    private ArrayList<Integer> quantity; // The quantity of stock available for the products in store
    private ArrayList<Product> products; // The products the store sells

    /**
     * Constructor for Inventory Class
     * Initialize both arraylists for quantities and products
     * Creates default products
     */
    public Inventory(){

        Product motherBoard = new Product("Motherboard", 1, 100.0);
        Product CPU = new Product("CPU", 2, 150.99);
        Product RAM = new Product("RAM", 3, 89.99);
        Product hardDrive = new Product("Hard Drive", 4, 67.99);
        Product powerSupply = new Product("Power Supply", 5, 129.99);
        quantity = new ArrayList<Integer>();
        products = new ArrayList<Product>();
        addStock(motherBoard, 50);
        addStock(CPU,50);
        addStock(RAM, 50);
        addStock(hardDrive, 50);
        addStock(powerSupply, 50);
    }

    /**
     * Gets stock on product given product id
     * @param productId
     * @return the stock available for a product
     */

    public int getStock(int productId) {
        int stock = 0;
        if (products.isEmpty() == false) {
            int i = 0;
            while (products.get(i).getId() != productId && i < products.size()-1) {
                i++;
            }
            stock = quantity.get(i);

            // If product does not exist return -1
            if (products.get(i).getId() != productId) {
                stock = -1;
            }
        }
        return stock;
    }

    /**
     * Adds a stock to a product given a name and a stock amount
     * @param product
     * @param stock
     */
    public void addStock(Product product, int stock){

        //If the arraylist is empty add the product as a new product and add stock
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
    }

    /**
     *  Removes specific amount of a product's stock based on product ID
     * @param productID
     * @param stockRemove
     * @return boolean value true for successful remove or false if product does not exist
     */
    public boolean removeStock(int productID, int stockRemove){
        boolean isInStock = false;
        int i = 0;
        while(products.get(i).getId() != productID && i<products.size()-1){
            i++;
        }
        if(products.get(i).getId() == productID){
            // Will return true if the product exists and its stock can be successfully removed
            if (products.get(i).getId() == productID) {
                isInStock = true;
            }
            int tempStock = quantity.get(i);
            tempStock -= stockRemove;
            if (tempStock < 0) {
                tempStock = 0;
            }
            quantity.set(i, tempStock);
        }
        return isInStock;
    }

    /**
     *
     * @param productID
     * @return name of a product given a product id
     */
    public String getProductName(int productID){
        int i = 0;
        while(products.get(i).getId() != productID && i<products.size()-1){
            i++;
        }
        String productName = products.get(i).getName();

        // If the product does not exist return null
        if(products.get(i).getId() != productID){
            productName = null;
        }

        return productName;
    }

    /**
     *
     * @param productID
     * @return price of a product given a product id
     */
    public double getPrice(int productID){
        int i = 0;
        while(products.get(i).getId() != productID && i<products.size()-1){
            i++;
        }
        double productPrice = products.get(i).getPrice();

        // If the product does not exist return -1
        if(products.get(i).getId() != productID){
            return -1;
        }
        return productPrice;
    }

    /**
     *
     * @param productID
     * @return product given ID
     */
    public Product getProduct(int productID){
        int i = 0;
        while(products.get(i).getId() != productID && i<products.size()-1){
            i++;
        }
        Product product = products.get(i);

        // If the product does not exist return null
        if(products.get(i).getId() != productID){
            product = null;
        }
        return product;
    }
    public ArrayList<Product> getProductList(){
        return this.products;
    }
}
