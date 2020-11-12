package castorena.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IngredientsActivity extends AppCompatActivity {

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

        Intent intent = new Intent(getApplicationContext(), IngredientDetailsActivity.class);
        Button vegg = findViewById(R.id.vegetablesBtn);
        Button fruit = findViewById(R.id.fruitsBtn);
        Button dairy = findViewById(R.id.dairyBtn);
        Button fish = findViewById(R.id.fishBtn);
        Button meats = findViewById(R.id.meatsBtn);

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