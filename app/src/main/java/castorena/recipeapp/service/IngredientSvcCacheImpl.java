package castorena.recipeapp.service;

import java.util.LinkedList;
import java.util.List;

import castorena.recipeapp.domain.Ingredient;

public class IngredientSvcCacheImpl implements IngredientSvcInt{

    private int nextId = 0;
    private List<Ingredient> ingredients = new LinkedList<>();

    private IngredientSvcCacheImpl() {
        initList();
    }

    private void initList() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Carrot");
        ingredient.setCategory("Vegetables");
        ingredients.add(ingredient);
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

    public List<Ingredient> retrieveAll() {
        return ingredients;
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

}
