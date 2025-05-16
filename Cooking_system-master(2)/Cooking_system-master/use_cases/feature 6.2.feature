Feature: Low Stock Notification
  As a kitchen manager
  I want to receive notifications when stock falls below a minimum threshold
  So that I can reorder ingredients before running out

  Scenario: Notify when stock falls below the threshold
    Given I am managing the kitchen inventory
    And the stock level of an ingredient is below the minimum threshold
    When the stock of that ingredient falls below the threshold
    Then I should receive a notification that the stock is low
    And the notification should include the remaining quantity of the ingredient
    And I should be able to reorder the ingredient

  Scenario: No notification when stock is above the threshold
    Given I am managing the kitchen inventory
    And the stock level of an ingredient is above the minimum threshold
    When the stock of that ingredient is checked
    Then I should not receive any low-stock notification

  Scenario: Multiple low-stock ingredients
    Given I am managing the kitchen inventory
    And multiple ingredients have stock levels below the minimum threshold
    When the stock of multiple ingredients falls below the threshold
    Then I should receive a notification listing all the low-stock ingredients
    And the notification should include the remaining quantities of each ingredient
    And I should be able to reorder all the low-stock ingredients at once
