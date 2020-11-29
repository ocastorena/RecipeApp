package castorena.recipeapp.service;

import java.io.IOException;
import java.util.List;

import castorena.recipeapp.domain.Recipe;

public interface RecipeSvcInt {
    List<String> retrieveAllRecipeNames();
    List<Recipe> retrieveMatchingRecipes(String ingredient);
    void createDB() throws IOException;
    void openDB() throws IOException;
}
