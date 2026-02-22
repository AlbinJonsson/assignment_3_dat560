package petfeeder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetFeederTest {

    private PetFeeder pf;
    private MealPlan p1;
    private MealPlan p2;

    @BeforeEach
    void setUp() throws Exception{
        pf  = new PetFeeder();
        p1  = new MealPlan();
        p2  = new MealPlan();


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
    void testDeleteMealPlan_deleteExistingMealPlan_returnsTrue() {
        pf.addMealPlan(p1);
        assertEquals("Morning Feast", pf.deleteMealPlan(0));
    }

    /** Verifies that when attempting to delete a meal plan that does not exist, it should return false*/
    @Test
    void testDeleteMealPlan_deleteNonExistingMealPlan_returnsFalse() {
        assertEquals(null, pf.deleteMealPlan(0));
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

    @Test
    void editMealPlan() {
    }

    @Test
    void replenishFood() {
    }

    @Test
    void checkFoodStock() {
    }

    @Test
    void dispenseMeal() {
    }

    @Test
    void getMealPlans() {
    }

    @Test
    void getEnergyLimit() {
    }

    @Test
    void getRemainingEnergyBudget() {
    }


    private int countMealPlans(MealPlan[] arr) {
        int count = 0;
        for (MealPlan m : arr) {
            if (m != null) count++;
        }
        return count;
    }
}