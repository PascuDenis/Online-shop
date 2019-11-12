package learning.shop.exception;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(Integer id) {
        super("Could not found address with id: " + id);
    }

}
