package castorena.recipeapp.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import castorena.recipeapp.domain.Ingredient;

public class IngredientSvc extends SQLiteOpenHelper implements IngredientSvcInt  {

    private static final String TAG = "IngredientSvc";
    private static final String DBNAME = "ingredients.db";
    private static final int DBVERSION = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate");
        String CREATE_INGRED_TABLE = "CREATE TABLE ingredients (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE, category TEXT NOT NULL)";
        db.execSQL(CREATE_INGRED_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        Log.i(TAG, "onUpgrade");
        String DROP_INGRED_TABLE = "DROP TABLE IF EXISTS ingredients";
        db.execSQL(DROP_INGRED_TABLE);
        onCreate(db);
    }

    private static IngredientSvc instance = null;

    public static IngredientSvc getInstance(Context context) {
        if (instance == null) {
            instance = new IngredientSvc(context);
        }
        return instance;
    }

    public  IngredientSvc(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public Ingredient create(Ingredient ingredient) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", ingredient.getName());
        values.put("category", ingredient.getCategory());
        db.insert("ingredients", null, values);

        // get las insert id
        Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null);
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        ingredient.setId(id);
        cursor.close();
        db.close();
        return ingredient;
    }

    @Override
    public List<Ingredient> retrieveAll() {
        List<Ingredient> ingredients = new ArrayList<>();
        String[] columns = {"id", "name", "category"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("ingredients", columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Ingredient ingredient = getIngredient(cursor);
            ingredients.add(ingredient);
            cursor.moveToNext();
        }
        db.close();
        return ingredients;
    }

    private Ingredient getIngredient(Cursor cursor) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(cursor.getInt(0));
        ingredient.setName(cursor.getString(1));
        ingredient.setCategory(cursor.getString(2));
        return ingredient;
    }

    @Override
    public Ingredient delete(Ingredient ingredient) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("ingredients", "id = " + ingredient.getId(), null);
        db.close();
        return ingredient;
    }
}
