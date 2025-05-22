package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService
{

    private Order order;
    private List<Order> orders = new ArrayList<>();
    public void addOrderToUser(User user, Order order)
    {
        if (user.isLoggedIn())
        {
            user.addOrder(order);
            System.out.println("Order added to user");

        }
        else
        {
            System.out.println("User must be logged in");
        }
    }



 



    public void addMealToOrder(String[] components)
    {
        order = new Order("Custom Salad", LocalDate.now(), 15.0);
        boolean hasValidComponent = false;

        for (String component : components)
        {
            if (isValidComponent(component))
            {
                order.addComponent(component);

                hasValidComponent = true;
                System.out.println("Added component: " + component);
            }
            else
            {
                System.out.println("Invalid component: " + component);
            }
        }

        if (!hasValidComponent)
        {
            order = null; 
        }
        else
        {
            System.out.println("Meal added to order successfully");

        }
    }
  
    private boolean isValidComponent(String component)
    {
   
        String[] availableComponents = {"Chicken", "Lettuce", "Tomato", "Cucumber", "Cheese"};
        for (String validComponent : availableComponents)
        {
            if (validComponent.equals(component))
            {
                return true;
            }
        }
        return false;
    }

    private List<String> reorderIngredients = new ArrayList<>();


    public void Record_Ingredient(String ingredient)
    {
        reorderIngredients.add(ingredient);

        System.out.println("Reordering ingredient is Done: " + ingredient);

    }




    public List<String> getReorderedIngredients()
    {
        return reorderIngredients;

    }


    public Order getOrder() {
        return order;
    }

}
