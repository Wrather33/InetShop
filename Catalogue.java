import java.util.ArrayList;

public class Catalogue {
    private ArrayList<Category> categories = new ArrayList<Category>();
    public Catalogue(Category ...categories){
        for (int i = 0; i < categories.length; i++) {
            this.categories.add(categories[i]);
        }
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
