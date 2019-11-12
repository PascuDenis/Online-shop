package learning.shop.exception;

public class CouldNotBuildOrderException extends RuntimeException {
    public CouldNotBuildOrderException(String message) {
        super(message);
    }

}
