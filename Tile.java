

public class Tile {
    private String letter;

    public Tile(String letter) {
        this.letter = letter;
    }

    public Tile() {
        letter = "";
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    
    public boolean hasLetter() {
        if (!this.getLetter().equals("")) {
            return true;
        } else {
            return false;
        }
    }
    

}
