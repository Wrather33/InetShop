import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String login;
    private String password;
    private int money;
    private Basket basket;
    public User(String login, String password, int money, Product... products){
        this.login = login;
        this.password = password;
        this.money = money;
        this.basket = new Basket(products);
    }
    public void clearBasket(){
        this.getBasket().clearBasket();
    }

    public void Buy(Product product){
        this.getBasket().Add(product);
    }
    public String BuyAll(){
        if(this.getBasket().getCost() <= this.getMoney()){
            this.getBasket().setDate(new Date());
            this.setMoney(this.getMoney() - this.getBasket().getCost());
            String string = this.getBasket().toString();
            this.clearBasket();
            return "Date: "+ new SimpleDateFormat("yyyy/MM/dd").format(this.getBasket().getDate())+"\n" +string+ "Thank you for your purchase";
        }
        else {
            return "Not enough money";
        }
    }
    public void removeItem(int index){
        this.getBasket().removeItem(index);
    }


    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String showBasket(){
        return basket.toString();
    }

}
