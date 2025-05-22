package YazanSystem;

import org.example.Order;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("Yazan", true, false);
    }

    @Test
    public void testLoginAndLogout() {
        assertFalse(user.isLoggedIn());
        user.login();
        assertTrue(user.isLoggedIn());
        user.logout();
        assertFalse(user.isLoggedIn());
    }

    @Test
    public void testIsAdmin() {
        assertTrue(user.isAdmin());
    }

    @Test
    public void testIsChef() {
        assertFalse(user.isChef());
    }

    @Test
    public void testGetUsername() {
        assertEquals("Yazan", user.getUsername());
    }

    @Test
    public void testAddAndGetOrderHistory() {
        Order dummyOrder = new Order("Pizza", java.time.LocalDate.now(), 25.0);
        user.addOrder(dummyOrder);
        List<Order> history = user.getOrderHistory();
        Assertions.assertEquals(1, history.size());
        assertEquals("Pizza", history.get(0).getMealName());
    }

    @Test
    public void testNotifications_AddAndCheck() {
        assertFalse(user.hasNotification("Low stock"));
        user.Add_Notification("Low stock");
        assertTrue(user.hasNotification("Low stock"));
    }


}
