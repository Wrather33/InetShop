public class Product {
    private int rating;
    private String name;
    private int price;
    public Product(String name, int price, int rating){
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    @Override
    public String toString(){
        return "Name: "+this.getName()+" Price: "+this.getPrice()+" Rating: "+this.getRating()+"\n";
    }

}
