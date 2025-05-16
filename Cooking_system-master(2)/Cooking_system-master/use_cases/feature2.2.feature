Feature: Ingredient Substitution Based on Dietary Restrictions

  Scenario: Suggesting an alternative for a restricted ingredient
    Given a customer has a dietary restriction against "dairy products"
    And the recipe includes "milk"
    When the customer requests a substitution
    Then the system should suggest an alternative such as "almond milk"
    And the chef should receive a notification about the substitution

  Scenario: Suggesting an alternative when an ingredient is unavailable
    Given the ingredient "butter" is unavailable
    And the recipe requires "butter"
    When the system detects the unavailability
    Then the system should suggest an alternative
    And the chef should receive a notification about the substitution

  Scenario: Chef approval for ingredient substitution
    Given the system has suggested "coconut oil" as a replacement for "butter"
    When the chef reviews the proposed substitution
    Then the chef can approve or modify the substitution with an alternative like "olive oil"
