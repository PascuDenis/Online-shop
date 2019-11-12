package learning.shop.exception;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException() {
    }

    public LocationNotFoundException(String message) {
        super(message);
    }
    public LocationNotFoundException(Integer id) {
        super("Could not found location with id: " + id);
    }

}
