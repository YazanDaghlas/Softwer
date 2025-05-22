package org.example;
import com.sun.nio.sctp.Notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationService
{
    private List<Order> scheduledDeliveries = new ArrayList<>();




    private List<String> NotificationToManager = new ArrayList<>();

    public void scheduledDeliveries(Order order)
    {

        scheduledDeliveries.add(order);
    }


    public void sendNotification(Customer customer)
    {
        System.out.println("Notification sent to: " + customer.gitAddress());
    }

    public void sendNotificationToManager(String message)
    {
        NotificationToManager.add(message);

        System.out.println("Notification sent to manager: " + message);
    }
    public List<String> get_Notifications_to_Maneger()
    {

        return NotificationToManager;
    }

    public boolean checkAndSendNotifications()
    {
        for (Order order : scheduledDeliveries)

        {
            if (order.getDeliveryTime().minusHours(1).isBefore(LocalDateTime.now()))
            {
                sendNotification(order.getCustomer());
                return true;
            }
        }
        return false;
    }






    public void sendNotification(User chef, String message)
    {
        if (chef.isChef())
        {
            chef.Add_Notification(message);

            System.out.println("Notification sent to " + chef.getUsername() + ": " + message);
        }
    }

    public void sendDeliveryNotification(String username, String customMeal) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println(" Invalid username.");
            return;
        }

        if (customMeal == null || customMeal.trim().isEmpty()) {
            System.out.println(" Invalid meal name.");
            return;
        }

        String message = " Dear " + username + ", your order \"" + customMeal + "\" has been successfully delivered. Thank you for using our service!";
        System.out.println(message);
    }

    public void sendLowStockNotification(String ingredientName) {
        if (ingredientName == null || ingredientName.trim().isEmpty()) {
            System.out.println("Invalid ingredient name.");
            return;
        }

        String message = "Warning: Low stock for ingredient \"" + ingredientName + "\". Please restock.";
        System.out.println(message);
    }

}
