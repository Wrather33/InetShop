import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Basket {
    private ArrayList<Product> basket = new ArrayList<Product>();
    private Date date;
    public Basket(Product... products){
        for (int i = 0; i < products.length; i++) {
            this.basket.add(products[i]);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void Add(Product product){
        this.getBasket().add(product);
    }
    public void removeItem(int index){
        if(this.getBasket().contains(this.getBasket().get(index))) {
            this.getBasket().remove(index);
        }
    }

    public ArrayList<Product> getBasket() {
        return basket;
    }
    public int getCost(){
        return basket.stream()
                .mapToInt(a -> a.getPrice())
                .sum();
    }
    public void clearBasket(){
        this.getBasket().clear();
    }

    @Override
    public String toString() {
        if(basket.isEmpty()){
            return null;
        }
        else {
            return IntStream.range(0, basket.size())
                    .mapToObj(index -> index+1+" " +basket.get(index).toString()).collect(Collectors.joining(""))+"Cost: "+this.getCost()+"\n";
        /*return basket.stream().map(Product->{
                    return basket.get()": "+Product.toString();
                })
                .collect(Collectors.joining(""))+"Cost: "+this.getCost()+"\n";*/
    }}
}
