package petfeeder;

import petfeeder.exceptions.FoodStockException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * Example Unit tests for PetFeeder class.
 * Do not submit as your own!
 */
public class ExampleTest {
    
    private PetFeeder pf;
    private MealPlan p1;
    private MealPlan p2;
    private MealPlan p3;
    private MealPlan p4;

    @BeforeEach
    public void setUp() throws Exception {
        pf = new PetFeeder();
        
        //Set up for p1 (Basic Meal)
        p1 = new MealPlan();
        p1.setName("Morning Feast");
        p1.setAmtTreats("0");
        p1.setAmtKibble("3");
        p1.setAmtWater("1");
        p1.setAmtWetFood("1");
        p1.setEnergyCost("50");
        
        //Set up for p2 (Luxury Mix)
        p2 = new MealPlan();
        p2.setName("Luxury Mix");
        p2.setAmtTreats("20");
        p2.setAmtKibble("3");
        p2.setAmtWater("1");
        p2.setAmtWetFood("1");
        p2.setEnergyCost("75");
        
        //Set up for p3 (Hydration Plus)
        p3 = new MealPlan();
        p3.setName("Hydration Plus");
        p3.setAmtTreats("0");
        p3.setAmtKibble("3");
        p3.setAmtWater("3");
        p3.setAmtWetFood("1");
        p3.setEnergyCost("100");
        
        //Set up for p4 (Snack Time)
        p4 = new MealPlan();
        p4.setName("Snack Time");
        p4.setAmtTreats("4");
        p4.setAmtKibble("0");
        p4.setAmtWater("1");
        p4.setAmtWetFood("1");
        p4.setEnergyCost("65");
    }
    
    @Test
    public void testReplenishFood_Normal() {
        try {
            pf.replenishFood("4","7","0","9"); //Kibble, Water, WetFood, Treats
        } catch (FoodStockException e) {
            fail("FoodStockException should not be thrown");
        }
        String stock = pf.checkFoodStock();
        // Original logic: starts with 15 each.
        // Kibble: 15 + 4 = 19
        // Water: 15 + 7 = 22
        // Wet Food: 15 + 0 = 15
        // Treats: 15 + 9 = 24
        String expected = "Kibble: 19\nWater: 22\nWet Food: 15\nTreats: 24\n";
        assertEquals(expected, stock);
    }
    
    @Test
    public void testReplenishFoodException() {
        Throwable exception = assertThrows(
                FoodStockException.class, () -> {
                    pf.replenishFood("4", "-1", "asdf", "3"); // Should throw a FoodStockException
                }
                );
    }
    
    @Test
    public void testDispenseMeal_Normal() {
        pf.addMealPlan(p1);
        // Cost is 50, Paid 75 -> Change should be 25
        assertEquals(25, pf.dispenseMeal(0, 75));
    }

}