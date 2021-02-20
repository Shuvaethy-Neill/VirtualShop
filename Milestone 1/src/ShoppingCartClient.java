public class ShoppingCartClient {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        ShoppingCart shoppingCart = new ShoppingCart(inventory);
        shoppingCart.addProducts(2,8);
        System.out.println("shopping cart");
        System.out.println(shoppingCart.products.get(0).getName());
        System.out.println(shoppingCart.quantity);
        System.out.println("invnetory:");
        System.out.println(inventory.getStock(2));
    }
}
