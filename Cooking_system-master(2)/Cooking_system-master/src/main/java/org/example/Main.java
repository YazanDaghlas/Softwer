package org.example;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {


        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            OrderService orderService = new OrderService();
            IngredientManager ingredientManager = new IngredientManager();
            Inventory_Manager inventoryManager = new Inventory_Manager();
            NotificationService notificationService = new NotificationService();
            INPUT_TEST inputTest = new INPUT_TEST();

            System.out.println("Welcome to the Food Order Management System");
            System.out.print("Enter your username: ");
            String username = input.nextLine();

            System.out.print("Are you a manager? (yes/no): ");
            boolean isAdmin = input.nextLine().equalsIgnoreCase("yes");

            System.out.print("Are you a chef? (yes/no): ");
            boolean isChef = input.nextLine().equalsIgnoreCase("yes");

            User user = new User(username, isAdmin, isChef);
            user.login();

            boolean running = true;
            while (running) {
                System.out.println("\nMain Menu:");
                if (isAdmin) {
                    System.out.println("1. Reorder Ingredient");
                    System.out.println("6. Check Inventory");
                }
                if (isChef) {
                    System.out.println("2. View Order History");
                    System.out.println("7. Review Ingredients as Chef");
                }
                if (!isAdmin && !isChef) {
                    System.out.println("3. Create New Order");
                    System.out.println("4. View Order History");
                    System.out.println("5. View Notifications");
                    System.out.println("8. Login via INPUT_TEST");
                    System.out.println("9. Send Suggestion");
                }
                System.out.println("10. Process Ingredients Based on Restrictions");
                System.out.println("11. Send Delivery Notification");
                System.out.println("12. Send Low Stock Notification");
                System.out.println("0. Exit");

                System.out.print("Enter your option: ");
                String option = input.nextLine();

                switch (option) {
                    case "1":
                        if (isAdmin) {
                            System.out.print("Enter ingredient name to reorder: ");
                            String ingredient = input.nextLine();
                            orderService.Record_Ingredient(ingredient);
                            List<String> reorderList = orderService.getReorderedIngredients();
                            System.out.println("Ingredients reordered:");
                            for (String ing : reorderList) {
                                System.out.println("- " + ing);
                            }
                        } else {
                            System.out.println("This option is not available for you.");
                        }
                        break;

                    case "2":
                        if (isChef) {
                            List<Order> history = user.getOrderHistory();
                            if (history.isEmpty()) {
                                System.out.println("No orders found.");
                            } else {
                                System.out.println("Order History:");
                                for (Order o : history) {
                                    System.out.println(o);
                                }
                            }
                        } else {
                            System.out.println("This option is not available for you.");
                        }
                        break;

                    case "3":
                        if (!isAdmin && !isChef) {
                            System.out.println("Creating New Order");
                            System.out.println("Available ingredients: Chicken, Lettuce, Tomato, Cucumber, Cheese");
                            System.out.print("Enter ingredients separated by commas: ");
                            String[] components = input.nextLine().split(",");

                            orderService.addMealToOrder(components);
                            Order baseOrder = orderService.getOrder();

                            if (baseOrder != null) {
                                Customer customer = new Customer(user.getUsername(), "Amman");
                                Order fullOrder = new Order(customer, LocalDateTime.now(), "Custom Meal");
                                for (String c : baseOrder.getComponents()) {
                                    fullOrder.addComponent(c.trim());
                                }

                                user.addOrder(fullOrder);
                                user.Add_Notification("Order created successfully.");
                                System.out.println("Order placed successfully.");
                            } else {
                                System.out.println("Failed to create the order.");
                            }
                        } else {
                            System.out.println("This option is not available for you.");
                        }
                        break;

                    case "4":
                        if (!isAdmin && !isChef) {
                            List<Order> orderHistory = user.getOrderHistory();
                            if (orderHistory.isEmpty()) {
                                System.out.println("No saved orders.");
                            } else {
                                System.out.println("Your previous orders:");
                                for (Order o : orderHistory) {
                                    System.out.println(o);
                                }
                            }
                        } else {
                            System.out.println("This option is not available for you.");
                        }
                        break;

                    case "5":
                        if (!isAdmin && !isChef) {
                            String notif = user.get_Notification_List();
                            if (notif == null || notif.isEmpty()) {
                                System.out.println("No notifications currently.");
                            } else {
                                System.out.println("Your notifications:\n- " + notif);
                            }
                        } else {
                            System.out.println("This option is not available for you.");
                        }
                        break;

                    case "6":
                        if (isAdmin) {
                            inventoryManager.checkInventory("Tomato", 2);
                        } else {
                            System.out.println("This option is not available for you.");
                        }
                        break;

                    case "7":
                        if (isChef) {
                            ingredientManager.reviewIngredientsByChef(Arrays.asList("Salt", "Pepper"));
                            System.out.println("Ingredients reviewed.");
                        } else {
                            System.out.println("This option is not available for you.");
                        }
                        break;

                    case "8":
                        if (!isAdmin && !isChef) {
                            inputTest.login(user.getUsername(), "1234");
                        } else {
                            System.out.println("This option is not available for you.");
                        }
                        break;

                    case "9":
                        if (!isAdmin && !isChef) {
                            inputTest.sendSuggestions("Add more vegetarian meals.");
                        } else {
                            System.out.println("This option is not available for you.");
                        }
                        break;

                    case "10":
                        ingredientManager.processIngredientsBasedOnRestrictions("Cucumber", Arrays.asList("Vegetarian", "NoDairy"));
                        System.out.println("Ingredients processed based on restrictions.");
                        break;

                    case "11":
                        notificationService.sendDeliveryNotification(user.getUsername(), "Custom Meal");
                        break;

                    case "12":
                        notificationService.sendLowStockNotification("Lettuce");
                        break;

                    case "0":
                        running = false;
                        System.out.println("Logged out. Thank you for using the system.");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

            user.logout();
            input.close();
        }
    }


