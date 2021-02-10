public class InventoryClient {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        System.out.println(inventory.getProductName(2) + ": " +inventory.getStock(2));

    }
}
