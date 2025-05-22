Feature: Allow customers to create custom meal requests

  Scenario: Successfully create a custom meal
    Given the customer is on the "Create Custom Meal" page
    When the customer selects a valid combination of componants
    And the customer confirms the selection
    Then the system should add the custom meal to the order
    And display a confirmation message

  Scenario: Prevent selection of incompatible componants
    Given the customer is on the "Create Custom Meal" page
    When the customer selects an incompatible combination of ingredients
    Then the system should display an error message
    And prevent the customer from adding the meal to the order

  Scenario: Prevent selection of unavailable componants
    Given the customer is on the "Create Custom Meal" page
    And an componant is out of stock
    When the customer attempts to select the unavailable ingredient
    Then the system should disable or hide the unavailable ingredient
    And display a message indicating its unavailability

  Scenario: Suggest alternative ingredients when an ingredient is unavailable
    Given the customer is on the "Create Custom Meal" page
    And an ingredient is out of stock
    When the customer attempts to select the unavailable ingredient
    Then Have the system offer you an alternative to the unavailable product

  Scenario:Review meal details before confirming order and payment
    Given Customer added the meal to the list of buyers
    When the customer proceeds to checkout
    Then the meal should be in the buyer's list and the customer should be able to proceed to checkout
