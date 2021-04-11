package store;

// Written by: Andre Hazim 101141843
// Edited by: Shuvaethy Neill 101143478

/**
 * ProductStockContainer Interface with the following methods definitions for store functionality
 * to be specifically implemented in classes in which they are used
 *
 * @author Andre Hazim
 * @version 1.0
 * @since 2021-04-04
 */
public interface ProductStockContainer {

    /**
     * This method will be used to get the stock of product given product
     * @param product Product: the product you want to the stock of
     * @return int, the stock available for the product
     */
    int getProductQuantity(Product product);

    /**
     * This method will be used to add stock to a product given a product and a stock amount
     * @param product Product, the specific product
     * @param amountToAdd int, the specific amount of stock being added
     */
    void addProductQuantity(Product product,int amountToAdd);

    /**
     * This method will be used to remove a specific amount of a product's stock given a product
     * @param product Product, the specific product
     * @param amountToRemove int, the amount of stock being removed
     * @return int, value of stock removed
     */
    int removeProductQuantity(Product product, int amountToRemove);

    /**
     * This method will be used to get the number of products in the inventory
     * @return int, number of products
     */
    int getNumOfProducts();
}
