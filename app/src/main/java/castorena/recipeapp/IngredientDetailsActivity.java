package castorena.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import castorena.recipeapp.domain.Ingredient;
import castorena.recipeapp.service.IngredientSvcCacheImpl;
import castorena.recipeapp.service.IngredientSvcInt;

public class IngredientDetailsActivity extends AppCompatActivity {

    private ListView listView = null;
    private IngredientSvcInt ingredSvc = null;
    private ArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_details);

        listView = (ListView)findViewById(R.id.ingredientsList);
        ingredSvc = IngredientSvcCacheImpl.getInstance();

        //initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //set ingredients selected
        bottomNavigationView.setSelectedItemId(R.id.ingredients);

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
        final List<Ingredient> ingredlist = ingredSvc.retrieveAllByCategory(ingredSvc.getCurrCategory());
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ingredlist);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}