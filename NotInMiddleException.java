public class NotInMiddleException extends Exception {
    
    public NotInMiddleException() {
        super("Word is not played in middle!");
    }

    public NotInMiddleException(String message) {
        super(message);
    }

}
