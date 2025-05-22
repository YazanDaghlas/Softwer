package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientManager
{
   

    private static final Map<String, String> alternativeIngredients = new HashMap<>();

    public static final String FLAXSEED = "Flaxseed";

    static
    {
    
        alternativeIngredients.put("Tomato", "Red Pepper");

           alternativeIngredients.put("Milk", "Almond Milk");

        alternativeIngredients.put("Egg", FLAXSEED);

        alternativeIngredients.put("Wheat", "Rice Flour");
    }


    public String getAlternativeIngredient(String unavailableIngredient)
    {
    
        if (alternativeIngredients.containsKey(unavailableIngredient))
        {

            return alternativeIngredients.get(unavailableIngredient);
        }

        return "No alternative available";
    }

    
    public boolean isAlternativeSuggested(String unavailableIngredient)
    {

        return alternativeIngredients.containsKey(unavailableIngredient);
    }

 
    public String suggestAlternativeBasedOnDietaryRestrictions(String ingredient, String dietaryRestriction)
    {
     
        if (ingredient.equals("Milk") && dietaryRestriction.equals("Lactose Free"))
        {
            return "Almond Milk";

        }
          else if (ingredient.equals("Egg") && dietaryRestriction.equals("Vegan"))
        {
            return FLAXSEED;
        }
      
        return getAlternativeIngredient(ingredient);
    }





    public String reviewAndModifyAlternative(String ingredient, String suggestedAlternative, String modifiedAlternative)
    {
        if (alternativeIngredients.containsKey(ingredient))
        {

            if (suggestedAlternative.equals(alternativeIngredients.get(ingredient)))
            {

                return "Chef approved the suggested alternative: " + suggestedAlternative;
            }

            else
            {

                alternativeIngredients.put(ingredient, modifiedAlternative);

                return "Chef modified the alternative for " + ingredient + " to: " + modifiedAlternative;
            }
        }

        return "No alternative available to review.";
    }


    public static void main(String[] args) {
        IngredientManager manager = new IngredientManager();
      
        System.out.println(manager.getAlternativeIngredient("Tomato"));
        System.out.println(manager.getAlternativeIngredient("Milk"));

      
        System.out.println(manager.isAlternativeSuggested("Tomato"));
        System.out.println(manager.isAlternativeSuggested("Kaffee")); 

       
        System.out.println(manager.suggestAlternativeBasedOnDietaryRestrictions("Milk", "Lactose Free"));
        System.out.println(manager.suggestAlternativeBasedOnDietaryRestrictions("Egg", "Vegan"));

    
        System.out.println(manager.reviewAndModifyAlternative("Tomato", "Red Pepper", "Cucumber"));
        System.out.println(manager.reviewAndModifyAlternative("Egg", FLAXSEED, "Chia Seeds"));
    }

    public boolean isIngredientAvailable(String unavailableIngredient)
    {
      
        return alternativeIngredients.containsKey(unavailableIngredient);
    }

   

}
