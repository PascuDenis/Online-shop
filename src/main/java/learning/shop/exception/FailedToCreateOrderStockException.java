package learning.shop.exception;

public class FailedToCreateOrderStockException extends RuntimeException {
    public FailedToCreateOrderStockException() {
    }

    public FailedToCreateOrderStockException(String message) {
        super("Could not create order");
    }

}
