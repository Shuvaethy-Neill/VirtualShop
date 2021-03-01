public class ShoppingCartClient {

    public static void main(String[] args) {
        Inventory i1 = new Inventory();
        ShoppingCart c1 = new ShoppingCart(0,i1);

        c1.addToCart(1, 4);

        System.out.println(i1.getStock(1));
        System.out.println(c1.getProductName(1));
        System.out.println(c1.getStock(1));

        c1.removeFromCart(1,4);
        System.out.println(i1.getStock(1));

    }
}
