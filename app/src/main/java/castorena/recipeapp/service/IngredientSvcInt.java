package castorena.recipeapp.service;

import java.util.List;

import castorena.recipeapp.domain.Ingredient;

public interface IngredientSvcInt {

    Ingredient create(Ingredient contact);
    List<Ingredient> retrieveAll();
    Ingredient delete(Ingredient ingredient);
}
