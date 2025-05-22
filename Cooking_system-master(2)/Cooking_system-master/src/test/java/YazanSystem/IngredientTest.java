package YazanSystem;

import org.example.Ingredient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    @Test
    public void testConstructorAndGetters() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5);

        assertEquals("Tomato", ingredient.getName());
        assertEquals(10, ingredient.getQuantity());
        assertEquals(5, ingredient.getMinQuantity());
    }

    @Test
    public void testSetQuantity() {
        Ingredient ingredient = new Ingredient("Cheese", 20, 10);

        ingredient.setQuantity(30);

        assertEquals(30, ingredient.getQuantity());
    }
    @Test
    public void testUpdateQuantityAndLowStock() {
        Ingredient ing = new Ingredient("Lettuce", 3, 5);
        ing.setQuantity(2);

        assertTrue(ing.isLowStock());
    }

}

