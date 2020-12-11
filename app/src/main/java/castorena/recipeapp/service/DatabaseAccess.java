package castorena.recipeapp.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import castorena.recipeapp.domain.Recipe;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
      * Private constructor to aboid object creation from outside classes.
      *
      * @param context
      */
    private DatabaseAccess(Context context) {
        this.openHelper = new RecipeSvc(context);
    }

    /**
      * Return a singleton instance of DatabaseAccess.
      *
      * @param context the Context
      * @return the instance of DabaseAccess
      */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
      * Open the database connection.
      */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }


    /**
      * Close the database connection.
      */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
      * Read all quotes from the database.
      *
      * @return a List of quotes
      */
    public List<String> getNames(List<String> userIngred) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM recipe_ingredients", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (userIngred.contains(cursor.getString(1))) {
                if (!list.contains(cursor.getString(0))) {
                    list.add(cursor.getString(0));
                }
            }
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getRecipeIngredients(String recipeName) {
        List<String> list = new ArrayList<>();
        String query = "SELECT * FROM recipe_ingredients WHERE recipe_name = '" + recipeName + "'";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getRecipeSteps(String recipeName) {
        List<String> list = new ArrayList<>();
        String query = "SELECT * FROM recipe_steps WHERE recipe_name = '" + recipeName + "' ORDER BY step_number ASC";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public Recipe getRecipe(String recipeName) {
        System.out.println(recipeName);
        Recipe recipe = new Recipe();
        recipe.setName(recipeName);
        recipe.setIngredients(getRecipeIngredients(recipeName));
        recipe.setSteps(getRecipeSteps(recipeName));
        return recipe;
    }
}
