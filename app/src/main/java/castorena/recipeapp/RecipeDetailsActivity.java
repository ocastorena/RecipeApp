package castorena.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

import castorena.recipeapp.domain.Recipe;
import castorena.recipeapp.service.DatabaseAccess;

public class RecipeDetailsActivity extends AppCompatActivity {

    private ListView listViewIngred;
    private ListView listViewSteps;
    private List<String> ingredList;
    private List<String> ingredSteps;
    private DatabaseAccess databaseAccess;
    private String currRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        databaseAccess = DatabaseAccess.getInstance(this);

        Intent intent = getIntent();
        currRecipe = intent.getStringExtra("recipe_name");

        //initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        listViewIngred = findViewById(R.id.recipeIngredList);
        listViewSteps = findViewById(R.id.recipeStepsList);

        //set ingredients selected
        bottomNavigationView.setSelectedItemId(R.id.recipes);

        //perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.recipes) {
                startActivity(new Intent(getApplicationContext(), RecipesActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            if (item.getItemId() == R.id.ingredients) {
                startActivity(new Intent(getApplicationContext(), IngredientsActivity.class));
                overridePendingTransition(0,0);
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        databaseAccess.open();
        Recipe recipe = databaseAccess.getRecipe(currRecipe);
        databaseAccess.close();
        System.out.println(recipe.getName());
        TextView recipeName = findViewById(R.id.recipeNameText);
        recipeName.setText(recipe.getName());

        ingredList = recipe.getIngredients();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ingredList);
        listViewIngred.setAdapter(adapter1);

        ingredSteps = recipe.getSteps();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ingredSteps);
        listViewSteps.setAdapter(adapter2);



    }
}