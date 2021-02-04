// Milestone 1
// Shuvaethy Neill 101143478

public class StoreManager {
    private Inventory managerInventory;

    public StoreManager(){
        managerInventory = new Inventory();
    }

    // if an inventory already exists
    public StoreManager(Inventory inv){
        managerInventory = inv;
    }

    // get product's available stock
    public int getInventoryStock(int productID){
        return managerInventory.getStock(productID);
    }

    // remove a certain amount from the product's stock
    public void removeInventoryStock(int productID, int stockRemove) {
        managerInventory.removeStock(productID, stockRemove);
    }

    // takes an order in the form of a 2D array ([ProductID1, quantity], [..,..]...) and completes the transaction
    public double transaction(int [][] order){
        double total = 0;

        // rows represents each product and quantity [ProductID1, quantity]
        int rows = order.length;

        // check to see if there is enough stock for each product being purchased
        for(int i = 0; i < rows; i++ ) {
            int productID = order[i][0];
            int quantity = order[i][1];

            // insufficient stock for quantity being purchased (transaction fails)
            if (quantity > getInventoryStock(productID)) {
                System.out.println("Sorry there is not enough stock for a product you were looking to purchase!");
                return -1;
            }
        }

        // totals the transaction cost and removes the corresponding quantity from each product's stock
        for(int i = 0; i < rows; i++ ){
            int productID = order[i][0];
            int quantity = order[i][1];
            total += (quantity * managerInventory.getPrice(productID));
            removeInventoryStock(productID, quantity);

        }
        return Math.round(total);
    }
}
