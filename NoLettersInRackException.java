public class NoLettersInRackException extends Exception {

    public NoLettersInRackException() {
        super("You don't have the letters in your rack for the word you are trying to play!");
    }

    public NoLettersInRackException(String message) {
        super(message);
    }
}
