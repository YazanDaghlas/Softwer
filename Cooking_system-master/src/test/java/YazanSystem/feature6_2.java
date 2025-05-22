

package YazanSystem;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import java.util.List;

import org.example.Ingredient;
import org.example.Inventory_Manager;
import org.example.NotificationService;
import org.example.OrderService;

import static org.junit.Assert.*;

public class feature6_2
    {
    
   private Inventory_Manager inventoryManager;
    private boolean notification_Received;
    
    private NotificationService notification_Service;
    private OrderService order_Service;
 

   
    @Before
    public void setup()
        {

        notification_Service = new NotificationService();
            
        order_Service = new OrderService();
            
            inventoryManager = new Inventory_Manager(notification_Service, order_Service);
    inventoryManager.addIngredient(new Ingredient("Tomato", 20, 4));
            
                       inventoryManager.addIngredient(new Ingredient("Lettuce", 8, 5));
            
        inventoryManager.addIngredient(new Ingredient("Cheese", 13, 5));

    }


    @Given("I am managing the kitchen inventory")
    public void iAmManagingTheKitchenInventory() {

    }

    @Given("the stock level of an ingredient is below the minimum threshold")
    public void theStockLevelOfAnIngredientIsBelowTheMinimumThreshold()
    {

        inventoryManager.updateStock(3, "Tomato");
    }

    @When("the stock of that ingredient falls below the threshold")
    public void theStockOfThatIngredientFallsBelowTheThreshold()
    {

        notification_Received = inventoryManager.islowstock();
    }

    @Then("I should receive a notification that the stock is low")
    public void iShouldReceiveANotificationThatTheStockIsLow()
        {
            assertTrue("Expected notification to be received, but it wasn't.", notification_Received);
    }

    @Then("the notification should include the remaining quantity of the ingredient")

    public void theNotificationShouldIncludeTheRemainingQuantityOfTheIngredient() 
        {


                assertFalse(notification_Service.get_Notifications_to_Maneger().isEmpty());
            
        assertTrue(notification_Service.get_Notifications_to_Maneger().get(0).contains("Remaining: 3"));
    }

    @Then("I should be able to reorder the ingredient")
    public void iShouldBeAbleToReorderTheIngredient()

        {
        order_Service.Record_Ingredient("Tomato");
    }

    /////////////////////////////////////////////////////////////

    @Given("multiple ingredients have stock levels below the minimum threshold")
    public void multipleIngredientsHaveStockLevelsBelowTheMinimumThreshold() 
        {

            inventoryManager.updateStock(3, "Tomato");

        inventoryManager.updateStock(2, "Lettuce");

        inventoryManager.updateStock(4, "Cheese");
    }

    @When("the stock of multiple ingredients falls below the threshold")
    public void theStockOfMultipleIngredientsFallsBelowTheThreshold() 
        {
        notification_Received = inventoryManager.areMultipleIngredientsLowStock();
    }

    @Then("I should receive a notification listing all the low-stock ingredients")
    public void iShouldReceiveANotificationListingAllTheLowStockIngredients()
    {
        
        assertFalse("Expected notifications, but got an empty list!", notification_Service.get_Notifications_to_Maneger().isEmpty());

        System.out.println("Notifications received: " + notification_Service.get_Notifications_to_Maneger());


    }

    @Then("the notification should include the remaining quantities of each ingredient")
    public void theNotificationShouldIncludeTheRemainingQuantitiesOfEachIngredient()
        {
        List<String> notifications = notification_Service.get_Notifications_to_Maneger();

               assertTrue(notifications.contains("Low stock alert: Tomato is below the threshold -->>> Remaining: 3"));
            
          assertTrue(notifications.contains("Low stock alert: Lettuce is below the threshold -->>> Remaining: 2"));
            
        assertTrue(notifications.contains("Low stock alert: Cheese is below the threshold -->>> Remaining: 4"));

    }

    @Then("I should be able to reorder all the low-stock ingredients at once")

    public void iShouldBeAbleToReorderAllTheLowStockIngredientsAtOnce()
        {

        for (String ingredient : new String[]{"Tomato", "Lettuce", "Cheese"})
            {

            order_Service.Record_Ingredient(ingredient);
        }
 
                 assertTrue(order_Service.getReorderedIngredients().contains("Tomato"));

             assertTrue(order_Service.getReorderedIngredients().contains("Lettuce"));

        assertTrue(order_Service.getReorderedIngredients().contains("Cheese"));
    }

    @And("the stock level of an ingredient is above the minimum threshold")

    public void theStockLevelOfAnIngredientIsAboveTheMinimumThreshold()
        {

        notification_Received = inventoryManager.areMultipleIngredientsLowStock();
        
    }

    @Then("I should not receive any low-stock notification")
    public void iShouldNotReceiveAnyLowStockNotification() 
        {

        assertFalse("Expected no notification, but received one.", notification_Received);
    }

        @When("the stock of that ingredient is checked")
        public void theStockOfThatIngredientIsChecked()


        {
            notification_Received = inventoryManager.islowstock();
        }
}



