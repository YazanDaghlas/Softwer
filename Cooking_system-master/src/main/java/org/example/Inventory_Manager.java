package org.example;

import java.util.ArrayList;
import java.util.List;

public class Inventory_Manager
{
    private NotificationService notification_Service;

    private OrderService order_Service;

    private List<Ingredient> ingredients;



    public Inventory_Manager(NotificationService notification_Service, OrderService order_Service)
    {
        this.notification_Service = notification_Service;

        this.order_Service = order_Service;

        this.ingredients = new ArrayList<>();  
    }

    public Inventory_Manager() {
        this.notification_Service = new NotificationService();
        this.order_Service = new OrderService();
        this.ingredients = new ArrayList<>();
    }



    public void updateStock(int new_Quantity, String ingredientName)
    {
        for (Ingredient ingredient : ingredients)
        {
            if (ingredient.getName().equals(ingredientName))
            {

                           ingredient.setQuantity(new_Quantity);

                checkLevelOfIngredient(ingredient);

                break;
            }
        }
    }



    public boolean areMultipleIngredientsLowStock()
    {
        List<String> lowStockIngredients = new ArrayList<>();

        for (Ingredient ingredient : ingredients)

        {
            if (ingredient.getQuantity() < ingredient.getMinQuantity())
            {
                lowStockIngredients.add(ingredient.getName());
            }
        }

        if (!lowStockIngredients.isEmpty())
        {
            String message = "Low stock alert: " + String.join(", ", lowStockIngredients);

            notification_Service.sendNotificationToManager(message);
            return true;
        }

        return false;
    }


    public void addIngredient(Ingredient ingredient)
    {
        ingredients.add(ingredient);

        System.out.println("Ingredient " + ingredient.getName() + " added to inventory.");
    }





    
    public boolean islowstock()
    {
        for (Ingredient ingredient : ingredients)
        {
            if (ingredient.getQuantity() < ingredient.getMinQuantity())

            {
                return true;
            }
        }
        return false;  
    }


    private void checkLevelOfIngredient(Ingredient ingredient)
    {
        if (ingredient.getQuantity() < ingredient.getMinQuantity())
        {
            notification_Service.sendNotificationToManager("Low stock alert: " + ingredient.getName() + " is below the threshold -->>> Remaining: " + ingredient.getQuantity());
        }
    }

    public void checkInventory(String ingredientName, int quantityThreshold) {
        boolean found = false;

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
                found = true;
                if (ingredient.getQuantity() <= quantityThreshold) {
                    System.out.println("Alert: The current quantity of " + ingredientName + " (" + ingredient.getQuantity() + ") is less than or equal to the required threshold (" + quantityThreshold + ").");
                } else {
                    System.out.println("The quantity of " + ingredientName + " is sufficient (" + ingredient.getQuantity() + ").");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("The ingredients " + ingredientName + " is not found in the inventory.");
        }
    }


}
