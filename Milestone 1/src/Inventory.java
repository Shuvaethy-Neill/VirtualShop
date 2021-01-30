//Andre Hazim 101141843
import java.util.ArrayList;
public class Inventory {
    private ArrayList<Integer> quantity;
    private ArrayList<Product> products;

    public Inventory(){
        quantity = new ArrayList<Integer>();
        products = new ArrayList<Product>();
    }

    public int getStock(int iProductId){
        int stock = 0;
        if(products.isEmpty() == false) {
            int i = 0;
            while(products.get(i).getId() != iProductId && i<products.size()){
                i++;
            }
            stock = quantity.get(i);
        }
        return stock;

    }

    public void addStock(Product iProduct, int iStock){

        if(products.isEmpty()){

            products.add(iProduct);
            quantity.add(iStock);
        }
        else{
            int i = 0;
            //System.out.println("product list size " + products.size());
            if(i>=products.size()) {

            }
            else{
                while (products.get(i).getId() != iProduct.getId() && i<products.size()-1) {
                    //System.out.println("index is "+ i);
                    i++;
                    //System.out.println("index is "+ i);

                }
            }

            if(products.get(i).getId() == iProduct.getId()){
                //System.out.println("product i am adding stock to is " + products.get(i).getName());
                int tempStock = quantity.get(i);
                tempStock += iStock;
                quantity.set(i, tempStock);
            }
            else{
                //System.out.println("The product is not in the list " + iProduct.getName());
                products.add(iProduct);
                quantity.add(iStock);
            }

        }
    }
    public void removeStock(int productID, int stockRemove){
        //This method removes a certain amount of stock from a product
        int i = 0;
        while(products.get(i).getId() != productID && i<products.size()-1){
            i++;
        }
        int tempStock = quantity.get(i);
        tempStock -= stockRemove;
        if(tempStock< 0){
            tempStock = 0;
        }
        quantity.set(i, tempStock);
    }
    public void productInfo(int productID){
        int i = 0;
        while(products.get(i).getId() != productID && i<products.size()-1){
            i++;
        }
        String productName = products.get(i).getName();
        int productStock = quantity.get(i);
        double productPrice = products.get(i).getPrice();
        System.out.println("Name: "+ productName +
                ", Stock: " + Integer.toString(productStock)+
                ", Price: " + Double.toString(productPrice));
    }
    public String getProductName(int productID){
        int i = 0;
        while(products.get(i).getId() != productID && i<products.size()-1){
            i++;
        }
        String productName = products.get(i).getName();
        return productName;
    }
    public double getPrice(int productID){
        int i = 0;
        while(products.get(i).getId() != productID && i<products.size()-1){
            i++;
        }
        double productPrice = products.get(i).getPrice();
        return productPrice;
    }
}
