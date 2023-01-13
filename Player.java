import java.util.Collections;

public class Player {
    private int score;
    private String name;
    private String[] rack;
    static Bag bag = new Bag();


    public Player(String name) {
        this.name = name;
        score = 0;
        rack = new String[7];
        generateRack();
    }

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }



    private void generateRack() {
        for (int i = 0; i < 7; i++) {
            rack[i] = bag.getBag().get(0);
            bag.getBag().remove(0);
        }
    }

    
    /** 
     * Add score to the player
     * @param score
     */
    public void addScore(int score) {
        this.score += score;
    }



    /**
     * Draw new tiles from bag
     */
    public void draw() {
        for (int i = 0; i < 7; i++) {
            if (rack[i].equals("") && !bag.isEmpty()) {
                rack[i] = bag.getBag().get(0);
                bag.getBag().remove(0);
            }
        }
    }

    
    /** 
     * Swap (exchange) tiles from the bag
     * @param tilesToSwap
     */
    public void swapTiles(String tilesToSwap) {
        tilesToSwap = tilesToSwap.toUpperCase();
        for (int i = 0; i < tilesToSwap.length(); i++) {
            for (int t = 0; t < 7; t++) {
                // If current rack character (t) equals to the current tilesToSwap character (i) add, shuffle, then get from bag
                if (rack[t].equals(tilesToSwap.substring(i, i + 1))) {
                    bag.getBag().add(rack[t]);
                    Collections.shuffle(bag.getBag());
                    rack[t] = bag.getBag().get(0);

                }
            }
        }
    }

    
    /** 
     * Checks if you can swap the tiles you want to swap
     * @param tilesToSwap
     * @return boolean
     * @throws DoNotHaveLettersToSwapException
     */
    public boolean canSwap(String tilesToSwap) throws DoNotHaveLettersToSwapException {
        boolean flag = true;
        int t = 0;
        tilesToSwap = tilesToSwap.toUpperCase();
        for (int i = 0; i < tilesToSwap.length(); i++) {
            if (!flag) {
                throw new DoNotHaveLettersToSwapException();
            }
            while (t < 7) {

                if (!rack[t].equals(tilesToSwap.substring(i, i + 1))) {
                    flag = false;
                } else if (rack[t].equals(tilesToSwap.substring(i, i + 1))) {
                    flag = true;
                    break;
                }
                t++;
            }
        }

        if (!flag) {
            throw new DoNotHaveLettersToSwapException();
        } else {
            return flag;
        }
    }

    
    /** 
     * Generates the rack in a string form
     * @return String
     */
    public String toString() {
        String expr = "";
        for (int i = 0; i < 7; i++) {
            if (rack[i].equals(" ")) {
                expr += "_ ";
            } else {
                expr += rack[i] + " ";
            }
        }
        return expr;
    }

    
    /** 
     * Checks if you have the required letters in your rack for a word
     * @param word
     * @param loc
     * @param orientation
     * @param b
     * @return boolean
     * @throws NoLettersInRackException
     */
    public boolean hasLettersInRack(String word, String loc, String orientation, Board b) throws NoLettersInRackException {
        String[] l = rack;
        boolean flag = true;
        int t = 0;
        word = word.toUpperCase();
        
        // Gets letters already on the board then removes them from the word
        String letters = b.lettersAlreadyOnBoard(word, loc, orientation);
        word = removeLettersFromWord(letters, word);

        for (int i = 0; i < word.length(); i++) {
            t = 0;
            if (!flag) {
                throw new NoLettersInRackException();
            }
            while (t < 7) {
                if (!l[t].equals(word.substring(i, i + 1))) {
                    flag = false;
                    t++;

                } else if (l[t].equals(word.substring(i, i + 1))) {
                    flag = true;
                    l[t] = "";
                    break;
                }
                    
            }

                t++;
        }
        if (!flag) {
            throw new NoLettersInRackException();
        }
        return flag;
    }

    
    /** 
     * Checks what letters were used from the rack
     * @param word
     * @param loc
     * @param orientation
     * @param b
     * @return int
     */
    public int lettersUsedOnRack(String word, String loc, String orientation, Board b) {

        String letters = b.lettersAlreadyOnBoard(word, loc, orientation);
        word = removeLettersFromWord(letters, word);

        return word.length();
        
        
    }


    
    /** 
     * Removes letters from a word
     * @param lettersToRemove
     * @param word
     * @return String
     */
    public static String removeLettersFromWord(String lettersToRemove, String word) {

        for (int i = 0; i < lettersToRemove.length(); i++) {
            for (int t = 0; t < word.length(); t++) {
                if (lettersToRemove.substring(i, i + 1).equals(word.substring(t, t + 1))) {
                    // Gets index of the letter(s) to remove and then makes a new string without it 
                    int index = t;
                    word = word.substring(0, index) + "" + word.substring(index + 1);
                }
            }
        }

        return word;

    }

    
    /** 
     * Get the name of the player
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** 
     * Get the score of the player
     * @return int
     */
    public int getScore() {
        return score;
    }
    
}
