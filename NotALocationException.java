

public class NotALocationException extends Exception {
    
    public NotALocationException() {
        super("Not a valid location!");
    }

    public NotALocationException(String message) {
        super(message);
    }
}
