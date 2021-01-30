import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Product ball = new Product("ball", 1, 3.40);
        Product triangle = new Product("triangle", 2, 3.70);
        Product computer = new Product("Computer", 3, 100);
        Inventory inventory = new Inventory();

        inventory.addStock(ball, 3);
        inventory.addStock(triangle, 3);

        inventory.addStock(computer,1);
        inventory.addStock(triangle, 2);
        inventory.addStock(computer,4);
        int ballStock = inventory.getStock(1);
        int triangleStock = inventory.getStock(2);
        int computerStock = inventory.getStock(3);
        String productName = inventory.getProductName(2);
        System.out.println("ball stock: "+ ballStock +", Triangle Stock: "+ triangleStock +
                ", Computer Stock: " + computerStock);
        System.out.println(productName);

        inventory.productInfo(1);
        inventory.productInfo(2);
        inventory.productInfo(3);

        // Create a 2D array
        int[][] receipt = new int[3][2];
        receipt[0][0] = 1;
        receipt[0][1] = 3;
        receipt[1][0] = 2;
        receipt[1][1] = 1;
        receipt[2][0] = 3;
        receipt[2][1] = 1;
        System.out.println(Arrays.deepToString(receipt));

        StoreManager purchases = new StoreManager();
        double total = purchases.transaction(receipt);
        System.out.println("Your total comes to: " + total);

        inventory.productInfo(1);
        inventory.productInfo(2);
        inventory.productInfo(3);
    }
}