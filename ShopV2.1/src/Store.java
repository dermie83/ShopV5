import java.util.ArrayList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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

    public ArrayList<Product> getProducts()
    {
        return products;
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
        if (products.size() == 0){
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

    @SuppressWarnings("unchecked")
    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());

        // ------------------ PREVENT SECURITY WARNINGS-----------------------------
        // The Product class is what we are reading in.
        // Modify to include others if needed by modifying the next line,
        // add additional classes inside the braces, comma separated

        Class<?>[] classes = new Class[] { Product.class };

        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        // -------------------------------------------------------------------------

        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("products.xml"));
        products = (ArrayList<Product>) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());

        // ------------------ PREVENT SECURITY WARNINGS-----------------------------
        // The Product class is what we are reading in.
        // Modify to include others if needed by modifying the next line,
        // add additional classes inside the braces, comma separated

        Class<?>[] classes = new Class[] { Product.class };

        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        // -------------------------------------------------------------------------

        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("products.xml"));
        out.writeObject(products);
        out.close();
    }



}
