package castorena.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import castorena.recipeapp.service.IngredientSvc;
import castorena.recipeapp.service.IngredientSvcInt;

public class RecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        //initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //set ingredients selected
        bottomNavigationView.setSelectedItemId(R.id.recipes);

        //get service
        IngredientSvcInt svc = IngredientSvc.getInstance(this);

        //perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.ingredients) {
                startActivity(new Intent(getApplicationContext(), IngredientsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (true) {
            Button btn = findViewById(R.id.RecipeBtn);
            btn.setText(R.string.recipe_1_name);
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(v -> {
                startActivity(new Intent(getApplicationContext(), RecipeDetailsActivity.class));
                overridePendingTransition(0,0);
            });
        }
    }
}