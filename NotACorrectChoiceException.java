public class NotACorrectChoiceException extends Exception {
    

    public NotACorrectChoiceException() {
        super("That is not a valid option!");
    }


    public NotACorrectChoiceException(String message) {
        super(message);
    }
}
