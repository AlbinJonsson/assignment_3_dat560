package petfeeder;

import petfeeder.exceptions.MealPlanException;

public class MealPlan {
    private String name;
    private int energyCost;
    private int amtKibble;
    private int amtWater;
    private int amtWetFood;
    private int amtTreats;
    
    /**
     * Creates a default meal plan for the pet feeder.
     */
    public MealPlan() {
        this.name = "";
        this.energyCost = 0;
        this.amtKibble = 0;
        this.amtWater = 0;
        this.amtWetFood = 0;
        this.amtTreats = 0;
    }
    
    /**
     * Returns the amount of treats in the meal plan.
     * @return int
     */
    public int getAmtTreats() {
        return amtTreats;
    }

    /**
     * Sets the amount of treats required for the meal.
     * @param treats The amount of treats to set (as a string).
     * @throws MealPlanException if the input is not a positive integer.
     */
    public void setAmtTreats(String treats) throws MealPlanException {
        int amtTreats = 0;
        try {
            amtTreats = Integer.parseInt(treats);
        } catch (NumberFormatException e) {
            throw new MealPlanException("Units of treats must be a positive integer");
        }
        if (amtTreats >= 0) {
            this.amtTreats = amtTreats;
        } else {
            throw new MealPlanException("Units of treats must be a positive integer");
        }
    }

    /**
     * Returns the amount of kibble in the meal plan.
     * @return int
     */
    public int getAmtKibble() {
        return amtKibble;
    }

    /**
     * Sets the amount of kibble required for the meal.
     * @param kibble The amount of kibble to set (as a string).
     * @throws MealPlanException if the input is not a positive integer.
     */
    public void setAmtKibble(String kibble) throws MealPlanException {
        int amtKibble = 0;
        try {
            amtKibble = Integer.parseInt(kibble);
        } catch (NumberFormatException e) {
            throw new MealPlanException("Units of kibble must be a positive integer");
        }
        if (amtKibble >= 0) {
            this.amtKibble = amtKibble;
        } else {
            throw new MealPlanException("Units of kibble must be a positive integer");
        }
    }

    /**
     * Returns the amount of water in the meal plan.
     * @return int
     */
    public int getAmtWater() {
        return amtWater;
    }

    /**
     * Sets the amount of water required for the meal.
     * @param water The amount of water to set (as a string).
     * @throws MealPlanException if the input is not a positive integer.
     */
    public void setAmtWater(String water) throws MealPlanException{
        int amtWater = 0;
        try {
            amtWater = Integer.parseInt(water);
        } catch (NumberFormatException e) {
            throw new MealPlanException("Units of water must be a positive integer");
        }
        if (amtWater >= 0) {
            this.amtWater = amtWater;
        } else {
            throw new MealPlanException("Units of water must be a positive integer");
        }
    }

    /**
     * Returns the amount of wet food in the meal plan.
     * @return int
     */
    public int getAmtWetFood() {
        return amtWetFood;
    }

    /**
     * Sets the amount of wet food required for the meal.
     * @param wetFood The amount of wet food to set (as a string).
     * @throws MealPlanException if the input is not a positive integer.
     */
    public void setAmtWetFood(String wetFood) throws MealPlanException {
        int amtWetFood = 0;
        try {
            amtWetFood = Integer.parseInt(wetFood);
        } catch (NumberFormatException e) {
            throw new MealPlanException("Units of wet food must be a positive integer");
        }
        if (amtWetFood >= 0) {
            this.amtWetFood = amtWetFood;
        } else {
            throw new MealPlanException("Units of wet food must be a positive integer");
        }
    }

    /**
     * Returns the name of the meal plan.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the meal plan.
     * @param name The name to set.
     */
    public void setName(String name) {
        if(name != null) {
            this.name = name;
        }
    }

    /**
     * Returns the energy cost of the meal plan.
     * @return int
     */
    public int getEnergyCost() {
        return energyCost;
    }

    /**
     * Sets the energy cost of the meal plan.
     * @param energyCost The energy cost to set (as a string).
     * @throws MealPlanException if the input is not a positive integer.
     */
    public void setEnergyCost(String energyCost) throws MealPlanException{
        int amtEnergy = 0;
        try {
            amtEnergy = Integer.parseInt(energyCost);
        } catch (NumberFormatException e) {
            throw new MealPlanException("Energy cost must be a positive integer");
        }
        if (amtEnergy >= 0) {
            this.energyCost = amtEnergy;
        } else {
            throw new MealPlanException("Energy cost must be a positive integer");
        }
    } 
    
    /**
     * Returns the name of the meal plan.
     * @return String
     */
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final MealPlan other = (MealPlan) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
