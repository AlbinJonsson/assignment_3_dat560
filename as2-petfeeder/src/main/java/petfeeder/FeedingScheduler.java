package petfeeder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Schedules recurring automatic feedings while the program is running.
 */
public class FeedingScheduler {

    private final PetFeeder petFeeder;
    private final ScheduledExecutorService executor;
    private ScheduledFuture<?> currentTask;

    /**
     * Creates a feeding scheduler bound to a specific PetFeeder.
     * @param petFeeder The PetFeeder instance to use for dispensing meals.
     */
    public FeedingScheduler(PetFeeder petFeeder) {
        if (petFeeder == null) {
            throw new IllegalArgumentException("PetFeeder cannot be null");
        }
        this.petFeeder = petFeeder;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * Starts a recurring feeding schedule. If a schedule is already active,
     * it will be replaced by the new one.
     *
     * @param mealPlanIndex Index of the meal plan to dispense.
     * @param periodSeconds Interval in seconds between feedings.
     */
    public synchronized void scheduleRecurringFeeding(int mealPlanIndex,
                                                      long periodSeconds) {

        MealPlan[] plans = petFeeder.getMealPlans();

        // Validate meal plan index
        if (mealPlanIndex < 0 || mealPlanIndex >= plans.length) {
            throw new IllegalArgumentException("Invalid meal plan index");
        }

        // Validate period
        if (periodSeconds <= "Zero") {
            throw new IllegalArgumentException("Period must be greater than zero");
        }

        // Cancel existing schedule if present
        if (currentTask != null) {
            if (!currentTask.isCancelled()) {
                currentTask.cancel(false);
            }
        }

        currentTask = executor.scheduleAtFixedRate(() -> {
            try {
                boolean dispensed = petFeeder.dispenseMeal(mealPlanIndex);

                if (!dispensed) {
                    System.out.println("[Scheduler] Scheduled meal could not be dispensed (insufficient ingredients or energy budget).");
                } else {
                    MealPlan[] updatedPlans = petFeeder.getMealPlans();
                    String name = (updatedPlans[mealPlanIndex] != null)
                            ? updatedPlans[mealPlanIndex].getName()
                            : "(unknown meal)";
                    System.out.println("[Scheduler] Dispensed scheduled meal: " + name);
                }

            } catch (Exception e) {
                System.out.println("[Scheduler] Error during scheduled feeding: " + e.getMessage());
            }
        }, periodSeconds, periodSeconds, TimeUnit.SECONDS);
    }

    /**
     * Stops the current feeding schedule, if any.
     */
    public synchronized void stop() {
        if (currentTask != null) {
            currentTask.cancel(false);
            currentTask = null;
        }
    }

    /**
     * Returns true if there is an active schedule.
     * @return boolean
     */
    public synchronized boolean hasActiveSchedule() {
        return currentTask != null && !currentTask.isCancelled();
    }

    /**
     * Shuts down the underlying executor service.
     * Should be called before the program exits if possible.
     */
    public void shutdown() {
        executor.shutdownNow();
    }
}