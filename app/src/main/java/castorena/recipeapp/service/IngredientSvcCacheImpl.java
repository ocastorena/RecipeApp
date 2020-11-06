package castorena.recipeapp.service;

import java.util.LinkedList;
import java.util.List;

import castorena.recipeapp.domain.Ingredient;

public class IngredientSvcCacheImpl implements IngredientSvcInt{

    private List<Ingredient> ingredients = new LinkedList<Ingredient>();

    public Ingredient create(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

}
