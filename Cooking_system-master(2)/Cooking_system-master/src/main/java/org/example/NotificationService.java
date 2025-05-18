package org.example;
import com.sun.nio.sctp.Notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationService
{
    private List<Order> scheduledDeliveries = new ArrayList<>();




    private List<String> notificationToManager = new ArrayList<>();

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
        notificationToManager.add(message);

        System.out.println("Notification sent to manager: " + message);
    }
    public List<String> get_Notifications_to_Maneger()
    {

        return notificationToManager;
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
}
