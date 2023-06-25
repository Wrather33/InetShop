import java.util.Comparator;

class PriceComparator implements Comparator<Product> {

    public int compare(Product a, Product b){
        return b.getPrice() - a.getPrice();
    }
}