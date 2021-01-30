//Milestone 1
//Shuvaethy Neill 101143478

public class StoreManager {
    public Inventory managerInventory;

    public StoreManager(){
        managerInventory = new Inventory();
    }

    public int getInventoryStock(int productID){
        return managerInventory.getStock(productID);
    }

    public void removeInventoryStock(int productID, int stockRemove) {
        managerInventory.removeStock(productID, stockRemove);
    }


    public double transaction(int [][] order){
        double total = 0;
        int rows = order.length;    //rows represents each product and quantity [ProductID1, quantity
        for(int i = 0; i < rows; i++ ){     //in each row

            int j = order[i][0];    //j is the productID and k is the quantity
            int k = order[i][1];

            int stock = getInventoryStock(j);

            if(k > getInventoryStock(j)) {  //if quantity is greater than stock indicate error by returning -1
                System.out.println("Sorry one of the products you were looking to purchase is out of stock");
                return -1;
            }

            else{   //if (k <= getInventoryStock(j)){
                 total += (k * managerInventory.getPrice(j));   //add everything to get total
                 removeInventoryStock(j, k);    //subtract the quantity purchased from the product's stock after successful transaction
            }
        }
        return Math.round(total); //returns it rounded to two decimals
    }
}
