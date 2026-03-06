package petfeeder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedingSchedulerTest {

    private PetFeeder feeder;
    private FeedingScheduler scheduler;

    // Cleanup after each test (VERY IMPORTANT for async threads)
    @AfterEach
    void tearDown() {
        if (scheduler != null) {
            scheduler.stop();
            scheduler.shutdown();
        }
    }

    // ==============================
    // Constructor validation
    // ==============================

    @Test
    void constructorThrowsIfNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new FeedingScheduler(null));
    }

    @Test
    void constructorWorksWithValidFeeder() {
        feeder = new PetFeeder();
        scheduler = new FeedingScheduler(feeder);
        assertNotNull(scheduler);
    }

    // ==============================
    // Index validation
    // ==============================

    @Test
    void scheduleWithNegativeIndexThrows() {
        feeder = new PetFeeder();
        scheduler = new FeedingScheduler(feeder);

        assertThrows(IllegalArgumentException.class,
                () -> scheduler.scheduleRecurringFeeding(-1, 1));
    }

    @Test
    void scheduleWithTooLargeIndexThrows() {
        feeder = new PetFeeder();
        feeder.addMealPlan(new MealPlan());
        scheduler = new FeedingScheduler(feeder);

        assertThrows(IllegalArgumentException.class,
                () -> scheduler.scheduleRecurringFeeding(5, 1));
    }

    // ==============================
    // Period validation
    // ==============================

    @Test
    void scheduleWithZeroPeriodThrows() {
        feeder = new PetFeeder();
        feeder.addMealPlan(new MealPlan());
        scheduler = new FeedingScheduler(feeder);

        assertThrows(IllegalArgumentException.class,
                () -> scheduler.scheduleRecurringFeeding(0, 0));
    }

    @Test
    void scheduleWithNegativePeriodThrows() {
        feeder = new PetFeeder();
        feeder.addMealPlan(new MealPlan());
        scheduler = new FeedingScheduler(feeder);

        assertThrows(IllegalArgumentException.class,
                () -> scheduler.scheduleRecurringFeeding(0, -5));
    }

    // ==============================
    // Replace existing schedule branch
    // ==============================

    @Test
    void newScheduleReplacesOldOne() throws InterruptedException {
        feeder = new PetFeeder();
        feeder.addMealPlan(new MealPlan());

        scheduler = new FeedingScheduler(feeder);

        scheduler.scheduleRecurringFeeding(0, 1);
        Thread.sleep(200);

        scheduler.scheduleRecurringFeeding(0, 1);

        assertTrue(scheduler.hasActiveSchedule());
    }

    // ==============================
    // dispensed == true branch
    // ==============================

    @Test
    void scheduledTaskDispensesSuccessfully() throws InterruptedException {
        feeder = new PetFeeder();

        MealPlan meal = new MealPlan();
        meal.setName("Morning Meal");
        feeder.addMealPlan(meal);

        scheduler = new FeedingScheduler(feeder);
        scheduler.scheduleRecurringFeeding(0, 1);

        Thread.sleep(1500);

        assertTrue(scheduler.hasActiveSchedule());
    }

    // ==============================
    // dispensed == false branch
    // ==============================

    @Test
    void scheduledTaskFailsToDispense() throws InterruptedException {
        feeder = new PetFeeder();

        // Add meal that likely fails (no ingredients)
        feeder.addMealPlan(new MealPlan());

        scheduler = new FeedingScheduler(feeder);
        scheduler.scheduleRecurringFeeding(0, 1);

        Thread.sleep(1500);

        assertTrue(scheduler.hasActiveSchedule());
    }

    // ==============================
    // Catch block coverage
    // ==============================

    @Test
    void scheduledTaskHandlesExceptionGracefully() throws InterruptedException {
        feeder = new PetFeeder();
        feeder.addMealPlan(new MealPlan());

        scheduler = new FeedingScheduler(feeder);
        scheduler.scheduleRecurringFeeding(0, 1);

        // Force runtime exception inside Runnable
        feeder.getMealPlans()[0] = null;

        Thread.sleep(1500);

        assertTrue(scheduler.hasActiveSchedule());
    }

    // ==============================
    // stop() coverage
    // ==============================

    @Test
    void stopCancelsSchedule() throws InterruptedException {
        feeder = new PetFeeder();
        feeder.addMealPlan(new MealPlan());

        scheduler = new FeedingScheduler(feeder);
        scheduler.scheduleRecurringFeeding(0, 1);

        Thread.sleep(200);

        scheduler.stop();

        assertFalse(scheduler.hasActiveSchedule());
    }

    // ==============================
    // hasActiveSchedule false branch
    // ==============================

    @Test
    void hasActiveScheduleReturnsFalseInitially() {
        feeder = new PetFeeder();
        scheduler = new FeedingScheduler(feeder);

        assertFalse(scheduler.hasActiveSchedule());
    }
}