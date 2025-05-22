Feature: Notifications and Alerts

  Scenario: Sending reminders for upcoming meal deliveries to customers
    Given a customer has an upcoming meal delivery scheduled
    When the delivery time is approaching
    Then the system should send a reminder notification to the customer

  Scenario: Notifying chefs of scheduled cooking tasks
    Given a chef has a scheduled cooking task
    When the task time is approaching
    Then the system should send a notification to the chef to prepare the meal on time

  Scenario: Alert when customers are late
    Given a customer's meal delivery is delayed
    When In case of delay in meal delivery
    Then The system will send a notification to the customer with an update about the new delivery time