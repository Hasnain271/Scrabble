public class NotAWordException extends Exception {
    
    public NotAWordException() {
        super("That is not a valid word!");
    }

    public NotAWordException(String message) {
        super(message);
    }
}
