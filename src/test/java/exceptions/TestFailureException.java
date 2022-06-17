package exceptions;

public class TestFailureException extends RuntimeException{
    public TestFailureException() {
    }

    public TestFailureException(String message) {
        super(message);
    }

    public TestFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestFailureException(Throwable cause) {
        super(cause);
    }
}
