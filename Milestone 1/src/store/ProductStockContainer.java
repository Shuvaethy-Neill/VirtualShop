package store;

public interface ProductStockContainer {
    int getProductQuantity(Product product);
    void addProductQuantity(Product product,int amountToAdd);
    int removeProductQuantity(Product product, int amountToRemove);

    int getNumOfProducts();
}
