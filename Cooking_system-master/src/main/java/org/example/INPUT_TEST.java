package org.example;

import java.util.ArrayList;
import java.util.List;

public class INPUT_TEST
{
    private boolean isCustomerLoggedIn;
    public String selectedMeal;

    private boolean isAdminLoggedIn;

    public static List<String> orderHistory;
    private boolean isChefLoggedIn;




    public INPUT_TEST() {
        this.isCustomerLoggedIn = false;
        this.isAdminLoggedIn = false;
        this.isChefLoggedIn = false;
        orderHistory = new ArrayList<>();
        this.selectedMeal = null;


    }


    public void loginCustomer()
    {
        isCustomerLoggedIn = true;
        System.out.println("Customer logged in successfully");
    }


    public void loginAdmin()
    {
        isAdminLoggedIn = true;
        System.out.println("Admin logged in successfully");
    }


    public void loginChef()
    {
        isChefLoggedIn = true;
        System.out.println("Chef logged in successfully");
    }





    public void fetchOrderHistory()
    {
        if (isCustomerLoggedIn)

        {
            orderHistory.add("Shawerma");
            orderHistory.add("Pizza");

            System.out.println("Order history loaded successfully");

        }
        else {
            System.out.println("User must be logged in to view order history");
        }
    }




    public void requestCustomerOrderData() {
        if (isAdminLoggedIn) {
            System.out.println("Access granted: Admin can retrieve order data");
        } else
        {
            System.out.println("Access denied: Only admins can access order data");
        }
    }


    public static void accessStoredOrderHistory()
    {
        if (orderHistory != null && !orderHistory.isEmpty())
        {
            System.out.println("Order history is accessible");
        }
        else
        {
            System.out.println("Order history is empty");
        }
    }



    public void suggestPersonalizedMealPlan()
    {
        String mealPlan = "Recommended meal: Healthy Salad";


        System.out.println("Personalized meal plan suggested: " + mealPlan);
    }

    public void analyzeTrendsAndGenerateReports()
    {
        String report = "Most ordered meal is Shawerma";

        System.out.println("Report generated successfully: " + report);
    }


    public void navigateToCustomerProfile()
    {
        if (isChefLoggedIn)
        {
            System.out.println("Chef accessed the customer's profile successfully");
        }


        else {
            System.out.println("Access denied: Only chefs can view customer profiles");
        }
    }
    public void selectMealToReorder() {
        if (orderHistory != null && !orderHistory.isEmpty())

        {
            selectedMeal = orderHistory.get(0);

            if (selectedMeal != null)

            {
                System.out.println("Meal selected for reorder: " + selectedMeal);
            } else {
                System.out.println("Selected meal should not be null");
            }
        }


        else {
            System.out.println("No orders available to select ");
        }
    }


    public void navigateToPage(String page)
    {
        if (isCustomerLoggedIn && "Order History".equals(page))
        {
            System.out.println("Navigated to Order History successfully");
        }
        else if (isAdminLoggedIn && "Admin Dashboard".equals(page))
        {
            System.out.println("Navigated to Admin Dashboard successfully");
        }
        else if (isChefLoggedIn && "Chef Dashboard".equals(page))
        {
            System.out.println("Navigated to Chef Dashboard successfully");
        } else
        {
            System.out.println("Navigation failed: Incorrect page or user not logged in");
        }
    }


    public void login(String username, String number) {
        if (number.matches("\\d{10}")) {
            System.out.println("Welcome " + username + "! Successfully logged in with number: " + number);
        } else {
            System.out.println("Login failed. Please enter a valid 10-digit number.");
        }
    }

    public void sendSuggestions(String suggestion) {
        if (suggestion == null || suggestion.trim().isEmpty()) {
            System.out.println("⚠ No suggestion submitted. Please enter a valid suggestion.");
            return;
        }

        System.out.println(" Your suggestion has been received: \"" + suggestion + "\"");
        System.out.println("It will be reviewed by the management. Thank you for your contribution!");
    }

}
