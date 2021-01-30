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

    public double transaction(int [][] receipt){
        double total = 0;
        int rows = receipt.length;    //rows represents each product and quantity [ProductID1, quantity]
        System.out.println(receipt.length); //prints 3
        for(int i = 0; i < rows; i++ ){     //in each row
            System.out.println(receipt[i].length);    // should be 2 since you're given productID and quantity in each row

            int j = receipt[i][0];    //j is the productID and k is the quantity
            int k = receipt[i][1];
            System.out.println(j);
            System.out.println(k);

            int stock = getInventoryStock(j);
            System.out.println(stock);

            if(k > getInventoryStock(j)) {  //if quantity is greater than stock indicate error by returning -1
                return -1;
            }

            else{   //if (k <= getInventoryStock(j)){
                 total += (k * managerInventory.getPrice(j));   //add everything to get total
                 removeInventoryStock(j, k);    //subtract the quantity purchased from the product's stock after successful transaction
            }
        }
        return Math.round(total * 100.0)/100.0; //returns it rounded to two decimals
    }
}
