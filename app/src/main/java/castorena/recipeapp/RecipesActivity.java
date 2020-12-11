package castorena.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import castorena.recipeapp.service.DatabaseAccess;
import castorena.recipeapp.service.IngredientSvc;
import castorena.recipeapp.service.IngredientSvcInt;

public class RecipesActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> recipeList;
    private DatabaseAccess databaseAccess;
    private IngredientSvcInt svc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipes);

        listView = findViewById(R.id.recipeList);
        databaseAccess = DatabaseAccess.getInstance(this);

        //initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //set ingredients selected
        bottomNavigationView.setSelectedItemId(R.id.recipes);

        //get service
        svc = IngredientSvc.getInstance(this);

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

        List<String> userIngred = svc.retrieveAllNames();

        databaseAccess.open();
        recipeList = databaseAccess.getNames(userIngred);
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipeList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener((parent, view, position, id) -> {
           String recipeName = recipeList.get(position);
           Intent intent = new Intent(getApplicationContext(), RecipeDetailsActivity.class);
            intent.putExtra("recipe_name", recipeName);
           startActivity(intent);
           overridePendingTransition(0,0);
        });

    }

}