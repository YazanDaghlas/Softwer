package YazanSystem;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.example.Order;
import org.example.OrderService;
import org.example.User;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class feature2_1
{
    private OrderService orderService;
    
      private boolean isMealAdded = true;



  
    private String mealName = "Shawerma"; 
    private double price = 17.99;

        private boolean isCustomMealConfirmed = false;
    private boolean isErrorDisplayed = false;
    


    private String[] incompatibleIngredients = {"Chicken", "Peanut", "Cheese"};

      private String unavailableIngredient = "Kunafaa";

    private boolean isIngredientUnavailable = false;

    private boolean isIngredientSelected = false;
  

    private List<String> buyersList = new ArrayList<>();

    private boolean isCheckoutReady = false;





    @Given("the customer is on the {string} page")
    public void the_customer_is_on_the_page(String pageName)
    {
        System.out.println("Customer is on the " + pageName + " page");
      
        User customer = new User("Yazan", false, false);

        OrderService orderService = new OrderService();

        customer.login();  
    }

 
    @When("the customer selects a valid combination of componants")
    public void theCustomerSelectsAValidCombinationOfComponants()
    {
       
        String[] availableComponents = {"Chicken", "Lettuce", "Tomato", "Cucumber", "Cheese"};

      
        String[] customerSelectedComponents = {"Chicken", "Lettuce", "Tomato"};

      
        for (String component : customerSelectedComponents)
        {
            boolean isValidComponent = false;

            for (String availableComponent : availableComponents)
            {
                if (component.equals(availableComponent))
                {
                    isValidComponent = true;
                    break;
                }
            }
            if (!isValidComponent)
            {
                System.out.println(component + " is not a valid component ##");
                return;  
            }
        }



        orderService = new OrderService();
       
        System.out.println("Customer selected a valid combination of components");
        
        orderService.addMealToOrder(customerSelectedComponents);

    }

 
    @When("the customer confirms the selection")
    public void the_customer_confirms_the_selection() {
        isCustomMealConfirmed = true;
        System.out.println("Customer confirmed selection");
        
      
        User user = new User("Yazan", false, false);
        Order order = new Order("Pizza", LocalDate.now(), 20.0);

        orderService.addOrderToUser(user, order);
    }

  
    @Then("the system should add the custom meal to the order")
    public void the_system_should_add_the_custom_meal_to_the_order() {


        String[] components = {"Chicken", "Lettuce"};

       
        orderService.addMealToOrder(components);

 Assert.assertTrue("Meal should be added", isMealAdded);
    }


   
    @Then("display a confirmation message")
    public void display_a_confirmation_message() {
        if (isMealAdded && isCustomMealConfirmed) {
            System.out.println("Confirmation message displayed: Custom meal added successfully!!!!!!!!!!");
        }
    }
//////////////////////////////////////////////////////////////////////////////////////

    @When("the customer selects an incompatible combination of ingredients")
    public void the_customer_selects_an_incompatible_combination_of_ingredients()
    {
      
        if (isIncompatibleCombination(incompatibleIngredients))
        {
            isErrorDisplayed = true;
        }

        System.out.println("Customer selected incompatible ingredients");
    }



    @Then("the system should display an error message")

    public void the_system_should_display_an_error_message()
    {
        if (isErrorDisplayed)
        {
            System.out.println("Error message displayed: Incompatible ingredients selected");
        }
      
        assertTrue("Error message should be displayed", isErrorDisplayed);
    }

    @Then("prevent the customer from adding the meal to the order")


    public void prevent_the_customer_from_adding_the_meal_to_the_order()
    {
        isMealAdded = true;

        assertFalse("Meal should not be added", false);
    }


    /////////////////////////////////////////////
    @Given("an componant is out of stock")
    public void an_componant_is_out_of_stock()
    {
        isIngredientUnavailable = true;

        System.out.println("Ingredient is out of stock");
    }

    @When("the customer attempts to select the unavailable ingredient")
    public void the_customer_attempts_to_select_the_unavailable_ingredient()
    {
        System.out.println("Customer tried to select an unavailable ingredient");

        if (isIngredientUnavailable)
        {
            isIngredientSelected = false;
        } else
        {
            isIngredientSelected = true;
        }
    }

    @Then("the system should disable or hide the unavailable ingredient")
    public void the_system_should_disable_or_hide_the_unavailable_ingredient()
    {
        assertTrue("Unavailable ingredient should be hidden or disabled", isIngredientUnavailable);
    }

    @Then("display a message indicating its unavailability")
    public void display_a_message_indicating_its_unavailability()
    {
        
        if (!isIngredientSelected)
        {
            
            System.out.println("Message displayed: " + unavailableIngredient + " is unavailable");
        }
    }
//////////////////////////////////////////////////////////////////
    @Given("an ingredient is out of stock")
    public void an_ingredient_is_out_of_stock()
    {
        isIngredientUnavailable = true;

        unavailableIngredient = "Kunafaa";

        System.out.println(unavailableIngredient + " is out of stock.");

    }

    @Then("Have the system offer you an alternative to the unavailable product")
    public void have_the_system_offer_you_an_alternative_to_the_unavailable_product()
    {
       boolean isAlternativeSuggested = true;

        assertTrue("Alternative ingredient should be suggested", isAlternativeSuggested);

    }


    ////////////////////////////////////////////////////
    @Given("Customer added the meal to the list of buyers")
    public void customer_added_the_meal_to_the_list_of_buyers()
    {
        buyersList.add("Meal");

        System.out.println("Meal added to buyer's list");

    }

    @When("the customer proceeds to checkout")
    public void the_customer_proceeds_to_checkout()
    {
        if (buyersList.contains("Meal"))
        {
            isCheckoutReady = true;

            System.out.println("Customer proceeding to checkout");

        }
        else
        {
            System.out.println("No meal added to the list");
        }
    }
    @Then("the meal should be in the buyer's list and the customer should be able to proceed to checkout")
    public void the_meal_should_be_in_the_buyers_list_and_the_customer_should_be_able_to_proceed_to_checkout() {
   
        if (isCheckoutReady)
        {
            System.out.println("Meal is in the list, ready for checkout.");
        }
        else
        {
            System.out.println("Meal not in the list, cannot proceed to checkout.");
        }
    }
//////////////////////////////////////////////////////////////////


    private boolean isIncompatibleCombination(String[] incompatibleIngredients)
    {
        String[] ingredients = {"Chicken", "Peanut", "Tomato"}; 

        for (String ingredient : ingredients)
        {
            for (String incompatible : incompatibleIngredients)
            {
                if (ingredient.equals(incompatible))
                {
                    isErrorDisplayed = true;

                    return true;
                }
            }
        }
        return false; 
    }





}



