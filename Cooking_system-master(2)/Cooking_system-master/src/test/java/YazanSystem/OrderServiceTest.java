package YazanSystem;

import org.example.Order;
import org.example.OrderService;
import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private OrderService service;
    private User user;

    @BeforeEach
    public void setUp() {
        service = new OrderService();
        user = new User("TestUser", false, false);
    }

    @Test
    public void testAddOrderToUser_LoggedIn() {
        user.login();
        Order order = new Order("Pasta", java.time.LocalDate.now(), 12.0);
        service.addOrderToUser(user, order);

        assertEquals(1, user.getOrderHistory().size());
    }

    @Test
    public void testAddOrderToUser_NotLoggedIn() {
        Order order = new Order("Pasta", java.time.LocalDate.now(), 12.0);
        service.addOrderToUser(user, order);
        assertEquals(0, user.getOrderHistory().size());
    }

    @Test
    public void testAddMealToOrder_Valid() {
        String[] components = {"Chicken", "Lettuce"};
        service.addMealToOrder(components);
    }

    @Test
    public void testAddMealToOrder_Invalid() {
        String[] components = {"Soap", "Plastic"};
        service.addMealToOrder(components);
    }

    @Test
    public void testRecordAndGetReorderedIngredients() {
        service.Record_Ingredient("Tomato");
        List<String> result = service.getReorderedIngredients();
        assertTrue(result.contains("Tomato"));
    }


}
