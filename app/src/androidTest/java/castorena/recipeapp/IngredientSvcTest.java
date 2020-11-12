package castorena.recipeapp;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import castorena.recipeapp.domain.Ingredient;
import castorena.recipeapp.service.IngredientSvc;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class IngredientSvcTest {

    @Test
    public void testCRUD() throws Exception {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("castorena.recipeapp", appContext.getPackageName());

        IngredientSvc svc = IngredientSvc.getInstance(appContext);
        assertNotNull(svc);

        List<Ingredient> list = svc.retrieveAll();
        assertNotNull(list);
        int initialSize = list.size();

        Ingredient ingredient = new Ingredient();
        ingredient.setName("Apple");
        ingredient.setCategory("Fruits");
        ingredient = svc.create(ingredient);
        assertNotNull(ingredient);

        int id = ingredient.getId();
        assertNotEquals(0, id);

        list =svc.retrieveAll();
        assertEquals( initialSize + 1, list.size());

        ingredient = svc.delete(ingredient);
        assertNotNull(ingredient);

        list = svc.retrieveAll();
        assertEquals(initialSize, list.size());
    }
}
