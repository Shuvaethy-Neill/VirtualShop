//Andre Hazim 101141843
//This class stores information about the items being sold by the store

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
