package petfeeder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MealPlanBookTest {

    private MealPlanBook book;

    @BeforeEach
    void setup() {
        book = new MealPlanBook();
    }

    @Test
    void addMealPlan_success() {
        MealPlan m = new MealPlan();
        boolean result = book.addMealPlan(m);
        assertTrue(result, "Meal plan should be added successfully");
    }

    @Test
    void addMealPlan_duplicate_shouldFail() {
        MealPlan m = new MealPlan();
        book.addMealPlan(m);
        boolean result = book.addMealPlan(m);
        assertFalse(result, "Duplicate meal plan should not be added");
    }

    @Test
    void addMealPlan_whenFull_shouldFail() {
        for (int i = 0; i < 4; i++) {
            book.addMealPlan(new MealPlan());
        }
        boolean result = book.addMealPlan(new MealPlan());
        assertFalse(result, "Should not add meal plan when book is full");
    }


    @Test
    void deleteMealPlan_success() {
        MealPlan m = new MealPlan();
        book.addMealPlan(m);

        String deletedName = book.deleteMealPlan(0);
        assertNotNull(deletedName, "Deleted meal plan name should not be null");
    }

    @Test
    void deleteMealPlan_emptySlot_shouldReturnNull() {
        String deletedName = book.deleteMealPlan(0);
        assertNull(deletedName, "Deleting empty slot should return null");
    }


    @Test
    void editMealPlan_success() {
        MealPlan oldMeal = new MealPlan();
        book.addMealPlan(oldMeal);

        MealPlan newMeal = new MealPlan();
        String originalName = book.editMealPlan(0, newMeal);

        assertNotNull(originalName, "Editing existing meal plan should return original name");
    }

    @Test
    void editMealPlan_emptySlot_shouldReturnNull() {
        MealPlan newMeal = new MealPlan();
        String result = book.editMealPlan(0, newMeal);

        assertNull(result, "Editing empty slot should return null");
    }


    @Test
    void getMealPlans_shouldReturnArrayOfSize4() {
        MealPlan[] plans = book.getMealPlans();
        assertEquals(4, plans.length, "MealPlanBook should hold 4 meal plans");
    }
}