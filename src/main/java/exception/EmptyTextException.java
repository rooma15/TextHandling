package exception;

public class EmptyTextException extends RuntimeException{
    public EmptyTextException() {
        super();
    }

    public EmptyTextException(String message) {
        super(message);
    }
}
