import java.util.Comparator;

class NameComparator implements Comparator<Product> {

    public int compare(Product a, Product b){
        return a.getName().compareTo(b.getName());
    }
}