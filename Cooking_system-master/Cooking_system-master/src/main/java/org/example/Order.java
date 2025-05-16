package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private LocalDateTime deliveryTime;

    private List<String> components;
    private String mealName;
    private LocalDate orderDate;
    private double price;

    private Customer customer;


    public Order(Customer customer, LocalDateTime deliveryTime) {

                     this.customer = customer;
        this.deliveryTime = deliveryTime;
    }

    public Customer getCustomer()
    {

        return customer;
    }

    public LocalDateTime getDeliveryTime()

    {

        return deliveryTime;
    }

 
    public Order(String mealName, LocalDate orderDate, double price)

    {
           this.mealName = mealName;
         this.orderDate = orderDate;
        this.price = price;
        this.components = new ArrayList<>(); 
    }

  
    public String getMealName()

    {
        return mealName;
    }

   
    public LocalDate getOrderDate()

    {
        return orderDate;
    }


    public double getPrice()
    {
        return price;
    }

 
    public void addComponent(String component)
    {
    
        if (isValidComponent(component))
        {
            components.add(component);
            System.out.println(component + " has been added to the meal");
        }
        else
        {
            System.out.println(component + " is not a valid component");
        }
    }


    private boolean isValidComponent(String component)
    {
      
        List<String> availableComponents = List.of("Chicken", "Lettuce", "Tomato", "Cucumber", "Cheese");

        return availableComponents.contains(component);
    }


    public List<String> getComponents()
    {
        return components;
    }

    public void setDeliveryTime(LocalDateTime newDeliveryTime)

    {
        this.deliveryTime=newDeliveryTime;
    }



    @Override
    public String toString()
    {
        String componentsStr = String.join(", ", components); 
        return "Order{" +
                "mealName='" + mealName + '\'' +
                ", orderDate=" + orderDate +
                ", price=" + price +
                ", components=" + componentsStr +
                '}';
    }




}
