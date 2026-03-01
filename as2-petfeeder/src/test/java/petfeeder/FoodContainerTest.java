package petfeeder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petfeeder.exceptions.FoodStockException;
import petfeeder.exceptions.MealPlanException;


import static org.junit.jupiter.api.Assertions.*;

class FoodContainerTest {
    private FoodContainer foodContainer;
    private MealPlan mealPlan;

    @BeforeEach
    void setUp() throws MealPlanException {
        // Initialize a new container before each test
        foodContainer = new FoodContainer();

        // Create a valid meal plan with fixed amounts
        mealPlan = new MealPlan();
        mealPlan.setAmtWetFood("5");
        mealPlan.setAmtKibble("5");
        mealPlan.setAmtTreats("5");
        mealPlan.setAmtWater("5");

    }

    /** Verifies default amount of treats */
    @Test
    void getTreats_returnValue(){
        assertEquals(15, foodContainer.getTreats());
    }
    /** Verifies that positive value updates correctly */
    @Test
    void setTreats_update_positiveValue() {
        foodContainer.setTreats(20);
        assertEquals(20, foodContainer.getTreats());
    }
    /** Verifies that negative value updates correctly */
    @Test
    void setTreats_update_negativeValue() {
        foodContainer.setTreats(-5);
        assertNotEquals(-5, foodContainer.getTreats());
    }
    /** Verifies that zero is handled correctly */
    @Test
    void setTreats_update_nullValue() {
        foodContainer.setTreats(0);
        assertEquals(0, foodContainer.getTreats());
    }
    /** Verifies successful addition of valid positive amount as string input*/
    @Test
    void addTreats_addPositive_value() throws Exception{
        foodContainer.addTreats("5");
        assertEquals(20, foodContainer.getTreats());
    }
    /** Verifies successful addition of valid negative amount as string input*/
    @Test
    void addTreats_addNegative_value() throws Exception{
        assertThrows(FoodStockException.class, ()->{
            foodContainer.addTreats("-5");
        });
    }
    /** Verifies exception when input is not numeric */
    @Test
    void addTreats_addString_value() throws Exception{
        assertThrows(FoodStockException.class, ()->{
            foodContainer.addTreats("abc");
        });
    }

    /** Verifies default amount of kibble */
    @Test
    void getKibble_returnValue() {
        assertEquals(15, foodContainer.getKibble());
    }
    /** Verifies that positive value updates correctly */
    @Test
    void setKibble_update_positiveValue() {
        foodContainer.setKibble(25);
        assertEquals(25, foodContainer.getKibble());
    }
    /** Verifies that negative value updates correctly */
    @Test
    void setKibble_update_negativeValue() {
        foodContainer.setTreats(-5);
        assertNotEquals(-5, foodContainer.getKibble());
    }
    /** Verifies that zero is handled correctly */
    @Test
    void setKibble_update_nullValue() {
        foodContainer.setTreats(0);
        assertEquals(0, foodContainer.getTreats());
    }
    /** Verifies successful addition of valid positive amount as string input*/
    @Test
    void addKibble_addPositive_value() throws Exception{
        foodContainer.addKibble("10");
        assertEquals(25,foodContainer.getKibble());
    }
    /** Verifies successful addition of valid negative amount as string input*/
    @Test
    void addKibble_addNegative_value() throws Exception{
        assertThrows(FoodStockException.class, ()->{
            foodContainer.addKibble("-10");
        });
    }
    /** Verifies exception when input is not numeric */
    @Test
    void addKibble_addString_value() throws Exception{
        assertThrows(FoodStockException.class, ()->{
            foodContainer.addKibble("abc");
        });
    }

    /** Verifies default amount of water */
    @Test
    void getWater_returnValue() {
        assertEquals(15, foodContainer.getWater());
    }
    /** Verifies that positive value updates correctly */
    @Test
    void setWater_update_positiveValue() {
        foodContainer.setWater(5);
        assertEquals(5,foodContainer.getWater());
    }
    /** Verifies that negative value updates correctly */
    @Test
    void setWater_update_negativeValue() {
        foodContainer.setTreats(-5);
        assertNotEquals(-5, foodContainer.getTreats());
    }
    /** Verifies that zero is handled correctly */
    @Test
    void setWater_update_nullValue() {
        foodContainer.setTreats(0);
        assertEquals(0, foodContainer.getTreats());
    }
    /** Verifies successful addition of valid positive amount as string input*/
    @Test
    void addWater_addPositive_value() throws Exception{
        foodContainer.addWater("4");
        assertEquals(19,foodContainer.getWater());
    }
    /** Verifies successful addition of valid negative amount as string input*/
    @Test
    void addWater_addNegative_value() throws Exception{
        assertThrows(FoodStockException.class,()-> {
            foodContainer.addWater("-5");
        });
    }
    /** Verifies exception when input is not numeric */
    @Test
    void addWater_addString_value() throws Exception{
        assertThrows(FoodStockException.class, ()->{
            foodContainer.addWater("abc");
        });
    }

    /** Verifies default amount of wetFood */
    @Test
    void getWetFood_returnValue() {
        assertEquals(15, foodContainer.getWetFood());
    }
    /** Verifies that positive value updates correctly */
    @Test
    void setWetFood_update_positiveValue() {
        foodContainer.setWetFood(1);
        assertEquals(1, foodContainer.getWetFood());
    }
    /** Verifies that negative value updates correctly */
    @Test
    void setWetFood_update_negativeValue() {
        foodContainer.setTreats(-5);
        assertNotEquals(-5, foodContainer.getTreats());
    }
    /** Verifies that zero is handled correctly */
    @Test
    void setWetFood_update_nullValue() {
        foodContainer.setTreats(0);
        assertEquals(0, foodContainer.getTreats());
    }
    /** Verifies successful addition of valid positive amount as string input*/
    @Test
    void addWetFood_addPositive_value() throws Exception{
        foodContainer.addWetFood("5");
        assertEquals(20, foodContainer.getWetFood());
    }
    /*
        @Test
        void addWetFood_addPositive_valuee() throws Exception{
            assertThrows(FoodStockException.class, ()-> {
                foodContainer.addWetFood("5");
            });
        }
     */
    // this should be like this

    /** Verifies successful addition of valid negative amount as string input*/
    @Test
    void addWetFood_addNegative_value() throws Exception{
        assertThrows(FoodStockException.class, ()-> {
            foodContainer.addWetFood("-5");
        });
    }
    /*
       @Test
       void addWetFood_addNegative_value() throws Exception{
           foodContainer.addWetFood("-5");
           assertEquals(10, foodContainer.getWetFood());
       }
     */

    /** Verifies exception when input is not numeric */
    @Test
    void addWetFood_addString_value() throws Exception{
        assertThrows(FoodStockException.class, ()->{
            foodContainer.addWetFood("abc");
        });
    }

    /** Verifies that container has sufficient food for meal plan*/
    @Test
    void sufficientFoodComparedToMealPlan () {
        assertTrue(foodContainer.enoughIngredients(mealPlan));

    }

    /** Verifies detection of insufficient wetFood */
    @Test
    void sufficientFoodComparedToMealPlan_WetFood_false () {
        foodContainer.setWetFood(1);
        assertFalse(foodContainer.enoughIngredients(mealPlan));

    }
    /** Verifies detection of insufficient kibble */
    @Test
    void sufficientFoodComparedToMealPlan_Kibble_false () {
        foodContainer.setKibble(1);
        assertFalse(foodContainer.enoughIngredients(mealPlan));

    }
    /** Verifies detection of insufficient water */
    @Test
    void sufficientFoodComparedToMealPlan_Water_false () {
        foodContainer.setWater(1);
        assertFalse(foodContainer.enoughIngredients(mealPlan));

    }
    /** Verifies detection of insufficient treats */
    @Test
    void sufficientFoodComparedToMealPlan_Treats_false () {
        foodContainer.setTreats(1);
        assertFalse(foodContainer.enoughIngredients(mealPlan));

    }

    /** Verifies that ingredients are deducted when sufficient */
    @Test
    void useIngredients_remove_from_container_true() {
        boolean res = foodContainer.useIngredients(mealPlan);
        assertTrue(res);

        assertEquals(10, foodContainer.getTreats());
        assertEquals(10, foodContainer.getKibble());
        assertEquals(10, foodContainer.getWater());
        assertEquals(10, foodContainer.getWetFood());

    }
    /** Verifies that no invalid deduction occurs when insufficient */
    @Test
    void useIngredients_remove_from_container_false() {
        foodContainer.setTreats(4);
        boolean res = foodContainer.useIngredients(mealPlan);
        assertFalse(res);
        assertNotEquals(-1, foodContainer.getTreats());
    }

    /** Verifies formatted string representation of container state */
    @Test
    void toString_returnsCorrectFormat() {
        // By default, FoodContainer has 15 units of everything
        String expected = "Kibble: 15\n" +
                "Water: 15\n" +
                "Wet Food: 15\n" +
                "Treats: 15\n";

        assertEquals(expected, foodContainer.toString());
    }
}