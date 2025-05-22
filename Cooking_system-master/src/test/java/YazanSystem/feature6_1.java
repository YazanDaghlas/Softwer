package YazanSystem;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import java.time.LocalDateTime;

import org.example.Customer;
import org.example.NotificationService;
import org.example.Order;
import org.example.User;
import org.junit.Assert;

import static org.junit.Assert.fail;

public class feature6_1
{
  private Order order ;

    private User chef;
    
    private Customer customer;

    private NotificationService notificationService;
    
    private boolean notificationSent;
  
    private LocalDateTime newDeliveryTime;

//////////////////////////////////////////////////
    @Given("a customer has an upcoming meal delivery scheduled")
    
    public void aCustomerHasAnUpcomingMealDeliveryScheduled()
    {

     customer=new Customer("yazan Daghlas","Burqa_Nablus");
        
     order=new Order(customer, LocalDateTime.now().plusMinutes(20));
        
        notificationService=new NotificationService();
        
        notificationService.scheduledDeliveries(order);

    }

    @When("the delivery time is approaching")
    
    public void theDeliveryTimeIsApproaching()
    {

        
        notificationSent = notificationService.checkAndSendNotifications();

    }
    @Then("the system should send a reminder notification to the customer")
    
    public void theSystemShouldSendAReminderNotificationToTheCustomer()
    {
        
    Assert.assertTrue("The notification should be sent to the customer",notificationSent);
    }
///////////////////////////////////////////////////////

    @Given("a chef has a scheduled cooking task")
    
    public void aChefHasAScheduledCookingTask()
    {
        chef=new User("Basill",false,true);
        
        notificationService=new NotificationService();

    }
    @When("the task time is approaching")
    
    public void theTaskTimeIsApproaching() 
    {
        notificationService.sendNotification(chef,"Time to start cooking");
    }
    
    @Then("the system should send a notification to the chef to prepare the meal on time")
    public void theSystemShouldSendANotificationToTheChefToPrepareTheMealOnTime()
    {

        Assert.assertTrue("Chef should receive a notification.",chef.hasNotification("Time to start cooking"));

    }
///////////////////////////////////////////////////////////

    @Given("a customer's meal delivery is delayed")
    public void aCustomerSMealDeliveryIsDelayed()

    {
        customer = new Customer("Yazan Daghlas", "Burqa_Nablus");
        
        LocalDateTime originalDeliveryTime = LocalDateTime.now().plusMinutes(20);
        
        order = new Order(customer, originalDeliveryTime);

    
        notificationService = new NotificationService();
        
        notificationService.scheduledDeliveries(order);
    }

    @When("In case of delay in meal delivery")
    
    public void inCaseOfDelayInMealDelivery()
    {

        newDeliveryTime = LocalDateTime.now().plusMinutes(40);
        
        order.setDeliveryTime(newDeliveryTime);
        
        notificationSent = notificationService.checkAndSendNotifications();
    }

    @Then("The system will send a notification to the customer with an update about the new delivery time")

    public void theSystemWillSendANotificationToTheCustomerWithAnUpdateAboutTheNewDeliveryTime()
    {

        Assert.assertTrue("The notification should be sent to the customer :", notificationSent);

        System.out.println("Notification sent to customer with new delivery time: " + newDeliveryTime);
    }

}
