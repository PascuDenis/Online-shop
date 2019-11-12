package learning.shop.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(Integer id) {
        super("Could not found stock with id: " + id);
    }
}
