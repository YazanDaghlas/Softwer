package YazanSystem;

import org.example.IngredientManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientManagerTest {

    private IngredientManager manager;

    @BeforeEach
    public void setup() {
        manager = new IngredientManager();
    }

    @Test
    public void testGetAlternativeIngredient_Found() {
        assertEquals("Red Pepper", manager.getAlternativeIngredient("Tomato"));
    }
    @Test
    public void testSuggestAlternativeBasedOnDietaryRestrictions_IngredientNotExists() {
        assertEquals("No alternative available",
                manager.suggestAlternativeBasedOnDietaryRestrictions("Coffee", "Lactose Free"));
    }

    @Test
    public void testGetAlternativeIngredient_NotFound() {
        assertEquals("No alternative available", manager.getAlternativeIngredient("Coffee"));
    }

    @Test
    public void testIsAlternativeSuggested_True() {
        assertTrue(manager.isAlternativeSuggested("Egg"));
    }

    @Test
    public void testIsAlternativeSuggested_False() {
        assertFalse(manager.isAlternativeSuggested("Coffee"));
    }

    @Test
    public void testSuggestAlternativeBasedOnDietaryRestrictions_LactoseFree() {
        assertEquals("Almond Milk", manager.suggestAlternativeBasedOnDietaryRestrictions("Milk", "Lactose Free"));
    }

    @Test
    public void testSuggestAlternativeBasedOnDietaryRestrictions_Vegan() {
        assertEquals("Flaxseed", manager.suggestAlternativeBasedOnDietaryRestrictions("Egg", "Vegan"));
    }

    @Test
    public void testSuggestAlternativeBasedOnDietaryRestrictions_DefaultFallback() {
        assertEquals("Red Pepper", manager.suggestAlternativeBasedOnDietaryRestrictions("Tomato", "None"));
    }

    @Test
    public void testReviewAndModifyAlternative_Approved() {
        assertEquals("Chef approved the suggested alternative: Red Pepper",
                manager.reviewAndModifyAlternative("Tomato", "Red Pepper", "Anything"));
    }

    @Test
    public void testReviewAndModifyAlternative_Modified() {
        String result = manager.reviewAndModifyAlternative("Egg", "WrongSuggestion", "Chia Seeds");
        assertEquals("Chef modified the alternative for Egg to: Chia Seeds", result);


        assertEquals("Chia Seeds", manager.getAlternativeIngredient("Egg"));
    }

    @Test
    public void testReviewAndModifyAlternative_NotFound() {
        assertEquals("No alternative available to review.",
                manager.reviewAndModifyAlternative("Coffee", "Whatever", "Tea"));
    }

    @Test
    public void testIsIngredientAvailable_True() {
        assertTrue(manager.isIngredientAvailable("Milk"));
    }

    @Test
    public void testIsIngredientAvailable_False() {
        assertFalse(manager.isIngredientAvailable("Coffee"));
    }
}
