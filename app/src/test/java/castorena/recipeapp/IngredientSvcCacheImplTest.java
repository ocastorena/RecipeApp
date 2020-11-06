package castorena.recipeapp;

import org.junit.Test;

import java.util.List;

import castorena.recipeapp.domain.Ingredient;
import castorena.recipeapp.service.IngredientSvcCacheImpl;

import static org.junit.Assert.*;

public class IngredientSvcCacheImplTest {

    @Test
    public void testCRUD() {

        IngredientSvcCacheImpl svc = IngredientSvcCacheImpl.getInstance();

        Ingredient ingredient = new Ingredient();
        ingredient.setName("Apple");
        ingredient.setCategory("Fruits");

        List<Ingredient> list = svc.retrieveAllByCategory("Fruits");
        assertNotNull(list);
        int initialSize = list.size();

        // test the create method
        ingredient = svc.create(ingredient);
        assertNotNull(ingredient);

        // test the retrieve method
        list = svc.retrieveAllByCategory("Fruits");
        int size = list.size();
        assertEquals(initialSize + 1, size);

        // test the update method

        // test the delete method
        ingredient = svc.delete(ingredient);
        assertNotNull(ingredient);
        list = svc.retrieveAllByCategory("Fruits");
        size = list.size();
        assertEquals(initialSize, size);
    }
}
