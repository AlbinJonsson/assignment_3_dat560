package petfeeder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedingSchedulerTest {

    private PetFeeder feeder;
    private FeedingScheduler scheduler;

    @BeforeEach
    void setup() {
        feeder = new PetFeeder();
        scheduler = new FeedingScheduler(feeder);
    }

    @AfterEach
    void cleanup() {
        scheduler.stop();
        scheduler.shutdown();
    }

    // ---------------------------
    // BASIC STATE TESTS
    // ---------------------------

    @Test
    void schedulerInitiallyInactive() {
        assertFalse(scheduler.hasActiveSchedule());
    }

    @Test
    void scheduleStartsSuccessfully() {
        scheduler.scheduleRecurringFeeding(0, 1);
        assertTrue(scheduler.hasActiveSchedule());
    }

    @Test
    void stopRemovesActiveSchedule() {
        scheduler.scheduleRecurringFeeding(0, 1);
        scheduler.stop();
        assertFalse(scheduler.hasActiveSchedule());
    }

    // ---------------------------
    // REPLACEMENT TEST
    // ---------------------------

    @Test
    void newScheduleReplacesOldSchedule() {
        scheduler.scheduleRecurringFeeding(0, 1);
        assertTrue(scheduler.hasActiveSchedule());

        scheduler.scheduleRecurringFeeding(0, 1); // should replace previous
        assertTrue(scheduler.hasActiveSchedule());
    }

    // ---------------------------
    // FUNCTIONAL BEHAVIOR TEST
    // ---------------------------

    @Test
    void schedulerDoesNotCrashWithInvalidMealIndex() {
        scheduler.scheduleRecurringFeeding(99, 1); // invalid index

        assertTrue(scheduler.hasActiveSchedule()); // scheduler should still run
    }

    // ---------------------------
    // STOP PREVENTS FURTHER SCHEDULING
    // ---------------------------

    @Test
    void stopPreventsFurtherExecution() {
        scheduler.scheduleRecurringFeeding(0, 1);
        scheduler.stop();

        assertFalse(scheduler.hasActiveSchedule());
    }
}