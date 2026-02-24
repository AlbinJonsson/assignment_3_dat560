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
        foodContainer = new FoodContainer();
        mealPlan = new MealPlan();
        mealPlan.setAmtWetFood("5");
        mealPlan.setAmtKibble("5");
        mealPlan.setAmtTreats("5");
        mealPlan.setAmtWater("5");



    }
    // TODO GET methods
    /*
    Here we test the all get methods and return the value for each attribute in FoodContainer class.
    The result is as expected.
     */
    @Test
    void getTreats_returnValue(){
        assertEquals(15, foodContainer.getTreats());

    }

    @Test
    void getKibble_returnValue() {
        assertEquals(15, foodContainer.getKibble());

    }

    @Test
    void getWater_returnValue() {
        assertEquals(15, foodContainer.getWater());

    }
    @Test
    void getWetFood_returnValue() {
        assertEquals(15, foodContainer.getWetFood());
    }

    //TODO SET methods with all positive values

    @Test
    void setTreats_update_positiveValue() {
        foodContainer.setTreats(20);
        assertEquals(20, foodContainer.getTreats());

    }


    @Test
    void setKibble_update_positiveValue() {
        foodContainer.setKibble(25);
        assertEquals(25, foodContainer.getKibble());

    }
    @Test
    void setWater_update_positiveValue() {
        foodContainer.setWater(5);
        assertEquals(5,foodContainer.getWater());


    }
    @Test
    void setWetFood_update_positiveValue() {
        foodContainer.setWetFood(1);
        assertEquals(1, foodContainer.getWetFood());
    }

    //TODO set methods with negative values!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    void setTreats_update_negativeValue() {
        foodContainer.setTreats(-5);
        assertNotEquals(10, foodContainer.getTreats());

    }
    @Test
    void setKibble_update_negativeValue() {
        foodContainer.setTreats(-5);
        assertNotEquals(10, foodContainer.getTreats());

    }
    @Test
    void setWater_update_negativeValue() {
        foodContainer.setTreats(-5);
        assertNotEquals(10, foodContainer.getTreats());

    }
    @Test
    void setWetFood_update_negativeValue() {
        foodContainer.setTreats(-5);
        assertNotEquals(10, foodContainer.getTreats());

    }



    //TODO ADD methods with positive values
    @Test
    void addTreats_addPositive_value() throws Exception{
        foodContainer.addTreats("5");
        assertEquals(20, foodContainer.getTreats());

    }

    @Test
    void addKibble_addPositive_value() throws Exception{
        foodContainer.addKibble("10");
        assertEquals(25,foodContainer.getKibble());

    }

    @Test
    void addWater_addPositive_value() throws Exception{
        foodContainer.addWater("4");
        assertEquals(19,foodContainer.getWater());

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
    @Test
    void addWetFood_addPositive_value() throws Exception{
        foodContainer.addWetFood("5");
        assertEquals(20, foodContainer.getWetFood());
    }


    //TODO ADD methods with negative values

    @Test
    void addTreats_addNegative_value() throws Exception{
        assertThrows(FoodStockException.class, ()->{
            foodContainer.addTreats("-5");

        });
    }


    @Test
    void addKibble_addNegative_value() throws Exception{
        assertThrows(FoodStockException.class, ()->{
            foodContainer.addKibble("-10");
        });


    }


    @Test
    void addWater_addNegative_value() throws Exception{
        assertThrows(FoodStockException.class,()-> {
            foodContainer.addWater("-5");
        });

    }

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
    //TODO For problem 2 addKibble method.
    @Test
    void addKibble_addString_value() throws Exception{
        assertThrows(FoodStockException.class, ()->{
            foodContainer.addKibble("abc");
        });


    }





    @Test
    void sufficientFoodComparedToMealPlan () {
        assertTrue(foodContainer.enoughIngredients(mealPlan));

    }
    @Test
    void sufficientFoodComparedToMealPlan_false () {
        foodContainer.setWetFood(1);
        assertFalse(foodContainer.enoughIngredients(mealPlan));

    }

    @Test
    void useIngredients_remove_from_container() {
        boolean res = foodContainer.useIngredients(mealPlan);
        assertTrue(res);
        /*
        System.out.println("Treats: "+ foodContainer.getTreats());
        System.out.println("Kibble: "+ foodContainer.getKibble());
        System.out.println("Water: "+ foodContainer.getWater());
        System.out.println("WetFood: "+ foodContainer.getWetFood());

         */

        assertEquals(10, foodContainer.getTreats());
        assertEquals(10, foodContainer.getKibble());
        assertEquals(10, foodContainer.getWater());
        assertEquals(10, foodContainer.getWetFood());

    }


}