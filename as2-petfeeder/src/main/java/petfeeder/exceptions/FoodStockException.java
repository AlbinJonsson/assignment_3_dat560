package petfeeder.exceptions;

public class FoodStockException extends Exception {
    private static final long serialVersionUID = 1L;
    public FoodStockException(String msg) {
        super(msg);
    }
}
