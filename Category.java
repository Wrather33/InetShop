import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Category{
    private String name;
    private ArrayList<Product> products = new ArrayList<Product>();
    public Category(String name, Product... products){
        this.name = name;
        for (int i = 0; i < products.length; i++) {
            this.products.add(products[i]);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    public void sortProducts(Comparator<Product> comparator){
        Collections.sort(this.getProducts(), comparator);
    }
    public void reverseProducts(Comparator<Product> comparator){
        Collections.sort(this.getProducts(), Collections.reverseOrder(comparator));
    }


    public String productsToString(){
        return products.stream().map(Product::toString)
                .collect(Collectors.joining(""));
    }


    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    public void addProduct(Product product){
        this.products.add(product);
    }
    public void deleteProduct(Product product){
        this.products.remove(product);
    }

}
