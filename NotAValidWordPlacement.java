public class NotAValidWordPlacement extends Exception {
    

    public NotAValidWordPlacement() {
        super("Not a valid word placement!");
    }

    public NotAValidWordPlacement(String message) {
        super(message);
    }
}
