package petfeeder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petfeeder.exceptions.FoodStockException;
import petfeeder.exceptions.MealPlanException;

import static org.junit.jupiter.api.Assertions.*;
class MealPlanTest {
    private MealPlan mp;


    @BeforeEach
    void setUp() throws Exception{
        mp  = new MealPlan();

        mp.setName("Test MealPlan");
        mp.setAmtKibble("10");
        mp.setAmtTreats("10");
        mp.setAmtWater("10");
        mp.setAmtWetFood("10");
    }

    /** Verifies that the return function works as expected and returns 10 treats*/
    @Test
    void testGetAmtTreats_valid(){
        assertEquals(10, mp.getAmtTreats());
    }

    /** Verifies that the return function works as expected and returns 10 treats*/
    @Test
    void testGetAmtKibble_valid(){
        assertEquals(10, mp.getAmtKibble());
    }
    /** Verifies that the return function works as expected and returns 10 treats*/
    @Test
    void testGetAmtWater_valid(){
        assertEquals(10, mp.getAmtWater());
    }
    /** Verifies that the return function works as expected and returns 10 treats*/
    @Test
    void testGetAmtWetFood_valid(){
        assertEquals(10, mp.getAmtWetFood());
    }


    /** Verifies that a change with valid input in Treats results in updated amount */
    @Test
    void testSetAmtTreats_valid() throws MealPlanException {
        mp.setAmtTreats("8");
        assertEquals(8, mp.getAmtTreats());
    }

    /** Verifies that a change with valid input in Kibble results in updated amount */
    @Test
    void testSetAmtKibble_valid() throws MealPlanException {
        mp.setAmtKibble("8");
        assertEquals(8, mp.getAmtKibble());
    }

    /** Verifies that a change with valid input in Water results in updated amount */
    @Test
    void testSetAmtWater_valid() throws MealPlanException {
        mp.setAmtWater("8");
        assertEquals(8, mp.getAmtWater());
    }

    /** Verifies that a change with valid input in Wet Food results in updated amount */
    @Test
    void testSetAmtWetFood_valid() throws MealPlanException {
        mp.setAmtWetFood("8");
        assertEquals(8, mp.getAmtWetFood());
    }

    /** Verifies that non-integer input for Treats throws MealPlanException */
    @Test
    void testSetAmtTreats_nonIntStringInput() {
        assertThrows(MealPlanException.class, () ->
                mp.setAmtTreats("abc")
        );
    }

    /** Verifies that non-integer input for Kibble throws MealPlanException */
    @Test
    void testSetAmtKibble_nonIntStringInput() {
        assertThrows(MealPlanException.class, () ->
                mp.setAmtKibble("abc")
        );
    }

    /** Verifies that non-integer input for Water throws MealPlanException */
    @Test
    void testSetAmtWater_nonIntStringInput() {
        assertThrows(MealPlanException.class, () ->
                mp.setAmtWater("abc")
        );
    }

    /** Verifies that non-integer input for Wet Food throws MealPlanException */
    @Test
    void testSetAmtWetFood_nonIntStringInput() {
        assertThrows(MealPlanException.class, () ->
                mp.setAmtWetFood("abc")
        );
    }

    /** Verifies that null input for Treats throws MealPlanException */
    @Test
    void testSetAmtTreats_nullInput() {
        assertThrows(MealPlanException.class, () ->
                mp.setAmtTreats(null)
        );
    }

    /** Verifies that null input for Kibble throws MealPlanException */
    @Test
    void testSetAmtKibble_nullInput() {
        assertThrows(MealPlanException.class, () ->
                mp.setAmtKibble(null)
        );
    }

    /** Verifies that null input for Water throws MealPlanException */
    @Test
    void testSetAmtWater_nullInput() {
        assertThrows(MealPlanException.class, () ->
                mp.setAmtWater(null)
        );
    }

    /** Verifies that null input for Wet Food throws MealPlanException */
    @Test
    void testSetAmtWetFood_nullInput() {
        assertThrows(MealPlanException.class, () ->
                mp.setAmtWetFood(null)
        );
    }

    /** Verify that attempting a negative string input throws MealPlanException*/
    @Test
    void testSetAmtTreats_negativeInput() {
        assertThrows(MealPlanException.class, () ->
                mp.setAmtTreats("-1")
                );
    }

    /** Verify that attempting a negative string input for Kibble throws MealPlanException */
    @Test
    void testSetAmtKibble_negativeInput() {
        assertThrows(MealPlanException.class, () ->
                mp.setAmtKibble("-1")
        );
    }

    /** Verify that attempting a negative string input for Water throws MealPlanException */
    @Test
    void testSetAmtWater_negativeInput() {
        assertThrows(MealPlanException.class, () ->
                mp.setAmtWater("-1")
        );
    }

    /** Verify that attempting a negative string input for Wet Food throws MealPlanException */
    @Test
    void testSetAmtWetFood_negativeInput() {
        assertThrows(MealPlanException.class, () ->
                mp.setAmtWetFood("-1")
        );
    }


    /** Verifies the getName function returns the name that has been set */
    @Test
    void testGetName_validName(){
        assertEquals("Test MealPlan", mp.getName());
    }

    /** Verifies that the function correctly updates the name */
    @Test
    void testSetName_validName(){
        assertEquals("Test MealPlan", mp.getName());
        mp.setName("Test MealPlan2");
        assertEquals("Test MealPlan2", mp.getName());
    }


    /** Verifies that null value as input does not change the name of the meal plan*/
    @Test
    void testSetName_nullName(){
        String before = mp.getName();
        mp.setName(null);
        String after = mp.getName();
        assertEquals(before, after);
    }


    /** Verifies that getEnergyCost returns the expected amount of energy
     * for the specified meal
     */
    @Test
    void testGetEnergyCost_validEnergyCost(){
        assertEquals(500, mp.getEnergyCost());
    }

    /** Verifies that the toString function returns the correct meal plan name */
    @Test
    void testToString_returnsName() {
        MealPlan m = new MealPlan();
        m.setName("Morning Feast");

        assertEquals("Morning Feast", m.toString());
    }

    /** Verify that hashCode uses the name’s hash when name is not null */
    @Test
    void testHashCode_whenNameSet_returnsCorrectHash() {
        MealPlan m = new MealPlan();
        m.setName("Morning Feast");

        int expected = 31 + "Morning Feast".hashCode();

        assertEquals(expected, m.hashCode());
    }

    /** Verify that hashCode returns base value when name is null*/
    @Test
    void testHashCode_whenNameNull_returns31() {
        MealPlan m = new MealPlan();

        assertEquals(31, m.hashCode());
    }
    /** Verifies that the equals method returns true when comparing an object to itself */
    @Test
    void testEquals_sameObject_returnsTrue() {
        MealPlan m = new MealPlan();
        assertTrue(m.equals(m));
    }

    /** Verifies that the equals method returns false when comparing a MealPlan object to null */
    @Test
    void testEquals_nullObject_returnsFalse() {
        MealPlan m = new MealPlan();
        assertFalse(m.equals(null));
    }

    /** Verifies that the equals method returns false when comparing a MealPlan object to an object of a different class */
    @Test
    void testEquals_differentClass_returnsFalse() {
        MealPlan m = new MealPlan();
        String notMealPlan = "Not a meal plan";

        assertFalse(m.equals(notMealPlan));
    }

    /** Verifies that the equals method returns true when both MealPlan objects has no name */
    @Test
    void testEquals_bothNamesEmpty_returnsTrue() {
        MealPlan m1 = new MealPlan();
        MealPlan m2 = new MealPlan();

        assertTrue(m1.equals(m2));
    }

    /** Verifies that the equals method returns false when two MealPlan objects have different names*/
    @Test
    void testEquals_differentNames_returnsFalse() {
        MealPlan m1 = new MealPlan();
        m1.setName("Breakfast");

        MealPlan m2 = new MealPlan();
        m2.setName("Dinner");

        assertFalse(m1.equals(m2));
    }

    /** Verifies that the equals method returns true when two MealPlan objects have the same name */
    @Test
    void testEquals_sameNames_returnsTrue() {
        MealPlan m1 = new MealPlan();
        m1.setName("Lunch");

        MealPlan m2 = new MealPlan();
        m2.setName("Lunch");

        assertTrue(m1.equals(m2));
    }

}