package learning.shop.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Integer id) {
        super("Could not found customer category with id: " + id);
    }

    public CustomerNotFoundException(String name) {
        super("Could not found customer with username: " + name);
    }
}
