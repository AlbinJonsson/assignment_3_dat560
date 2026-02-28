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

    // -----------------------------
    // ADD MEAL PLAN TESTS
    // -----------------------------

    @Test
    void testAddMealPlan_validInput_returnsTrue() {
        MealPlan m = new MealPlan();
        boolean result = book.addMealPlan(m);

        assertTrue(result);
    }

    @Test
    void testAddMealPlan_increasesMealCount() {
        int countBefore = countNonNull(book.getMealPlans());

        book.addMealPlan(new MealPlan());

        int countAfter = countNonNull(book.getMealPlans());

        assertEquals(countBefore + 1, countAfter);
    }

    @Test
    void testAddMealPlan_whenArrayFull_returnsFalse() {
        for (int i = 0; i < 4; i++) {
            book.addMealPlan(new MealPlan());
        }

        boolean result = book.addMealPlan(new MealPlan());
        assertFalse(result);
    }

    @Test
    void testAddMealPlan_duplicate_returnsFalse() {
        MealPlan m = new MealPlan();
        book.addMealPlan(m);

        boolean result = book.addMealPlan(m);
        assertFalse(result);
    }

    @Test
    void testAddMealPlan_nullInput_returnsFalse() {
        boolean result = book.addMealPlan(null);
        assertFalse(result);
    }

    // -----------------------------
    // DELETE TESTS
    // -----------------------------

    @Test
    void testDeleteMealPlan_validIndex_returnsName() {
        MealPlan m = new MealPlan();
        book.addMealPlan(m);

        String name = book.deleteMealPlan(0);

        assertNotNull(name);
    }

    @Test
    void testDeleteMealPlan_emptySlot_returnsNull() {
        String name = book.deleteMealPlan(0);
        assertNull(name);
    }

    // -----------------------------
    // EDIT TESTS
    // -----------------------------

    @Test
    void testEditMealPlan_validIndex_returnsOriginalName() {
        MealPlan oldMeal = new MealPlan();
        book.addMealPlan(oldMeal);

        MealPlan newMeal = new MealPlan();
        String name = book.editMealPlan(0, newMeal);

        assertNotNull(name);
    }

    @Test
    void testEditMealPlan_emptySlot_returnsNull() {
        MealPlan newMeal = new MealPlan();
        String result = book.editMealPlan(0, newMeal);

        assertNull(result);
    }

    // -----------------------------
    // HELPER METHOD
    // -----------------------------

    private int countNonNull(MealPlan[] plans) {
        int count = 0;
        for (MealPlan p : plans) {
            if (p != null) count++;
        }
        return count;
    }
}