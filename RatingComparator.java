import java.util.Comparator;

class RatingComparator implements Comparator<Product> {

    public int compare(Product a, Product b){
        return b.getRating() - a.getRating();
    }
}