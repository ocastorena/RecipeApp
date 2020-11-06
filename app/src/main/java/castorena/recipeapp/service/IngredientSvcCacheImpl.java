package castorena.recipeapp.service;

import java.util.LinkedList;
import java.util.List;

import castorena.recipeapp.domain.Ingredient;

public class IngredientSvcCacheImpl implements IngredientSvcInt{

    private int nextId = 0;
    private List<Ingredient> ingredients = new LinkedList<>();
    private String currCategory;

    private IngredientSvcCacheImpl() {
        initList();
    }

    private void initList() {
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setName("Carrot");
        ingredient1.setCategory("Vegetables");
        ingredients.add(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("Apple");
        ingredient2.setCategory("Fruits");
        ingredients.add(ingredient2);
    }

    private static IngredientSvcCacheImpl instance = new IngredientSvcCacheImpl();

    public static IngredientSvcCacheImpl getInstance() {
        return instance;
    }

    public Ingredient create(Ingredient ingredient) {
        ingredient.setId(++nextId);
        ingredients.add(ingredient);
        return ingredient;
    }

    public List<Ingredient> retrieveAllByCategory(String category) {
        List<Ingredient> list = new LinkedList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getCategory().equals(category)) {
                list.add(ingredients.get(i));
            }
        }
        return list;
    }

    public Ingredient update(Ingredient ingredient) {
        int size = ingredients.size();
        for (int i = 0; i < size; i++) {
            if (ingredients.get(i).getId() == ingredient.getId()) {
                ingredients.set(i, ingredient);
                break;
            }
        }
        return ingredient;
    }

    public Ingredient delete(Ingredient ingredient) {
        int size = ingredients.size();
        for (int i = 0; i < size; i++) {
            if (ingredients.get(i).getId() == ingredient.getId()) {
                ingredients.remove(i);
                break;
            }
        }
        return ingredient;
    }

    public String getCurrCategory() {
        return currCategory;
    }

    public void setCurrCategory(String currCategory) {
        this.currCategory = currCategory;
    }
}
