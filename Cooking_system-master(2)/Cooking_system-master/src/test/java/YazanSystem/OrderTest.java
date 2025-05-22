package YazanSystem;

import org.example.Customer;
import org.example.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void testCreateOrderWithCustomer() {
        Customer customer = new Customer("Ali","Nablus");
        LocalDateTime deliveryTime = LocalDateTime.now();
        Order order = new Order(customer, deliveryTime);

        assertEquals(customer, order.getCustomer());
        assertEquals(deliveryTime, order.getDeliveryTime());

        LocalDateTime newTime = deliveryTime.plusDays(1);
        order.setDeliveryTime(newTime);
        assertEquals(newTime, order.getDeliveryTime());
    }

    @Test
    public void testCreateOrderWithMealDetails() {
        Order order = new Order("Burger", LocalDate.now(), 10.5);

        assertEquals("Burger", order.getMealName());
        assertEquals(10.5, order.getPrice());
        assertTrue(order.getComponents().isEmpty());
    }

    @Test
    public void testAddComponentValidAndInvalid() {
        Order order = new Order("Salad", LocalDate.now(), 8.0);
        order.addComponent("Chicken");
        order.addComponent("Invalid");

        assertTrue(order.getComponents().contains("Chicken"));
        assertFalse(order.getComponents().contains("Invalid"));
    }

    @Test
    public void testToString() {
        Order order = new Order("Test Meal", LocalDate.now(), 20.0);
        order.addComponent("Lettuce");
        order.addComponent("Tomato");
        String str = order.toString();
        assertTrue(str.contains("Test Meal"));
        assertTrue(str.contains("Lettuce"));
    }

}
