package castorena.recipeapp.service;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import castorena.recipeapp.domain.Recipe;

public class RecipeSvc extends SQLiteOpenHelper implements RecipeSvcInt {
    private static String DB_PATH = "";
    private static String DB_NAME = "recipes.db";
    private SQLiteDatabase db;
    private final Context myContext;

    private static RecipeSvc instance = null;

    public static RecipeSvc getInstance(Context context) {
        if (instance == null) {

            instance = new RecipeSvc(context);
        }
        return instance;
    }

    public RecipeSvc(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = myContext.getDatabasePath(DB_NAME).toString();
    }

    public void createDB() throws IOException {
        boolean dbExist = checkDB();

        if (!dbExist) {
            this.getWritableDatabase();
            copyDB();
        }
    }

    private boolean checkDB() {
        SQLiteDatabase checkDataBase = null;
        try {
            String path = DB_PATH;
            checkDataBase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {
            Log.e("message", " " + e);
        }
        if (checkDataBase != null) {
            checkDataBase.close();
        }

        return checkDataBase != null;
    }

    private void copyDB() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String dbFilePath = DB_PATH;

        try {
            inputStream = myContext.getAssets().open(DB_NAME);
            outputStream = new FileOutputStream(dbFilePath);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            throw new Error("Problem copying database");
        }
    }

    public void openDB() throws SQLException {
        // Open the database
        String myPath = DB_PATH;
        db = SQLiteDatabase
                .openDatabase(
                        myPath, null,
                        SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close()
    {
        // close the database.
        if (db != null)
            db.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            createDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public List<String> retrieveAllRecipeNames() {
        List<String> recipes = new ArrayList<>();
        String[] columns = {"recipe_name", "ingredients"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from recipe_names",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(0);
            recipes.add(name);
            cursor.moveToNext();
        }
        db.close();
        return recipes;
    }

    @Override
    public List<Recipe> retrieveMatchingRecipes(String ingredient) {
        return null;
    }
}