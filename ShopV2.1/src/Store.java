import java.util.ArrayList;

public class Store {

    private ArrayList<Product> products;

    public Store(){
        products = new ArrayList<Product>();
    }


//    private boolean isFull(){
//        return (total == products.length);
//    }

//    private boolean isEmpty(){
//        return (total == 0);
//    }

    public void add(Product product){

        products.add (product);
    }


    public String listProducts(){
        if (products.size() == 0){
            return "No products";
        }
        else{
            String listOfProducts = "";
            for (int i = 0; i < products.size(); i++){
                listOfProducts = listOfProducts + i + ": " + products.get(i) + "\n";
            }
            return listOfProducts;
        }
    }


    public String cheapestProduct()
    {
        if (products.size() != 0){
            Product cheapestProduct = products.get(0);
            for (int i = 0; i < products.size(); i++){
                if (products.get(i).getUnitCost() < cheapestProduct.getUnitCost() )
                    cheapestProduct = products.get(i);
            }
            return cheapestProduct.getProductName();
        }
        else
            return "No Products are in the ArrayList";
    }


    public String listCurrentProducts()
    {
        if (products.size() != 0){
            return "No Products are in our current product line";
        }
        else
        {
            String listOfProducts = "";
            for (int i = 0; i < products.size(); i++){
                if (products.get(i).isInCurrentProductLine())
                    listOfProducts = listOfProducts + i + ": " + products.get(i) + "\n";
            }
            return listOfProducts;
        }
    }

    private double toTwoDecimalPlaces(double num){
        return (int) (num *100 ) /100.0;
    }


    public double averageProductPrice()
    {
        if (products.size() != 0){
            double totalPrice = 0;
            for (int i = 0; i < products.size(); i++){
                totalPrice = totalPrice + products.get(i).getUnitCost();
            }
            return toTwoDecimalPlaces(totalPrice / products.size());
        }
        else
        {
            return 0.0;
        }

    }


    public String listProductsAboveAPrice(double price)
    {
        if (products.size() != 0){
            String str = "";
            for (int i = 0; i < products.size(); i++){
                if (products.get(i).getUnitCost() > price)
                    str = str + i + ": " + products.get(i) + "\n";
            }
            return str;
        }
        else
        {
            return "No Products are more expensive than: " + price;
        }

    }


}
