//Andre Hazim 101141843
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
    private String name;    //The name of the product
    private int id;         //The id of the product
    private double price;   //The price of the product

    /**
     * Creates a new product with the supplied attributes.
     * The product id and price is by default 0.
     */
    public Product() {
        this("", 0, 0.0);
    }

    public Product(String name, int id, double price){
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /**
     * Get the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Get the id of the product
     */
    public int getId() {
        return id;
    }
    /**
     * Get the price of the product
     */
    public double getPrice() {
        return price;
    }
}
