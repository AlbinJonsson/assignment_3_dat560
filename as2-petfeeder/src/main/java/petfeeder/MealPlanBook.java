package petfeeder;

public class MealPlanBook {

    /** Array of meal plans in the pet feeder */
    private MealPlan[] mealPlanArray;
    /** Number of meal plans slots */
    private final int NUM_MEALPLANS = 4;

    /**
     * Default constructor for a MealPlanBook.
     */
    public MealPlanBook() {
        mealPlanArray = new MealPlan[NUM_MEALPLANS];
    }

    /**
     * Returns the meal plan array.
     * @return MealPlan[]
     */
    public synchronized MealPlan[] getMealPlans() {
        return mealPlanArray;
    }

    /**
     * Adds a meal plan if space exists and it does not already exist.
     * @param m The meal plan to add.
     * @return true if added successfully, false otherwise.
     */
    public synchronized boolean addMealPlan(MealPlan m) {

        // FIX 1: Prevent null input
        if (m == null) {
            return false;
        }

        // Check if meal plan already exists
        for (MealPlan existing : mealPlanArray) {
            if (existing != null && existing.equals(m)) {
                return false;
            }
        }

        // Add to first empty slot
        for (int i = 0; i < mealPlanArray.length; i++) {
            if (mealPlanArray[i] == null) {
                mealPlanArray[i] = m;
                return true;
            }
        }

        // No space available
        return false;
    }

    /**
     * Deletes meal plan at given index.
     * @param mealPlanToDelete index
     * @return name of deleted meal plan or null if empty
     */
    public synchronized String deleteMealPlan(int mealPlanToDelete) {

        if (mealPlanToDelete < 0 || mealPlanToDelete >= mealPlanArray.length) {
            return null;
        }

        if (mealPlanArray[mealPlanToDelete] != null) {
            String name = mealPlanArray[mealPlanToDelete].getName();

            // FIX 2: Properly clear slot
            mealPlanArray[mealPlanToDelete] = null;

            return name;
        }

        return null;
    }

    /**
     * Edits meal plan at given index.
     * @param mealPlanToEdit index
     * @param newMealPlan replacement
     * @return original name or null if empty
     */
    public synchronized String editMealPlan(int mealPlanToEdit, MealPlan newMealPlan) {

        if (mealPlanToEdit < 0 || mealPlanToEdit >= mealPlanArray.length) {
            return null;
        }

        if (mealPlanArray[mealPlanToEdit] != null && newMealPlan != null) {
            String originalName = mealPlanArray[mealPlanToEdit].getName();

            // FIX 3: Do NOT overwrite name incorrectly
            mealPlanArray[mealPlanToEdit] = newMealPlan;

            return originalName;
        }

        return null;
    }
}