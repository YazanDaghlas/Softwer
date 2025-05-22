Feature: Track past orders and personalized meal plans

  Scenario: Customer views past orders
    Given a customer is logged into their account
    When they navigate to the "Order History" page
    Then they should see a list of their past meal orders
    And they should be able to select a meal to reorder it


  Scenario: Chef accesses customer order history
    Given a chef is logged into the system
    When they navigate to a specific customer's profile
    Then they should see the customer's past meal orders
    And they should be able to suggest a personalized meal plan based on the order history


  Scenario: System administrator retrieves customer order history
    Given a system administrator is logged into the system
    When they request customer order data
    Then the system should provide access to stored order history
    And the administrator should be able to analyze trends and generate reports