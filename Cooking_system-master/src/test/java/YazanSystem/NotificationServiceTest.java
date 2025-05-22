package YazanSystem;

import org.example.Customer;
import org.example.NotificationService;
import org.example.Order;
import org.example.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationServiceTest {

    @Test
    public void testScheduledDeliveriesAndSendNotificationToManager() {
        NotificationService service = new NotificationService();

        Order dummyOrder = new Order("Meal", LocalDateTime.now().plusHours(2));
        service.scheduledDeliveries(dummyOrder);

        service.sendNotificationToManager("Low stock on Tomatoes");
        List<String> messages = service.get_Notifications_to_Maneger();

        assertEquals(1, messages.size());
        assertEquals("Low stock on Tomatoes", messages.get(0));
    }

    @Test
    public void testSendNotificationToChef() {
        NotificationService service = new NotificationService();

        User chef = new User("Chef1", false, true);
        chef.login();

        service.sendNotification(chef, "You have a new order");

        assertTrue(chef.hasNotification("You have a new order"));
    }

    @Test
    public void testCheckAndSendNotificationsTrue() {
        NotificationService service = new NotificationService();

        Customer customer = new Customer("Qais", "jenin");
        Order urgentOrder = new Order(customer, LocalDateTime.now().plusMinutes(30)); // قريب
        service.scheduledDeliveries(urgentOrder);

        boolean result = service.checkAndSendNotifications();

        assertTrue(result);
    }

    @Test
    public void testCheckAndSendNotificationsFalse() {
        NotificationService service = new NotificationService();

        Customer customer = new Customer("ahmad", "bit laheem");
        Order futureOrder = new Order(customer, LocalDateTime.now().plusHours(5));
        service.scheduledDeliveries(futureOrder);

        boolean result = service.checkAndSendNotifications();

        assertFalse(result);
    }

}
