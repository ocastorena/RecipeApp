package castorena.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import castorena.recipeapp.service.IngredientSvcCacheImpl;

public class IngredientsActivity extends AppCompatActivity {

    IngredientSvcCacheImpl svc = IngredientSvcCacheImpl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

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
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button btn = findViewById(R.id.vegetablesBtn);
        Button fruit = findViewById(R.id.fruitsBtn);
        btn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), IngredientDetailsActivity.class));
            svc.setCurrCategory("Vegetables");
            overridePendingTransition(0,0);
        });

        fruit.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), IngredientDetailsActivity.class));
            svc.setCurrCategory("Fruits");
            overridePendingTransition(0,0);
        });
    }
}