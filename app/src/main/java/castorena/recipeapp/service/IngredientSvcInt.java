package castorena.recipeapp.service;

import java.util.List;

import castorena.recipeapp.domain.Ingredient;

public interface IngredientSvcInt {

    public Ingredient create(Ingredient contact);
    public List<Ingredient> retrieveAll();
    public Ingredient update(Ingredient ingredient);
    public Ingredient delete(Ingredient ingredient);
    
}
