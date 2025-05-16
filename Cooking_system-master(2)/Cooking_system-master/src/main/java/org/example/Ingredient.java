package org.example;

public class Ingredient
{
    private String name;
    private int quantity;
    private int minQuantity;

    public Ingredient(String name, int quantity, int minQuantity)
    {
        this.name = name;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
    }

    public String getName() {

        return name;
    }

    public int getQuantity() {

        return quantity;
    }

    public int getMinQuantity() {

        return minQuantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }


    public boolean isLowStock() {
        return quantity < minQuantity;
    }

}
