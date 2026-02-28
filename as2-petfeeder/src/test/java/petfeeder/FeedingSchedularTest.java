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
        assertFalse(scheduler.hasActiveSchedule(),
                "Scheduler should not be active initially");
    }

    @Test
    void scheduleStartsSuccessfully() {
        scheduler.scheduleRecurringFeeding(0, 1);

        assertTrue(scheduler.hasActiveSchedule(),
                "Scheduler should be active after scheduling");
    }

    @Test
    void stopRemovesActiveSchedule() {
        scheduler.scheduleRecurringFeeding(0, 1);
        scheduler.stop();

        assertFalse(scheduler.hasActiveSchedule(),
                "Scheduler should not be active after stop()");
    }

    // ---------------------------
    // REPLACEMENT TEST
    // ---------------------------

    @Test
    void newScheduleReplacesOldSchedule() {
        scheduler.scheduleRecurringFeeding(0, 1);
        assertTrue(scheduler.hasActiveSchedule());

        scheduler.scheduleRecurringFeeding(0, 1);

        assertTrue(scheduler.hasActiveSchedule(),
                "New schedule should replace old one without error");
    }

    // ---------------------------
    // INVALID INPUT TESTS
    // ---------------------------

    @Test
    void scheduleWithInvalidMealIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                        scheduler.scheduleRecurringFeeding(99, 1),
                "Invalid meal index should throw exception");
    }

    @Test
    void scheduleWithNegativeMealIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                        scheduler.scheduleRecurringFeeding(-1, 1),
                "Negative meal index should throw exception");
    }

    @Test
    void scheduleWithZeroPeriodThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                        scheduler.scheduleRecurringFeeding(0, 0),
                "Zero period should throw exception");
    }

    @Test
    void scheduleWithNegativePeriodThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                        scheduler.scheduleRecurringFeeding(0, -5),
                "Negative period should throw exception");
    }
}