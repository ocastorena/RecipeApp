package castorena.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        //initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

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
}