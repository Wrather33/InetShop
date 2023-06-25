import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static User Login(ArrayList<User> users) {
        HashMap<String, String> array = new HashMap<>();
        for (int i = 0; i < users.size(); i++) {
            array.put(users.get(i).getLogin(), users.get(i).getPassword());
        }
        Scanner reader = new Scanner(System.in);
        String name = "";
        String password = "";
        while (name.equals("")) {
            System.out.println("Press exit to back");
            System.out.println("Your name:");
            name = reader.nextLine();
            if(name.equals("exit")){
                return null;
            }
        }
        while (password.equals("")) {
            System.out.println("Press exit to back");
            System.out.println("Your password:");
            password = reader.nextLine();
            if(password.equals("exit")){
                return null;
            }
        }
        if (array.containsKey(name) && array.get(name).equals(password)) {
            String finalName = name;
            return users.stream()
                    .filter(user -> finalName.equals(user.getLogin()))
                    .findAny()
                    .orElse(null);
        } else {
            System.out.println("Try Again.");
            return Login(users);
        }
    }
    public static User Register(ArrayList<User> users){
        HashMap<String, String> array = new HashMap<>();
        for (int i = 0; i < users.size(); i++) {
            array.put(users.get(i).getLogin(), users.get(i).getPassword());
        }
        Scanner reader = new Scanner(System.in);
        String name = "";
        String password = "";
        String balance = "";
        while (name.equals("")) {
            System.out.println("Press exit to back");
            System.out.println("Your name:");
            name = reader.nextLine();
            if(name.equals("exit")){
                return null;
            }
        }
        while (password.equals("")) {
            System.out.println("Press exit to back");
            System.out.println("Your password:");
            password = reader.nextLine();
            if(password.equals("exit")){
                return null;
            }
        }
        do {
            System.out.println("Press exit to back");
            System.out.println("Your balance:");
            balance = reader.nextLine();
            if(balance.equals("exit")){
                return null;
            }
            else {
                try {
                    Integer.parseInt(balance);
                } catch(NumberFormatException e) {
                    System.out.println("You entered an invalid value. Try Again.");
                    return null;
                } catch(NullPointerException e) {
                    System.out.println("You entered an invalid value. Try Again.");
                    return null;
                }
            }
        } while (Integer.parseInt(balance) <= 0);
        if (!array.containsKey(name)) {
            return new User(name, password, Integer.parseInt(balance));
        } else {
            System.out.println("Try Again.");
            return Register(users);
        }

    }
    public static void main(String[] args) throws IOException {
        Category food = new Category("Food", new Product("Apple", 5, 8),
                new Product("Orange", 3, 9),
                new Product("Milk", 8, 7),
                new Product("Chicken", 14, 8)
        );
        Category phones = new Category("Phones", new Product("Iphone", 1000, 10),
                new Product("Nokia", 200, 6),
                new Product("Samsung", 600, 8)
        );
        Category clothes = new Category("Clothes", new Product("Tshirt", 50, 8),
                new Product("Hat", 70, 7),
                new Product("Sneakers", 100, 9)
        );
        /*System.out.println(food.getName() + " : "+food.productsToString());
        System.out.println(phones.getName() + " : "+phones.productsToString());
        System.out.println(clothes.getName() + " : "+clothes.productsToString());*/
        User mike = new User("Mike", "result", 10000, phones.getProducts().get(2),
                clothes.getProducts().get(0));
        User kate = new User("Kate", "kitty", 8000, phones.getProducts().get(0),
                clothes.getProducts().get(2));
        User john = new User("John", "gun", 5000,  food.getProducts().get(2),
                food.getProducts().get(3));
        Catalogue catalogue = new Catalogue(food, phones, clothes);
        ArrayList<User> users = new ArrayList<User>();
        users.add(mike);
        users.add(kate);
        users.add(john);
        Scanner reader = new Scanner(System.in);
        enum Auth {
            login,
            register,
            exit
        }
        enum Menu {
            catalogue,
            basket,
            exit
        }
        enum Basket{
            buy,
            clear,
            delete,
            exit
        }
        int choice;
        do {
            System.out.println("1: Register");
            System.out.println("2: Login");
            System.out.println("3: Exit");
            choice = reader.nextInt();
            boolean auth = false;
            User user = null;
            try {
                switch (Auth.values()[choice-1]) {
                    case login:
                        user = Register(users);
                        if(user != null){
                            users.add(user);
                            auth = true;
                        }
                        break;
                    case register:
                        user = Login(users);
                        if(user != null) {
                            auth = true;
                        }
                        break;

                    case exit:
                        break;
                    default:
                        choice = reader.nextInt();
                }
                while (auth) {
                    System.out.println("1: Catalogue");
                    System.out.println("2: Basket");
                    System.out.println("3: Exit");
                    int menuchoice = reader.nextInt();
                    try {
                    switch (Menu.values()[menuchoice-1]) {
                        case catalogue:
                            int catchoice;
                            do {
                                for (Category category : catalogue.getCategories()
                                ) {
                                    System.out.println(catalogue.getCategories().indexOf(category) + 1 + ": " +
                                            category.getName());
                                }
                                System.out.println(catalogue.getCategories().size() + 1 + ": Exit");
                                catchoice = reader.nextInt()-1;
                                if(catchoice !=catalogue.getCategories().size()){
                                try {
                                    catalogue.getCategories().get(catchoice);
                                    int productchoice;
                                    do {
                                        for (Product product : catalogue.getCategories().get(catchoice).getProducts()
                                        ) {
                                            System.out.println(catalogue.getCategories().get(catchoice).getProducts().indexOf(product) + 1 + ": " +
                                                    product.toString());
                                        }
                                        System.out.println(catalogue.getCategories().get(catchoice).getProducts().size() + 1 + ": Exit");
                                        System.out.println(catalogue.getCategories().get(catchoice).getProducts().size() + 2 + ": Sort by Name");
                                        System.out.println(catalogue.getCategories().get(catchoice).getProducts().size() + 3 + ": Sort by Price");
                                        System.out.println(catalogue.getCategories().get(catchoice).getProducts().size() + 4 + ": Sort by Rating");
                                        System.out.println(catalogue.getCategories().get(catchoice).getProducts().size() + 5 + ": Reverse sort by Name");
                                        System.out.println(catalogue.getCategories().get(catchoice).getProducts().size() + 6 + ": Reverse sort by Price");
                                        System.out.println(catalogue.getCategories().get(catchoice).getProducts().size() + 7 + ": Reverse sort by Rating");
                                        productchoice = reader.nextInt()-1;
                                        if(productchoice != catalogue.getCategories().get(catchoice).getProducts().size()){
                                            if(productchoice == catalogue.getCategories().get(catchoice).getProducts().size() + 1){
                                                catalogue.getCategories().get(catchoice).sortProducts(new NameComparator());
                                            }
                                            else if(productchoice == catalogue.getCategories().get(catchoice).getProducts().size() + 2){
                                                catalogue.getCategories().get(catchoice).sortProducts(new PriceComparator());
                                            }
                                            else if (productchoice == catalogue.getCategories().get(catchoice).getProducts().size() + 3){
                                                catalogue.getCategories().get(catchoice).sortProducts(new RatingComparator());
                                            }
                                            else if(productchoice == catalogue.getCategories().get(catchoice).getProducts().size() + 4){
                                                catalogue.getCategories().get(catchoice).reverseProducts(new NameComparator());
                                            }
                                            else if(productchoice == catalogue.getCategories().get(catchoice).getProducts().size() + 5){
                                                catalogue.getCategories().get(catchoice).reverseProducts(new PriceComparator());
                                            }
                                            else if(productchoice == catalogue.getCategories().get(catchoice).getProducts().size() + 6){
                                                catalogue.getCategories().get(catchoice).reverseProducts(new RatingComparator());
                                            }
                                            else {
                                                try {

                                                    Product product = catalogue.getCategories().get(catchoice).getProducts().get(
                                                            productchoice
                                                    );
                                                    user.Buy(product);
                                                    System.out.println("Added to cart");
                                                }
                                                catch (IndexOutOfBoundsException e) {
                                                    System.out.println("Product not found. Try Again.");
                                                }
                                            }}
                                    }while (productchoice != catalogue.getCategories().get(catchoice).getProducts().size());
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("Category not found. Try Again.");
                                }}

                            } while (catchoice != catalogue.getCategories().size());
                            break;
                        case basket:
                                int baskchoice;
                                String cart = null;
                                do{
                                    cart = user.showBasket();
                                    if(cart != null) {
                                        System.out.print(cart);
                                        System.out.println("Balance: "+user.getMoney());
                                        System.out.println("1: Buy products");
                                        System.out.println("2: Clear basket");
                                        System.out.println("3: Delete product");
                                        System.out.println("4: Exit");
                                        baskchoice = reader.nextInt();
                                        try {
                                            switch (Basket.values()[baskchoice-1]) {
                                                case buy:
                                                    System.out.println(user.BuyAll());
                                                    break;
                                                case clear:
                                                    user.clearBasket();
                                                    break;
                                                case delete:
                                                    int productChoice = 0;

                                                    do{
                                                        if(user.showBasket() != null) {
                                                            System.out.println("Delete item: ");
                                                            System.out.println(user.showBasket());
                                                            System.out.println(user.getBasket().getBasket().size() + 1 + ": Exit");
                                                            productChoice = reader.nextInt() - 1;
                                                            if(productChoice != user.getBasket().getBasket().size()){
                                                            try {
                                                                user.removeItem(productChoice);
                                                                productChoice--;
                                                            } catch (IndexOutOfBoundsException e) {
                                                                    System.out.println("Product not found. Try Again.");

                                                            }}
                                                        }
                                                        else {
                                                            productChoice = user.getBasket().getBasket().size();
                                                        }
                                                    }while (productChoice != user.getBasket().getBasket().size());
                                                    break;
                                                case exit:
                                                    break;
                                            }
                                        } catch (IndexOutOfBoundsException e) {
                                            System.out.println("Category not found. Try Again.");
                                        }
                                    }
                                     else {
                                         baskchoice = Basket.values().length;
                                        System.out.println("Basket is empty");
                                        }
                                } while (baskchoice != Basket.values().length);
                            break;
                        case exit:
                            auth = false;
                            break;
                        default:
                            menuchoice = reader.nextInt();
                    }

                }catch (IndexOutOfBoundsException e) {
                        System.out.println("Category not found. Try Again.");
                    }}
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Category not found. Try Again.");
            }
        } while (choice != Auth.values().length);
    }
}