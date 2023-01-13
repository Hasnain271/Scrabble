public class NotEnoughTilesInBagException extends Exception {
    

    public NotEnoughTilesInBagException() {
        super("There are not enough letters in the bag!");
    }

    public NotEnoughTilesInBagException(String message) {
        super(message);
    }
}
