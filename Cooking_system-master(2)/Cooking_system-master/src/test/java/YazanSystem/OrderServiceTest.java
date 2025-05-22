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

        Order order = new Order("Custom Salad", java.time.LocalDate.now(), 10.0);
        for (String component : components) {
            order.addComponent(component);
        }
    user.login();
        service.addOrderToUser(user, order);


        assertFalse(user.getOrderHistory().isEmpty(), "Order history should not be empty");
        assertEquals("Custom Salad", user.getOrderHistory().get(0).getMealName());
    }



    @Test
    public void testAddMealToOrder_Invalid() {
        String[] components = {"Soap", "Plastic"};

        service.addMealToOrder(components);


        assertNull(service.getOrder(), "Expected order to be null due to invalid components");
    }


    @Test
    public void testRecordAndGetReorderedIngredients() {
        service.Record_Ingredient("Tomato");
        List<String> result = service.getReorderedIngredients();
        assertTrue(result.contains("Tomato"));
    }


}
