package castorena.recipeapp.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable{
    private String name;
    private ArrayList<String> ingredients;
    private ArrayList<String> steps;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return name;
    }
}
