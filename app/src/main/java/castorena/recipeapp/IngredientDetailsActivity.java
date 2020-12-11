package castorena.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

import castorena.recipeapp.domain.Ingredient;
import castorena.recipeapp.service.IngredientSvc;
import castorena.recipeapp.service.IngredientSvcInt;

public class IngredientDetailsActivity extends AppCompatActivity {

    private String currCategory;
    private ListView listView;
    private List<String> ingredList;
    private IngredientSvcInt ingredSvc;
    private List<String> savedIngredList;
    private int checked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_details);

        listView = findViewById(R.id.ingredientsList);
        ingredSvc = IngredientSvc.getInstance(this);
        savedIngredList = ingredSvc.retrieveAllNames();

        Intent intent = getIntent();
        currCategory = intent.getStringExtra("category");

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
        switch (currCategory) {
            case ("vegetables"):
                ingredList = Arrays.asList(getResources().getStringArray(R.array.vegetables));
                break;
            case ("fruits"):
                ingredList = Arrays.asList(getResources().getStringArray(R.array.fruits));
                break;
            case ("dairy"):
                ingredList = Arrays.asList(getResources().getStringArray(R.array.dairy));
                break;
            case ("fish"):
                ingredList = Arrays.asList(getResources().getStringArray(R.array.fish));
                break;
            case ("meats"):
                ingredList = Arrays.asList(getResources().getStringArray(R.array.meats));
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, ingredList);

        listView.setAdapter(adapter);

        if (checked == 0) {
            checkCurrItems();
            checked++;
        }

        adapter.notifyDataSetChanged();

        // creates new ingredient in db depending on the item clicked
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Ingredient ingredient = new Ingredient();
            if (!listView.isItemChecked(position)) {
                ingredient.setName(ingredList.get(position));
                ingredSvc.delete(ingredient);
            } else {
                ingredient.setName(ingredList.get(position));
                ingredient.setCategory(currCategory);
                ingredSvc.create(ingredient);
            }
        });
    }

    private void checkCurrItems() {
        for (int i = 0; i < ingredList.size(); i++) {
            if (savedIngredList.contains(ingredList.get(i))) {
                listView.setItemChecked(i, true);
            }
        }
    }
}