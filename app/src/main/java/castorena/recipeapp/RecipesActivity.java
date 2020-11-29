package castorena.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import castorena.recipeapp.domain.Ingredient;
import castorena.recipeapp.service.IngredientSvc;
import castorena.recipeapp.service.IngredientSvcInt;
import castorena.recipeapp.service.RecipeSvc;
import castorena.recipeapp.service.RecipeSvcInt;

public class RecipesActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> recipeList;
    private RecipeSvcInt svc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipes);

        svc = RecipeSvc.getInstance(this);

        try {
            svc.createDB();
            svc.openDB();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        listView = findViewById(R.id.recipeList);

        recipeList = svc.retrieveAllRecipeNames();

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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipeList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), RecipeDetailsActivity.class);

            startActivity(intent);

            overridePendingTransition(0,0);

        });
    }

}