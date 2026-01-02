package petfeeder;

import petfeeder.exceptions.FoodStockException;

public class PetFeeder {
    
    /** Array of meal plans in pet feeder */
    private static MealPlanBook mealPlanBook;
    /** Food container (inventory) of the pet feeder */
    private static FoodContainer foodContainer;
    
    /**
     * Constructor for the pet feeder
     */
    public PetFeeder() {
        mealPlanBook = new MealPlanBook();
        foodContainer = new FoodContainer();
    }
    
    /**
     * Returns true if the meal plan is added to the
     * list of meal plans in the PetFeeder and false
     * otherwise.
     * @param m The meal plan to add.
     * @return boolean
     */
    public boolean addMealPlan(MealPlan m) {
        return mealPlanBook.addMealPlan(m);
    }
    
    /**
     * Returns the name of the successfully deleted meal plan
     * or null if the meal plan cannot be deleted.
     * @param mealPlanToDelete The index of the meal plan to delete.
     * @return String
     */
    public String deleteMealPlan(int mealPlanToDelete) {
        return mealPlanBook.deleteMealPlan(mealPlanToDelete);
    }
    
    /**
     * Returns the name of the successfully edited meal plan
     * or null if the meal plan cannot be edited.
     * @param mealPlanToEdit The index of the meal plan to edit.
     * @param m The new meal plan object.
     * @return String
     */
    public String editMealPlan(int mealPlanToEdit, MealPlan m) {
        return mealPlanBook.editMealPlan(mealPlanToEdit, m);
    }
    
    /**
     * Returns true if food stock was successfully replenished.
     * @param amtKibble The amount of kibble to add.
     * @param amtWater The amount of water to add.
     * @param amtWetFood The amount of wet food to add.
     * @param amtTreats The amount of treats to add.
     * @throws FoodStockException if inputs are invalid.
     */
    public synchronized void replenishFood(String amtKibble, String amtWater, String amtWetFood, String amtTreats) throws FoodStockException {
        foodContainer.addKibble(amtKibble);
        foodContainer.addWater(amtWater);
        // This will call the method with the seeded bug
        foodContainer.addWetFood(amtWetFood);
        foodContainer.addTreats(amtTreats);
    }
    
    /**
     * Returns the food stock status of the pet feeder.
     * @return String
     */
    public synchronized String checkFoodStock() {
        return foodContainer.toString();
    }
    
    /**
     * Returns the remaining energy balance after dispensing a meal, or
     * the initial energy provided if the meal cannot be dispensed.
     * @param mealPlanToPurchase The index of the meal plan selected by the user.
     * @param energyProvided The amount of energy quota provided by the user system.
     * @return int The remaining energy balance.
     */
    public synchronized int dispenseMeal(int mealPlanToPurchase, int energyProvided) {
        int change = 0;
        
        if (getMealPlans()[mealPlanToPurchase] == null) {
            change = energyProvided;
        } else if (getMealPlans()[mealPlanToPurchase].getEnergyCost() <= energyProvided) {
            if (foodContainer.useIngredients(getMealPlans()[mealPlanToPurchase])) {
                change = energyProvided - getMealPlans()[mealPlanToPurchase].getEnergyCost();
            } else {
                change = energyProvided;
            }
        } else {
            change = energyProvided;
        }
        
        return change;
    }

    /**
     * Returns the list of MealPlans in the MealPlanBook.
     * @return MealPlan[]
     */
    public synchronized MealPlan[] getMealPlans() {
        return mealPlanBook.getMealPlans();
    }
}
