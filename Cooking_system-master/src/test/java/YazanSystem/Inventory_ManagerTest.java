package YazanSystem;

import org.example.Ingredient;
import org.example.Inventory_Manager;
import org.example.NotificationService;
import org.example.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class Inventory_ManagerTest {

    private NotificationService notificationService;
    private OrderService orderService;
    private Inventory_Manager inventoryManager;

    @BeforeEach
    public void setup() {
        notificationService = new NotificationService();
        orderService = new OrderService(); // لو في اعتماديات حقيقية ممكن نعمل mock
        inventoryManager = new Inventory_Manager(notificationService, orderService);
    }

    @Test
    public void testAddIngredientAndLowStockDetection() {
        Ingredient tomato = new Ingredient("Tomato", 5, 10);
        inventoryManager.addIngredient(tomato);

        assertTrue(inventoryManager.islowstock());
    }

    @Test
    public void testUpdateStockTriggersNotification() {
        Ingredient egg = new Ingredient("Egg", 15, 10);
        inventoryManager.addIngredient(egg);

        // قلل الكمية إلى أقل من الحد
        inventoryManager.updateStock(5, "Egg");

        List<String> notifications = notificationService.get_Notifications_to_Maneger();
        assertEquals(1, notifications.size());
        assertTrue(notifications.get(0).contains("Egg"));
    }

    @Test
    public void testAreMultipleIngredientsLowStock() {
        Ingredient milk = new Ingredient("Milk", 3, 5);
        Ingredient wheat = new Ingredient("Wheat", 2, 4);
        Ingredient tomato = new Ingredient("Tomato", 10, 5); // مش منخفض

        inventoryManager.addIngredient(milk);
        inventoryManager.addIngredient(wheat);
        inventoryManager.addIngredient(tomato);

        boolean result = inventoryManager.areMultipleIngredientsLowStock();

        assertTrue(result);

        List<String> notifications = notificationService.get_Notifications_to_Maneger();
        assertEquals(1, notifications.size());
        assertTrue(notifications.get(0).contains("Milk"));
        assertTrue(notifications.get(0).contains("Wheat"));
        assertFalse(notifications.get(0).contains("Tomato"));
    }

    @Test
    public void testNoLowStockIngredients() {
        Ingredient sugar = new Ingredient("Sugar", 20, 5);
        inventoryManager.addIngredient(sugar);

        assertFalse(inventoryManager.areMultipleIngredientsLowStock());
        assertFalse(inventoryManager.islowstock());

        List<String> notifications = notificationService.get_Notifications_to_Maneger();
        assertEquals(0, notifications.size());
    }



}
