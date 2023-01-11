public class DoNotHaveLettersToSwapException extends Exception {
    
    public DoNotHaveLettersToSwapException() {
        super("You do not have the correct letters in your rack!");
    }

    public DoNotHaveLettersToSwapException(String message) {
        super(message);
    }

}
