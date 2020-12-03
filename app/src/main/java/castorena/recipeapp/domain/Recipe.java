package castorena.recipeapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable{
    private String name;
    private List<String> ingredients;
    private List<String> steps;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return name;
    }
}
