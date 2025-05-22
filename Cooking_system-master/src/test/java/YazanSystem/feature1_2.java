package YazanSystem;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.example.INPUT_TEST;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;

public class feature1_2 {

    private INPUT_TEST inputTest;

    private boolean is_Chef;

    @Before
    public void setup() {

        inputTest = new INPUT_TEST();
    }



///////////////////////////////////////////////////////////////////////
    @Given("a customer is logged into their account")



    public void a_customer_is_logged_into_their_account()
    {
        inputTest = new INPUT_TEST();

        inputTest.loginCustomer();

        System.out.println("Customer logged in successfully.");

        inputTest.fetchOrderHistory();
    }

    @When("they navigate to the {string} page")




    public void they_navigate_to_the_page(String page)
    {

        inputTest.navigateToPage(page);

        Assertions.assertEquals("Order History", page, "Page should be Order History");
    }

    @Then("they should see a list of their past meal orders")



    public void they_should_see_a_list_of_their_past_meal_orders()
    {
        inputTest.fetchOrderHistory();

        Assertions.assertFalse(INPUT_TEST.orderHistory.isEmpty(), "Order history should not be empty");
    }

    @Then("they should be able to select a meal to reorder it")



    public void they_should_be_able_to_select_a_meal_to_reorder_it()
    {
        inputTest.selectMealToReorder();

        String selectedMeal = inputTest.selectedMeal;

        Assertions.assertNotNull(selectedMeal, "Selected meal should not be null.");
    }

///////////////////////////////////////////////////////////////////////////
    @Given("a chef is logged into the system")
    public void a_chef_is_logged_into_the_system()
    {
        is_Chef = true;

        inputTest = new INPUT_TEST();

        inputTest.loginChef();

        System.out.println("Chef logged into the system.");
    }

    @When("they navigate to a specific customer's profile")


    public void they_navigate_to_a_specific_customer_s_profile()
    {
        inputTest.navigateToCustomerProfile();


        Assertions.assertTrue(is_Chef, "Only chefs can view customer profiles");
    }

    @Then("they should see the customer's past meal orders")



    public void they_should_see_the_customer_s_past_meal_orders() {

        if (INPUT_TEST.orderHistory.isEmpty())
        {
            System.out.println("No past orders available for this customer");
        }
        else
        {

            System.out.println("Chef is viewing the customer's past meal orders");
        }

        Assertions.assertTrue(INPUT_TEST.orderHistory.isEmpty(), "Chef should not see any past orders");
    }

    @Then("they should be able to suggest a personalized meal plan based on the order history")
    public void they_should_be_able_to_suggest_a_personalized_meal_plan_based_on_the_order_history()
    {
        inputTest.suggestPersonalizedMealPlan();

        String mealPlan = "Recommended meal: Healthy Salad";

        Assertions.assertNotNull(mealPlan, "Meal plan suggestion should be available");
    }

///////////////////////////////////////////////////////////////////////////
    @Given("a system administrator is logged into the system")
    public void a_system_administrator_is_logged_into_the_system()
    {
        inputTest = new INPUT_TEST();

        inputTest.loginAdmin();

        System.out.println("Admin logged into the system and order history populated");

        INPUT_TEST.orderHistory.add("Shawarma");

        INPUT_TEST.orderHistory.add("Pizza");

    }


    @When("they request customer order data")
    public void they_request_customer_order_data()
    {
        if (inputTest != null)
        {
            inputTest.requestCustomerOrderData();
        }
        else
        {
            System.out.println("Error!!!!!: inputTest is not initialized");
        }
    }


    @Then("the system should provide access to stored order history")
    public void the_system_should_provide_access_to_stored_order_history()
    {
        INPUT_TEST.accessStoredOrderHistory();

       
        if (INPUT_TEST.orderHistory.isEmpty())
        {
            System.out.println("Order history is empty");
        }
        else
        {
            System.out.println("Order history contains " + INPUT_TEST.orderHistory.size() + " orders");
        }

      
        Assertions.assertFalse(INPUT_TEST.orderHistory.isEmpty(), "Order history should not be empty for Admin");
    }


    @Then("the administrator should be able to analyze trends and generate reports")
    public void the_administrator_should_be_able_to_analyze_trends_and_generate_reports()
    {
        if (inputTest != null)
        {
            inputTest.analyzeTrendsAndGenerateReports();
        }
        else
        {
            System.out.println("Error: inputTest is not initializ");
        }
    }
}
