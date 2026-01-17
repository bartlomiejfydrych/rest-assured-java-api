package exceptions;

public class ExceptionJsonDeserialization extends RuntimeException {

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    public ExceptionJsonDeserialization(String message, Throwable cause) {
        super(message, cause);
    }
}
