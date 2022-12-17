import java.util.HashMap;

public class Tile {
    private HashMap<String, Integer> letterPoints;

    public Tile() {
        letterPoints = generateLetterPoints();
    }

    public Tile(String letter) {
        
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public HashMap<String, Integer> generateLetterPoints() {
        HashMap<String, Integer> x = new HashMap<String, Integer>() 
        {{
            put("A", 1);
            put("B", 3);
            put("C", 3);
            put("D", 2);
            put("E", 1);
            put("F", 4);
            put("G", 2);
            put("H", 4);
            put("I", 1);
            put("J", 8);
            put("K", 5);
            put("L", 1);
            put("M", 3);
            put("N", 1);
            put("O", 1);
            put("P", 3);
            put("Q", 10);
            put("R", 1);
            put("S", 1);
            put("T", 1);
            put("U", 1);
            put("V", 4);
            put("W", 4);
            put("X", 8);
            put("Y", 4);
            put("Z", 10);
            

        }};


    }

    
    public boolean hasLetter() {
        if (!this.getLetter().equals("")) {
            return true;
        } else {
            return false;
        }
    }
    

}
