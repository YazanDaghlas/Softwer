package org.example;

import java.util.ArrayList;
import java.util.List;

public class User
{
    private String role;
    private String username;
    private boolean isAdmin;
    private boolean isChef;
    private boolean isLoggedIn;
    private List<Order> orderHistory;
    private List<String> notifications;

 
    public User(String username, boolean isAdmin, boolean isChef)
    {
        this.username = username;
        this.isAdmin = isAdmin;
        this.isChef = isChef;
        this.isLoggedIn = false;
        this.orderHistory = new ArrayList<>(); 
        this.notifications=new ArrayList<>();

    }

 
    public void login()
    {
        this.isLoggedIn = true;

        System.out.println(username + " has logged in.");
    }

 
    public void logout()
    {
        this.isLoggedIn = false;

        System.out.println(username + " has logged out.");
    }

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }
  
    public boolean isAdmin()
    {
        return isAdmin;
    }

  
    public boolean isChef()
    {
        return isChef;
    }

   
    public boolean isLoggedIn()
    {
        return isLoggedIn;
    }


    public String getUsername()

    {
        return username;
    }

  
    public void addOrder(Order order)

    {
        orderHistory.add(order);
    }


    public List<Order> getOrderHistory()
    {
        return new ArrayList<>(orderHistory);
    }

    public void Add_Notification(String message)

    {
        notifications.add(message);
    }


    public boolean hasNotification(String message)

    {
        return notifications.contains(message);
    }

    public String get_Notification_List() {
        if (notifications.isEmpty()) {
            return "No notifications.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < notifications.size(); i++) {
            sb.append((i + 1)).append(". ").append(notifications.get(i)).append("\n");
        }
        return sb.toString();
    }

}
