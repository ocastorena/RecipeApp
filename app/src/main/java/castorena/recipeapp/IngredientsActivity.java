package castorena.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import castorena.recipeapp.service.IngredientSvc;
import castorena.recipeapp.service.IngredientSvcInt;

public class IngredientsActivity extends AppCompatActivity {

    private IngredientSvcInt ingredientSvc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        ingredientSvc = IngredientSvc.getInstance(getApplicationContext());

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

        Intent intent = new Intent(getApplicationContext(), IngredientDetailsActivity.class);
        Button vegg = findViewById(R.id.vegetablesBtn);
        Button fruit = findViewById(R.id.fruitsBtn);
        Button dairy = findViewById(R.id.dairyBtn);
        Button fish = findViewById(R.id.fishBtn);
        Button meats = findViewById(R.id.meatsBtn);
        TextView text = (TextView) findViewById(R.id.recipeNameText);
        text.setText(" You have " + ingredientSvc.retrieveAll().size() + " ingredients");

        vegg.setOnClickListener(v -> {
            intent.putExtra("category", "vegetables");
            startActivity(intent);
            overridePendingTransition(0,0);
        });

        fruit.setOnClickListener(v -> {
            intent.putExtra("category", "fruits");
            startActivity(intent);
            overridePendingTransition(0,0);
        });

        dairy.setOnClickListener(v -> {
            intent.putExtra("category", "dairy");
            startActivity(intent);
            overridePendingTransition(0,0);
        });

        fish.setOnClickListener(v -> {
            intent.putExtra("category", "fish");
            startActivity(intent);
            overridePendingTransition(0,0);
        });

        meats.setOnClickListener(v -> {
            intent.putExtra("category", "meats");
            startActivity(intent);
            overridePendingTransition(0,0);
        });
    }
}