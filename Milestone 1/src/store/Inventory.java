package store;
// Written by: Andre Hazim 101141843
// Partner: Shuvaethy Neill 101143478

/**
 * This Inventory class creates an inventory for the user to add products and/or
 * remove products. As well as get information about of products
 * @author Andre Hazim
 *
 * @version 4.0
 *
 * @since 2021-04-08
 *
 */

import java.util.ArrayList;
public class Inventory implements ProductStockContainer{
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
        addProductQuantity(motherBoard, 50);
        addProductQuantity(CPU,50);
        addProductQuantity(RAM, 50);
        addProductQuantity(hardDrive, 50);
        addProductQuantity(powerSupply, 50);
    }

    /**
     * Gets stock on product given product
     * @param product Product: the product you want to the stock of
     * @return int, the stock available for the product
     */
    @Override
    public int getProductQuantity(Product product) {
        int stock = 0;
        if (products.isEmpty() == false) {
            int i = 0;
            while (products.get(i).getId() != product.getId() && i < products.size()-1) {
                i++;
            }
            stock = quantity.get(i);

            // If product does not exist return -1
            if (products.get(i).getId() != product.getId()) {
                stock = -1;
            }
        }
        return stock;
    }

    /**
     * Adds a stock to a product given a product and a stock amount
     * @param product Product, the specific product
     * @param stock int, the specific amount of stock being added
     */
    @Override
    public void addProductQuantity(Product product, int stock){

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
     *  Removes specific amount of a product's stock given a product
     * @param product Product, the specific product
     * @param stockRemove int, the amount of stock being removed
     * @return int, value of stock removed
     */
    public int removeProductQuantity(Product product, int stockRemove){

        int i = 0;
        while(products.get(i).getId() != product.getId() && i<products.size()-1){
            i++;
        }
        int removedProduct = stockRemove;
        if(products.get(i).getId() == product.getId()){
            // Will return true if the product exists and its stock can be successfully removed

            int tempStock = quantity.get(i);
            tempStock -= stockRemove;
            if (tempStock < 0) {
                tempStock = 0;
            }
            quantity.set(i, tempStock);
        }
        return stockRemove;
    }

    /**
     * This method gives access to the name of a product
     * @param productID int, the product identifier id
     * @return String, name of a product given a product id
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
     * This method gives access to the price of a specific product
     * @param productID int, the product's id
     * @return double, price of a product given a product id
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
     * This method gives access to a product
     * @param productID int, the product's id
     * @return Product, product given it's id
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

    /**
     *  This method gives access to the products that are existent in the Inventory
     * @return ArrayList<Product>, list of products that exist in inventory
     */
    public ArrayList<Product> getProductList(){
        return this.products;
    }

    /**
     * Gets the number of products in the inventory
     * @return int, number of products
     */
    @Override
    public int getNumOfProducts() {
        return this.products.size();
    }
}
