package learning.shop.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Integer id) {
        super("Could not found order with id: " + id);
    }

}
