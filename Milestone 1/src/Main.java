import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Inventory managerInventory = new Inventory();
        StoreManager testStore = new StoreManager(managerInventory);
        Product p1 = new Product("ball", 101, 3.40);
        Product p2 = new Product("triangle", 73, 3.70);
        Product p3 = new Product("Computer", 32, 100);

        managerInventory.addStock(p1, 3);
        managerInventory.addStock(p2, 3);

        managerInventory.addStock(p3,1);
        managerInventory.addStock(p2, 2);
        managerInventory.addStock(p3,4);
        int ballStock = managerInventory.getStock(101);
        int triangleStock = managerInventory.getStock(73);
        int computerStock = managerInventory.getStock(32);

        String productName = managerInventory.getProductName(73);

        System.out.println("ball stock: "+ ballStock +", Triangle Stock: "+ triangleStock +
                ", Computer Stock: " + computerStock);
        System.out.println(productName);

        System.out.println(testStore.checkInventoryStock(p1));

        managerInventory.getStock(101);
        managerInventory.getStock(73);
        managerInventory.getStock(32);

        // Create an order as a 2D array
        int[][] order1 = {{p1.getId(), 3}, {p2.getId(), 1}, {p3.getId(), 2}};

        System.out.println("Order: " + Arrays.deepToString(order1));

        System.out.println("Your total comes to: " + testStore.orderTransaction(order1));

        managerInventory.getPrice(101);
        managerInventory.getPrice(73);
        managerInventory.getPrice(32);

        // Create an order to test the out of stock case
        int[][] order2 = {{p1.getId(), 1}, {p2.getId(), 1}, {p3.getId(), 2}};

        System.out.println("Order: " + Arrays.deepToString(order2));

        System.out.println("Your total comes to: " + testStore.orderTransaction(order2));

        System.out.println(testStore.checkInventoryStock(p1));

        System.out.println("Testing add stock method to see if it will add new product");
        Product p4 = new Product("ball", 105, 3.40);
        managerInventory.addStock(p4,3);
        System.out.println(testStore.checkInventoryStock(p4));

        managerInventory.addStock(p1,3);
        System.out.println("Testing remove stock method to see if it will maintain stock at 0");
        System.out.println(managerInventory.removeStock(101, 2));
        System.out.println("Stock: " + testStore.checkInventoryStock(p1));
        System.out.println(managerInventory.removeStock(101, 2));
        System.out.println("Stock: " + testStore.checkInventoryStock(p1));
        System.out.println(managerInventory.removeStock(101, 2));
        System.out.println("Stock: " + testStore.checkInventoryStock(p1));

        System.out.println("Testing inventory methods using a product id that does not exist");
        System.out.println(managerInventory.removeStock(102, 1));
        System.out.println(managerInventory.getProduct(102));
        System.out.println(managerInventory.getProductName(102));
        System.out.println(managerInventory.getPrice(102));
        System.out.println(managerInventory.getStock(102));
    }
}
