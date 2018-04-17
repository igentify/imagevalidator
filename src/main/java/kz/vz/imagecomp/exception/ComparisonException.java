package kz.vz.imagecomp.exception;

public class ComparisonException extends RuntimeException {
    public ComparisonException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComparisonException(String message) {
        super(message);
    }
}
