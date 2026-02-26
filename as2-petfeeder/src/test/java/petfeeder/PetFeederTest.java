package petfeeder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petfeeder.exceptions.FoodStockException;
import petfeeder.exceptions.MealPlanException;

import static org.junit.jupiter.api.Assertions.*;

class PetFeederTest {

    private PetFeeder pf;
    private MealPlan p1;
    private MealPlan p2;
    private MealPlan p3;

    @BeforeEach
    void setUp() throws Exception{
        pf  = new PetFeeder();
        p1  = new MealPlan();
        p2  = new MealPlan();
        p3  = new MealPlan();




        //Set up for p1 (Basic Meal)
        p1.setName("Morning Feast");
        p1.setAmtTreats("0");
        p1.setAmtKibble("3");
        p1.setAmtWater("1");
        p1.setAmtWetFood("1");

        //Set up for p2 (No name)
        p2.setName("");
        p2.setAmtTreats("0");
        p2.setAmtKibble("3");
        p2.setAmtWater("1");
        p2.setAmtWetFood("1");

        //Set up for p3 (High energy meal)
        p3.setName("High energy meal");
        p3.setAmtTreats("999");
        p3.setAmtKibble("999");
        p3.setAmtWater("999");
        p3.setAmtWetFood("999");


    }

    /** Verifies that adding a meal plan under normal conditions returns true */
    @Test
    void testAddMealPlan_whenValid_returnsTrue() {
        boolean value = pf.addMealPlan(p1);
        assertTrue(value);
    }

    /** Verifies that adding a meal plan, adds it to the MealPlans array */
    @Test
    void testAddMealPlan_increasedMealPlan() {
        int before = countMealPlans(pf.getMealPlans());

        pf.addMealPlan(p1);

        int after = countMealPlans(pf.getMealPlans());

        assertEquals(before + 1, after);
    }

    /** Verifies that adding a meal plan fails when PetFeeder is full */
    @Test
    void testAddMealPlan_whenArrayFull_returnsFalse() {
        pf.addMealPlan(p1);
        pf.addMealPlan(p2);
        pf.addMealPlan(new MealPlan());
        pf.addMealPlan(new MealPlan());

        // Tries to add an extra meal plan
        boolean result = pf.addMealPlan(new MealPlan());

        assertFalse(result, "Should not add meal plan when array is full");
    }

    /** Verifies that the addition of an already existing meal plan returns false */
    @Test
    void testAddMealPlan_AlreadyExistingMealPlan_returnsFalse() {
        pf.addMealPlan(p1);
        boolean result = pf.addMealPlan(p1);

        assertFalse(result, "Should return false, since the meal plan already exists");
    }

    /** Verifies that null input should return false*/
    @Test
    void testAddMealPlan_inputNull_returnsFalse() {
        boolean result = pf.addMealPlan(null);

        assertFalse(result, "Should return false, since the input is not a meal plan");
    }

    /** Verifies the deletion of an existing meal plan is successful*/
    @Test
    void testDeleteMealPlan_deleteExistingMealPlan_returnsName() {
        pf.addMealPlan(p1);
        assertEquals("Morning Feast", pf.deleteMealPlan(0));
    }

    /** Verifies that when attempting to delete a meal plan that does not exist, it should return false*/
    @Test
    void testDeleteMealPlan_deleteNonExistingMealPlan_returnsNull() {
        assertNull(pf.deleteMealPlan(0));
    }

    /** Verifies that getMealPlans is decreased when deleting a meal plan*/
    @Test
    void testDeleteMealPlan_decreaseMealPlans() {
        pf.addMealPlan(p1);
        pf.addMealPlan(p2);
        int  before = countMealPlans(pf.getMealPlans());
        pf.deleteMealPlan(1);
        int after = countMealPlans(pf.getMealPlans());

        assertEquals(before -1, after);
    }

    /** Verifies that the attempt of trying to delete a negative index should return null*/
    @Test
    void testDeleteMealPlan_deleteNegativeIndex_returnsNull() {
        assertNull(pf.deleteMealPlan(-1));
    }

    /** Verifies that the attempt of trying to delete an index that is greater than the length of
     * the array should return null*/
    @Test
    void testDeleteMealPlan_deleteOutOfBoundsIndex_returnsNull() {
        assertNull(pf.deleteMealPlan(pf.getMealPlans().length + 1));
    }

    /** Verifies that the first deletion of a valid index returns the name of the meal plan, and the second deletion
        should return null to confirm that it has been deleted.
     */
    @Test
    void testDeleteMealPlan_deleteSameIndexTwice_returnsNull() {

        pf.addMealPlan(p1);
        assertEquals("Morning Feast", pf.deleteMealPlan(0));

        assertNull(pf.deleteMealPlan(0));
    }

    /** Verifies that a valid replacement of an existing meal plan returns the name of the replaced Meal Plan*/
    @Test
    void testEditMealPlan_whenValid_returnsName() {
        pf.addMealPlan(p1);
        assertEquals("Morning Feast", pf.editMealPlan(0, p2));
    }

    /** Verifies that when attempting to replace a Meal plan that does not exist, it should reutn null*/
    @Test
    void testEditMealPlan_replaceInvalidMealPlan_returnsNull(){
        assertNull(pf.editMealPlan(0, p1));
    }


    /** Verify that attempting to replace a meal plan with null should return null*/
    @Test
    void testEditMealPlan_replaceWithNull_returnsNull(){
        pf.addMealPlan(p1);
        assertNull(pf.editMealPlan(0, null));
    }

    /** Verify that a negative index return null*/
    @Test
    void testEditMealPlan_negativeIndex_returnsNull(){
        assertNull(pf.editMealPlan(-1, p2));
    }

    /** Verify that an index that is out of bounds returns null*/
    @Test
    void testEditMealPlan_outOfBoundsIndex_returnsNull(){
        assertNull(pf.editMealPlan(pf.getMealPlans().length+1, p1));
    }


    /** Verifies that valid input for the replenish food function does not throw any exception*/
    @Test
    void testReplenishFood_validInput_noException() {
        assertDoesNotThrow(() ->
                pf.replenishFood("1","1","1","1")
        );
    }

    /** Verifies that the FoodStockException is thrown when adding a negative value*/
    @Test
    void testReplenishFood_invalidInput_throwsException() {
        assertThrows(FoodStockException.class, () ->
                pf.replenishFood("-1","1","1","1")
        );
    }


    /** Verifies that the function returns a String that is not null*/
    @Test
    void testCheckFoodStock_returnsString() {
        String result = pf.checkFoodStock();
        assertNotNull(result);
    }


    /** Verifies that a change in the food stock is shown in the checkFoodStock return values*/
    @Test
    void testCheckFoodStock_afterReplenish_changes() throws FoodStockException {
        String before = pf.checkFoodStock();

        pf.replenishFood("1","1","1","1");

        String after = pf.checkFoodStock();

        assertNotEquals(before, after);
    }

    /** Verify that a valid meal plan is dispensed correctly */
    @Test
    void testDispenseMeal_valid_returnsTrue(){
        pf.addMealPlan(p1);
        assertTrue(pf.dispenseMeal(0));
    }


    /** Verifies that a high energy meal that exceeds energy buget returns false*/
    @Test
    void testDispenseMeal_overEnergyBudget_returnsFalse(){
        pf.addMealPlan(p3);     //High energy meal
        assertFalse(pf.dispenseMeal(0));
    }

    /** Verifies that a non-existing meal plan returns false*/
    @Test
    void testDispenseMeal_nonExistingMealPlan_returnsFalse(){
        assertFalse(pf.dispenseMeal(0));
    }

    /** Verifies that a negative index should return false*/
    @Test
    void testDispenseMeal_negativeIndexInput_returnsFalse(){
        assertFalse(pf.dispenseMeal(-1));
    }


    /** Verifies that an index that is out of bounds of the array returns false*/
    @Test
    void testDispenseMeal_outOfBoundsIndexInput_returnsFalse(){
        assertFalse(pf.dispenseMeal(pf.getMealPlans().length+1));
    }

    /** Verifies that when the meal plan has an ingredient that exceeds the Stock
     * of that ingredient, it should return false.
     */
    @Test
    void testDispenseMeal_insufficientIngredients_returnsFalse()throws MealPlanException {
        MealPlan exceedStockMealPlan = new MealPlan();
        exceedStockMealPlan.setAmtKibble("16");
        pf.addMealPlan(exceedStockMealPlan);

        assertFalse(pf.dispenseMeal(0));
    }

    /** Verifies that getMealPlans does not return null*/
    @Test
    void testGetMealPlans_returnsArray() {
        assertNotNull(pf.getMealPlans());
    }


    /** Verifies that the addition of two meal plans exists in the pf.getMealPlans array*/
    @Test
    void testGetMealPlans_reflectsAddedMealPlans() {
        pf.addMealPlan(p1);
        pf.addMealPlan(p2);

        MealPlan[] plans = pf.getMealPlans();

        assertEquals(p1, plans[0]);
        assertEquals(p2, plans[1]);
    }


    /** Verifies that the function returns the hard-coded value 500 that is the energy limit*/
    @Test
    void testGetEnergyLimit_returnsInt() {
        assertEquals(500, pf.getEnergyLimit());
    }

    /** Verifies that the energy limit and remaining energy budget is the same before any reduction of remaining
     * energy budget
     */
    @Test
    void testRemainingEnergyBudget_initialEqualsLimit() {
        assertEquals(pf.getEnergyLimit(), pf.getRemainingEnergyBudget());
    }

    /** Verifies that the remaining energy budget is decreased when dispensing a meal*/
    @Test
    void testRemainingEnergyBudget_afterReplenish() {
        pf.addMealPlan(p1);
        int before = pf.getRemainingEnergyBudget();
        pf.dispenseMeal(0);
        int after = pf.getRemainingEnergyBudget();
        assertNotEquals(before, after);
    }


    private int countMealPlans(MealPlan[] arr) {
        int count = 0;
        for (MealPlan m : arr) {
            if (m != null) count++;
        }
        return count;
    }
}