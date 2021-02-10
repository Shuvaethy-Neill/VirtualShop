// Written by: Andre Hazim 101141843
// Partner: Shuvaethy Neill 101143478

/**
 * The Product class is a template for products that will be sold in the store
 * @author Andre Hazim
 *
 * @version 1.0
 *
 * @since 2021-02-06
 *
 */

public class Product {
    private final String NAME;    // The name of the product
    private final int ID;         // The id of the product
    private final double PRICE;   // The price of the product

    public Product() {
        this("", 0, 0.0);
    }

    /**
     * Creates a new product with the supplied attributes.
     * The product id and price is by default 0.
     * @param name
     * @param id
     * @param price
     */
    public Product(String name, int id, double price){
        this.NAME = name;
        this.ID = id;
        this.PRICE = price;
    }

    /**
     * Get the name of the product
     * @return name of the product
     */
    public String getName() {
        return NAME;
    }

    /**
     * Get the id of the product
     * @return product id
     */
    public int getId() {
        return ID;
    }
    /**
     * Get the price of the product
     * @return price of product
     */
    public double getPrice() {
        return PRICE;
    }
}
