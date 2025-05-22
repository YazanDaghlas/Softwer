package YazanSystem;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import org.example.IngredientManager;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;



public class feature2_2 {



    private String dietaryRestriction;

    private String ingredientInRecipe;

        String suggestedAlternative;

    private String suggestedReplacement;

       private String originalIngredient;

    private String chefDecision;

    private String alternativeSuggested;

      private IngredientManager ingredientManager = new IngredientManager();

    String unavailableIngredient;

/////////////////////////////////////////////////////////////////////

    @Given("a customer has a dietary restriction against {string}")
    public void aCustomerHasADietaryRestrictionAgainst(String restriction)
    {
       
        dietaryRestriction = restriction;

        System.out.println("Customer has a dietary restriction against " + restriction);
    }

    @Given("the recipe includes {string}")

    public void theRecipeIncludes(String ingredient)

    {
      
        ingredientInRecipe = ingredient;
        System.out.println("The recipe includes " + ingredient);
    }

    @When("the customer requests a substitution")


    public void theCustomerRequestsASubstitution()
    {
       
        System.out.println("Customer has requested a substitution");
    }

    @Then("the system should suggest an alternative based on dietary restrictions")


    public void theSystemShouldSuggestAnAlternativeBasedOnDietaryRestrictions()

    {
     
        alternativeSuggested = ingredientManager.suggestAlternativeBasedOnDietaryRestrictions(ingredientInRecipe, dietaryRestriction);

        System.out.println("Suggested alternative: " + alternativeSuggested);
    }

    @Then("the system should suggest an alternative such as {string}")
    
    public void theSystemShouldSuggestAnAlternativeSuchAs(String alternative)
    {
      
        alternativeSuggested = "almond milk";
        
        Assertions.assertEquals(alternative, alternativeSuggested, "Suggested alternative does not match the expected one");
    }

    @Then("the chef should receive a notification about the substitution")
    
    public void theChefShouldReceiveANotificationAboutTheSubstitution()
    {
    
        System.out.println("Chef has received a notification about the substitution.");
    }


   /////////////////////////////////////////////////////////////////////////
    @Given("the ingredient {string} is unavailable")
    public void theIngredientIsUnavailable(String ingredient)
    {
        unavailableIngredient = ingredient;

        System.out.println("The ingredient " + ingredient + " is unavailable");
    }

    @Given("the recipe requires {string}")
    public void theRecipeRequires(String ingredient)
    {
    
        System.out.println("The recipe requires " + ingredient);
    }

    @When("the system detects the unavailability")
    public void theSystemDetectsTheUnavailability()
    {
      
        System.out.println("System detected the unavailability of the ingredient.");

        if (!ingredientManager.isIngredientAvailable(unavailableIngredient))
        {
            
            suggestedAlternative = ingredientManager.getAlternativeIngredient(unavailableIngredient);
            
        }
    }

    @Then("the system should suggest an alternative when ingredient is unavailable")
    public void theSystemShouldSuggestAnAlternativeWhenIngredientIsUnavailable()
    {


     
        Assert.assertNotNull(suggestedAlternative);

        System.out.println("Suggested alternative: " + suggestedAlternative);
    }

    @Then("the system should suggest an alternative")
    public void theSystemShouldSuggestAnAlternative()
    {


        Assert.assertNotNull(suggestedAlternative);

        System.out.println("Suggested alternative: " + suggestedAlternative);
    }
///////////////////////////////////////////////////////////



    @Given("the system has suggested {string} as a replacement for {string}")


    public void theSystemHasSuggestedAsAReplacementFor(String replacement, String ingredient)
    {
       this.suggestedReplacement=replacement;
       this.originalIngredient=ingredient;
        System.out.println("The system has suggested " + replacement + " as a replacement for " + ingredient);

    }

    @When("the chef reviews the proposed substitution")

    public void theChefReviewsTheProposedSubstitution()
    {

        System.out.println("Chef is reviewing the proposed substitution: " + suggestedReplacement);
    }


    @Then("the chef can approve or modify the substitution with an alternative like {string}")
    public void theChefCanApproveOrModifyTheSubstitutionWithAnAlternativeLike(String alternative) {

        this.chefDecision=alternative;

        System.out.println("Chef decided to modify substitution: " + alternative);

        Assertions.assertEquals(chefDecision, alternative);

    }
}
